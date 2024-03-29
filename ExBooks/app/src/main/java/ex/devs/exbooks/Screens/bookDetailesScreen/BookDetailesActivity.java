package ex.devs.exbooks.Screens.bookDetailesScreen;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;

import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import ex.devs.exbooks.Screens.ChatScreen.MessageActivity;
import ex.devs.exbooks.R;
import ex.devs.exbooks.model.Book;

public class BookDetailesActivity extends AppCompatActivity implements BookDetailesContract.BookDetailesViewInterface {
    BookDetailesContract.BookDetailesPresenterInterface bookDetailesPresenterInterface;
    ImageView bookImage;
    TextView bookCategory;
    TextView bookDescription;
    TextView bookLocation;
    ImageView bookImgView ;
    ImageView bookImgInDialougView;
    Book book;
    FloatingActionButton chatBtn;
    String ownerPhone ;
    FloatingActionButton showMapBtn;
    LocationManager locationManager;
    private static final int PERM_REQ=0;
    MyLocationListener locationListener;
    public static Location myLocationGlobal;
    Toolbar toolbar ;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detailes);

        bookDetailesPresenterInterface=new BookDetailesPresenterImp(this);
        book = (Book)getIntent().getSerializableExtra("book");
        bookCategory=(TextView)findViewById(R.id.detailesBookCategory);
//        bookCategory=(TextView)findViewById(R.id.detailesBookCategory);
        bookDescription=(TextView)findViewById(R.id.detailesBookDescription);
        bookLocation=(TextView)findViewById(R.id.detailesBookLocation);
        bookImgView = findViewById(R.id.bookImageInDetailesActivity);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_bookdetails);
        chatBtn=findViewById(R.id.chat_btn);
        showMapBtn=findViewById(R.id.showMap_btn);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


        setDataOfBook();
        bookDetailesPresenterInterface.getBookOwnerPhone(book.getUser());
        locationListener=new MyLocationListener();
    /*    Button callBtn = findViewById(R.id.call_btn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ownerPhone));
                startActivity(intent);
            }
        });*/

        // so i can't chat with myself because it leads to crash
        if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(book.getUser())){
            chatBtn.setOnClickListener((event)->{
                Toast.makeText(this, "This book was added by you", Toast.LENGTH_LONG).show();
            });
        }else{
            chatBtn.setOnClickListener((event)->{
                Intent intent=new Intent(this, MessageActivity.class);
                intent.putExtra("userID",book.getUser());
                startActivity(intent);
            });
        } 
        showMapBtn.setOnClickListener((event)->{
            String geoUri = "http://maps.google.com/maps?q=loc:" + book.getReturnedPlaceLat() + "," + book.getReturnedPlaceLong();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
            startActivity(intent);
//            Intent intent = new Intent(this,MapActivity.class);
//            intent.putExtra("Lat",book.getReturnedPlaceLat());
//            intent.putExtra("Long",book.getReturnedPlaceLong());
//            intent.putExtra("myLocation",myLocationGlobal);
//            startActivity(intent);
        });
        bookImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Dialog dialog = new Dialog(BookDetailesActivity.this);
                dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(getLayoutInflater().inflate(R.layout.image_layout
                        , null));




                bookImgInDialougView=dialog.findViewById(R.id.bookImageInDetailesDialog);
                bookDetailesPresenterInterface.getBookImg(book.getImgUrl(),bookImgInDialougView);


                dialog.show();
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
                dialog.getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);



            }
        });


        FloatingActionButton callFAB = findViewById(R.id.callFAB);
        callFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+ownerPhone));
                System.out.println("tel:"+ownerPhone);
                startActivity(intent);

            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(BookDetailesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BookDetailesActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERM_REQ);


        }
        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }


    }

    private void setDataOfBook() {
        bookCategory.setText(book.getCategory());
        bookDescription.setText(book.getDescription());
        bookLocation.setText(book.getLocation());
//        bookCategory.setText(book.getCategory());
        toolbar.setTitle(book.getTitle());
        System.out.println("bookUrl->>>   "+book.getImgUrl());
        bookDetailesPresenterInterface.getBookImg(book.getImgUrl(),bookImgView);

    }

    @Override
    public void setOwnerPhone(String phone) {
        this.ownerPhone = phone ;
    }
    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            //Toast.makeText(getApplicationContext(), "Longitude:"+location.getLongitude()+"\n"+"latitude:"+location.getLatitude(), Toast.LENGTH_SHORT).show();
            myLocationGlobal=location;

            locationManager.removeUpdates(locationListener);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (ActivityCompat.checkSelfPermission(BookDetailesActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(BookDetailesActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           // ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERM_REQ);
            showMapBtn.hide();

        }

        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }
}
