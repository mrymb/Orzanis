package com.example.maryambaig.orzanis.Entity;

import android.location.Address;

import java.util.List;

/**
 * Created by MaryamBaig on 6/1/2017.
 */
public class User_Location {

    Address address;
    double latitude;
    double longitude;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
