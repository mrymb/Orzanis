package com.example.maryambaig.orzanis.Dao;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.maryambaig.orzanis.Entity.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MaryamBaig on 6/3/2017.
 */
public class ContactDao {

    ContentResolver contentResolver=getContentResolver();
    /*Cursor cursor_android_contacts=contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,null,
            null,null,null);


    public List<Contact> getContactList()
    {
        List<Contact>list=new ArrayList<Contact>();
        if(cursor_android_contacts.getCount()>0)
        {
            while(cursor_android_contacts.moveToNext())
            {
                Contact contact=new Contact();
                //String name=cursor_android_contacts.getString(cursor_android_contacts.getColumnIndex(ContactsContract.DISPLAY_NAME));
            }
        }


        return list;
    }*/

    public ContentResolver getContentResolver() {
        return contentResolver;
    }


    public void getDetails() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    Log.d("halo","halo");
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.d("new number", "getDetails: " + phoneNo);
                    }
                    pCur.close();
                }
            }
        }
    }}
