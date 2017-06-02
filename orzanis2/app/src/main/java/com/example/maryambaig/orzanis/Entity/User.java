package com.example.maryambaig.orzanis.Entity;

import android.location.Address;

import java.util.List;

/**
 * Created by MaryamBaig on 6/1/2017.
 */
public class User {
    String name;
    String mobile_number;
    Address address;
    double latitude;
    double longitude;
    List<User> Contact_List;

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

    public List<User> getContact_List() {
        return Contact_List;
    }

    public void setContact_List(List<User> contact_List) {
        Contact_List = contact_List;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }



}
