package com.example.maryambaig.orzanis.Activities;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by MaryamBaig on 5/29/2017.
 */
public class GPSTracker extends Service implements LocationListener {


    private Context mcontext;
    boolean isGPSenabled = false;
    boolean isNetworkEnabled = false;
    public boolean canGetLocation = false;
    Location location;
    double latitude;
    double longitude;

    private long Min_distance_to_change_for_updates = 10;
    private long Min_Time_bw_updates = 1000 * 60;
    protected LocationManager locationManager;

    public GPSTracker(Context context) {
        this.mcontext = context;
        getLocation();
    }

    private Location getLocation() {
        try {
            locationManager = (LocationManager) mcontext.getSystemService(LOCATION_SERVICE);

            isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSenabled && !isNetworkEnabled) {

            } else {
                this.canGetLocation = true;

                if (isNetworkEnabled) {

                   locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Min_Time_bw_updates, Min_Time_bw_updates,this);


                    Log.d("Network","Network");

                    if(locationManager!=null)
                    {
                        location=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if(location!=null)
                        {
                            latitude=location.getLatitude();
                            longitude=location.getLongitude();
                        }
                    }


                }
            }

            if (isGPSenabled)
            {
                if (location==null)
                {
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,Min_Time_bw_updates,Min_distance_to_change_for_updates,this);
                    Log.d("GPS Enabled","GPS Enabled");

                    if(locationManager!=null)
                    {
                        location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(location!=null)
                        {
                            latitude=location.getLatitude();
                            longitude=location.getLongitude();
                        }
                    }

                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return location;
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public double getLatitude()
    {
        if(location!=null)
        {
            latitude=location.getLatitude();
        }
        return latitude;
    }

    public double getLongitude()
    {
        if(location!=null)
        {
            latitude=location.getLongitude();
        }
        return longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
        }

    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mcontext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // Setting Icon to Dialog
       //alertDialog.setIcon(R.drawable.delete);

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mcontext.startActivity(intent);
                }
            });

       // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
                }
            });

       // Showing Alert Message
        alertDialog.show();
        }

    public void stopUsingGPS(){
        if(locationManager != null){
            locationManager.removeUpdates(GPSTracker.this);
            }
       }

}
