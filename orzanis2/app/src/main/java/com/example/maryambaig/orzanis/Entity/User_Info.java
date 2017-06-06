package com.example.maryambaig.orzanis.Entity;

import android.location.Address;

import java.util.List;

/**
 * Created by MaryamBaig on 6/1/2017.
 */
public class User_Info {

    String mobile;
    double latitude;
    double longitude;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

}
