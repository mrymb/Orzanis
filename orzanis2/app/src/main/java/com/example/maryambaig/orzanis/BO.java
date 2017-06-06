package com.example.maryambaig.orzanis;

import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

import com.example.maryambaig.orzanis.Activities.GPSTracker;
import com.example.maryambaig.orzanis.Dao.UserDao;
import com.example.maryambaig.orzanis.Entity.User_Info;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by MaryamBaig on 6/6/2017.
 */
public class BO {

    GPSTracker gps;
    User_Info user_info=new User_Info();
    UserDao userDao=new UserDao();


    public User_Info getUserLocation(String User, Context context)
    {
        gps=new GPSTracker(context);
        if(gps.canGetLocation) {
            double latitude = gps.getLatitude();
             double longitude = gps.getLongitude();

            user_info.setLatitude(latitude);
            user_info.setLongitude(longitude);

        }
        else
        {
            gps.showSettingsAlert();
        }
        return user_info;
    }

    public String getAddressFromLocation(double lat,double lon, Context context, Locale locale) throws IOException {
        Geocoder gcd=new Geocoder(context,locale);
        List<Address> addresses;

        addresses=gcd.getFromLocation(lat,lon,1);

        String address=addresses.get(0).getAddressLine(0);
        String city=addresses.get(0).getLocality();
        String state=addresses.get(0).getAdminArea();
        String country=addresses.get(0).getCountryName();
        String knownName=addresses.get(0).getFeatureName();

        return ""+address+", "+city+", "+state+", "+country+ ", Feature:  "+knownName+".";
    }

    public void AddUser(String username)
    {
        userDao.addtoDb(username,user_info);
    }

    public String getUsername(Context context)
    {
        Cursor c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex("display_name"));

    }


}
