package com.example.maryambaig.orzanis.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;

import com.example.maryambaig.orzanis.BO;
import com.example.maryambaig.orzanis.Entity.User_Info;
import com.example.maryambaig.orzanis.R;


import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

   BO bo=new BO();
    Button Highbtn;
    Button Medbtn;
    Button LowBtn;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Highbtn=(Button)findViewById(R.id.highbtn);
        Medbtn=(Button)findViewById(R.id.mediumbtn);
        LowBtn=(Button)findViewById(R.id.lowbtn);

        Highbtn.setBackgroundColor(Color.RED);
        Medbtn.setBackgroundColor(Color.BLUE);
        LowBtn.setBackgroundColor(Color.GRAY);

        final SmsManager smsManager=SmsManager.getDefault();
        final User_Info user_info=new User_Info();


        double latitude= bo.getUserLocation("maryam",MainActivity.this).getLatitude();
        double longitude=bo.getUserLocation("maryam",MainActivity.this).getLongitude();
        user_info.setMobile("+923315191992");


        String username=bo.getUsername(getApplicationContext());
        bo.AddUser(username);
        try {
             message=bo.getAddressFromLocation(latitude,longitude,getApplicationContext(),Locale.getDefault());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Highbtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                //Call High emergency numbers
                smsManager.sendTextMessage(user_info.getMobile(),null,"High level, help required at "+message,null,null);

            }
        });

        Medbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsManager.sendTextMessage(user_info.getMobile(),null,"Medium level, help required at "+message,null,null);
            }
        });

        LowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsManager.sendTextMessage(user_info.getMobile(),null,"Low level, help required at "+message,null,null);
            }
        });

}
}
