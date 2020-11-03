package ex.devs.exbooks.Screens.bookDetailesScreen;

import android.graphics.Color;
import android.location.Location;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import ex.devs.exbooks.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double bookLat;
    private double bookLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        bookLat=getIntent().getDoubleExtra("Lat",0);
        bookLong=getIntent().getDoubleExtra("Long",0);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        mMap.setMinZoomPreference(15.0f);

        double lat=getIntent().getDoubleExtra("Lat",0);
        double lng=getIntent().getDoubleExtra("Long",0);
        Location location=getIntent().getParcelableExtra("myLocation");
        LatLng endPoint=new LatLng(bookLat,bookLong);
        LatLng startPoint=new LatLng(location.getLatitude(),location.getLongitude());

        Polyline polyline=googleMap.addPolyline(new PolylineOptions().color(Color.RED)
                .clickable(true)
                .add(
                        startPoint,endPoint
                ));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(startPoint.latitude,startPoint.longitude)));
        mMap.addMarker(new MarkerOptions().position(startPoint).title("me"));
        mMap.addMarker(new MarkerOptions().position(endPoint).title("book"));
    }


}
