package com.example.newfortuneteller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HoroscopeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);

        TextView todaysDate=findViewById(R.id.todayDate);
        String currentDate=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        todaysDate.setText("DATE :- " + currentDate);

        TextView horoscopeText=findViewById(R.id.horoscopeText);

        Intent horo=getIntent();
        String data=horo.getStringExtra("data");
        Log.d("vinshu",data);
        horoscopeText.setText(data);

    }
}