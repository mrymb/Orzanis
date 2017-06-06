package com.example.maryambaig.orzanis.Dao;


import android.support.annotation.NonNull;

import com.example.maryambaig.orzanis.Entity.User_Info;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by MaryamBaig on 6/1/2017.
 */
public class UserDao {

    List<User_Info> users=new ArrayList<User_Info>();


    public void addtoDb(String Username,User_Info info){

        FirebaseDatabase.getInstance().getReference().child(Username).setValue(info);


    }

    public void retrivefromDb(){

    }

    public void getNearestUser(String s){

    }

}
