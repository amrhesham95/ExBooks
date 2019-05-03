package com.example.exbooks.Screens.bookDetailesScreen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exbooks.Screens.ChatScreen.MessageActivity;
import com.example.exbooks.R;
import com.example.exbooks.model.Book;
import com.google.android.gms.maps.model.LatLng;

public class BookDetailesActivity extends AppCompatActivity implements BookDetailesContract.BookDetailesViewInterface {
    BookDetailesContract.BookDetailesPresenterInterface bookDetailesPresenterInterface;
    ImageView bookImage;
    //TextView bookTitle;
    TextView bookCategory;
    TextView bookDescription;
    TextView bookLocation;
    ImageView bookImgView ;
    Book book;
    FloatingActionButton chatBtn;
    String ownerPhone ;
    FloatingActionButton showMapBtn;
    LocationManager locationManager;
    private static final int PERM_REQ=0;
    MyLocationListener locationListener;
    public static Location myLocationGlobal;
    Toolbar toolbar ;
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
        chatBtn.setOnClickListener((event)->{
            Intent intent=new Intent(this, MessageActivity.class);
            intent.putExtra("userID",book.getUser());
            startActivity(intent);
        });
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
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},PERM_REQ);

        }

        else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

    }
}
