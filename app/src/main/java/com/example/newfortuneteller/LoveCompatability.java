package com.example.newfortuneteller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LoveCompatability extends AppCompatActivity {

    static String percent="";
    EditText you,bae;
    TextView result;
    MediaPlayer mediaPlayer;
    public static Button upayeButton;
    public static String string="this is the text i want to show in dialogue";

    public void calculate(View view){

        you=(EditText)  findViewById(R.id.yourName);
        bae=(EditText)findViewById(R.id.baeName);
        result=(TextView) findViewById(R.id.percentage);
        String m=you.getText().toString();
        String f=bae.getText().toString();
        String sum=m+f;
        sum=sum.toLowerCase();
        int value=sum.hashCode();

        if(m.equals("")||f.equals("")) {
        }else{
            Random random = new Random(value);
            percent = (random.nextInt(100) + 1) + "";
            result.setText(percent + "%");
            try {
                Integer resultInt = Integer.parseInt(percent);
                if (resultInt > 80) {
                    string="you two are made for each other, keep loving ur partner";
                    mediaPlayer.start();
                }
                else if(resultInt<80&&resultInt>60){
                    string="you have to give ur partner some time and gotta improve your chemistry and you are good to go";
                }
                else if(resultInt<60&&resultInt>40){
                    string="your love is significantly weak try to manage ur professional life and your love life";
                }
                else if(resultInt<40){
                    string="you are on the verge of your divorce, treat your partner nice and take good care of him/her and make it to the end with your partner";
                }
            } catch (Exception e) {
                Toast.makeText(this, "BEEP", Toast.LENGTH_SHORT).show();
            }

            upayeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showUpaye();

                }
            });
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_compatability);
        Intent love=getIntent();
         mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.turulob);
         upayeButton=findViewById(R.id.upayebutton);


    }

    public void showUpaye(){
        upayeDialog upayeDialog=new upayeDialog();
        upayeDialog.show(getSupportFragmentManager(),"nothing");
    }

}