package com.example.maryambaig.orzanis.Activities;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.maryambaig.orzanis.Entity.User;
import com.example.maryambaig.orzanis.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnShowLocation;
    TextView textview;
    GPSTracker gps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btnShowLocation=(Button)findViewById(R.id.btn);
        textview=(TextView)findViewById(R.id.textView);
        btnShowLocation.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                gps=new GPSTracker(MapsActivity.this);

                if(gps.canGetLocation) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    textview.setText("location is" + latitude + ", " + longitude + ".");
                    Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> address = null;
                    User user=new User();

                    try {
                        address = gcd.getFromLocation(latitude, longitude, 1);
                        user.setAddress(address.get(0));
                        user.setLatitude(latitude);
                        user.setLongitude(longitude);

                        LatLng current = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(current).title("Marker in" +
                        address.get(0).getLocality()));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
                else
                {
                    gps.showSettingsAlert();
                }
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
