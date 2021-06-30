package com.example.newfortuneteller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Menu extends AppCompatActivity {

    public static TextView zodiacText;
    public static ImageView signImage;
    public static Integer month;
    public static Integer date;
    public static int num;
    public static String signName="";
    public static Button loveCalculatorButton;
    private static final String url="http://horoscope-api.herokuapp.com/horoscope/today/";
    private static String urlSignName="";
    private String total;
    public static Intent menu;
    public static String data="";
    public Boolean flag;
    Horoscope ob=new Horoscope();

    public void kundliFetch(View view){
        Intent intent=new Intent(getApplicationContext(),KundliWebView.class);
        startActivity(intent);
    }

    public void horoscope(View view){

        menu=new Intent(getApplicationContext(),HoroscopeActivity.class);
        Log.d("data in intent",data);
        menu.putExtra("data",data);
        startActivity(menu);

    }

    public void loveCalculator(View view){
        menu=new Intent(getApplicationContext(),LoveCompatability.class);
        startActivity(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        flag=false;

        loveCalculatorButton=findViewById(R.id.loveCalculatorButton);

        zodiacText=findViewById(R.id.zodiacNameText);
        signImage=findViewById(R.id.signImageView);

        menu=getIntent();
        month=menu.getIntExtra("Month",1);
        date=menu.getIntExtra("Date",1);

        if(MainActivity.flag==0){
            loveCalculatorButton.setVisibility(View.GONE);
        }else{
            loveCalculatorButton.setVisibility(View.VISIBLE);
        }

        ob.zodiacSign();

        // Setting Name of Sign

        if(!signName.equals("")) {
            zodiacText.setText("Your zodiac sign is : " + signName);


        }else{
            Toast.makeText(getApplicationContext(),"Enter a valid DOB",Toast.LENGTH_SHORT).show();
            finish();
        }

        // Fetching Data

        total=url+urlSignName;
        networking();

    }


    public void networking(){
        RequestQueue requestQueue;
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, total, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("vinshu","in the on response ");

                try {
                    Log.d("vinshu","putting data in string");
                    data=response.getString("horoscope");
                    Log.d("vinshu","data is put in string");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("vinshu","error occured");
                Toast.makeText(getApplicationContext(), "some error occurred", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    public static class Horoscope extends AppCompatActivity{


        public void zodiacSign(){
            if(date>31){
                signName="";
            }
            else if((month == 3 && date >= 20)||(month==4 && date <= 19))
            {
                signName="Aries";
                urlSignName="Aries";
                signImage.setImageResource(R.drawable.aries2);

            }
            else if((month == 4 && date >= 20)||(month==5 && date <= 20))
            {
                signName="Taurus";
                urlSignName="Taurus";
                signImage.setImageResource(R.drawable.taurus2);

            }
            else if((month == 5 && date >= 21)||(month==6 && date <= 20))
            {
                signName="Gemini";
                urlSignName="Gemini";
                signImage.setImageResource(R.drawable.gemini2);
            }
            else if((month == 6 && date >= 21)||(month==7 && date <= 22))
            {
                signName="Cancer";
                urlSignName="Cancer";
                signImage.setImageResource(R.drawable.cancer2);

            }
            else if((month == 7&& date >= 23)||(month==8 && date <= 22))
            {
                signName="Leo";
                urlSignName="Leo";
                signImage.setImageResource(R.drawable.leo2);

            }
            else if((month == 8&& date >= 23)||(month==9 && date <= 22))
            {
                signName="Virgo";
                urlSignName="Virgo";
                signImage.setImageResource(R.drawable.virgo2);
            }
            else if((month == 9&& date >= 23)||(month==10 && date <= 22))
            {
                signName="Libra";
                urlSignName="Libra";
                signImage.setImageResource(R.drawable.libra2);
            }
            else if((month == 10&& date >= 23)||(month==11 && date <= 21))
            {
                signName="Scorpio";
                urlSignName="Scorpio";
                signImage.setImageResource(R.drawable.scorpio2);
            }
            else if((month == 11&& date >= 22)||(month==12 && date <= 21))
            {
                signName="Saggitarius";
                urlSignName="Saggitarius";
                signImage.setImageResource(R.drawable.sagittarious2);
            }
            else if((month == 12&& date >= 22)||(month==1 && date <= 20))
            {
                signName="Capricorn";
                urlSignName="Capricorn";
                signImage.setImageResource(R.drawable.capricorn2);

            }
            else if((month == 1&& date >= 21)||(month==2 && date <= 18))
            {
                signName="Aquarius";
                urlSignName="Aquarius";
                signImage.setImageResource(R.drawable.aquarius2);

            }
            else if((month == 2&& date >= 19)||(month==3 && date <= 19))
            {
                signName="Pisces";
                urlSignName="Pisces";
                signImage.setImageResource(R.drawable.pisces2);

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.setting : AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                            builder.setMessage("Do you want to show the hidden button")
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            loveCalculatorButton.setVisibility(View.VISIBLE);
                            flag = true;
                        }
                        });
                          builder.show();
                        break;
        }
        return true;
    }



}