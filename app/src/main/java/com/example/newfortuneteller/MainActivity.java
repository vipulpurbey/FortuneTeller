package com.example.newfortuneteller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.Executor;


public class MainActivity extends AppCompatActivity {

    EditText month;
    EditText date;
    public static Integer flag=1;
    public static String passwordToEnter="vinshu";
    androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    androidx.biometric.BiometricPrompt biometricPrompt;

    public void hideBut(View view){
        if(flag==1) {
            flag = 0;
            Toast.makeText(this, "HIDDEN", Toast.LENGTH_SHORT).show();
        }else{
            flag=1;
            Toast.makeText(this, "VISIBLE", Toast.LENGTH_SHORT).show();
        }
    }

    public void passDetails(View view) {

            if(month.getText().toString().equals("")||date.getText().toString().equals("")){
                Toast.makeText(this, "Please enter your details", Toast.LENGTH_SHORT).show();
            }else {
                Intent main = new Intent(getApplicationContext(), Menu.class);
                int monthInt = Integer.parseInt(month.getText().toString());
                int dateInt = Integer.parseInt(date.getText().toString());
                main.putExtra("Month", monthInt);
                main.putExtra("Date", dateInt);

                startActivity(main);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        fingerPrintScan();
        biometricPrompt.authenticate(promptInfo);
//        fingerPrintCheck();


        month = (EditText) findViewById(R.id.monthTextNumber);
        date = (EditText) findViewById(R.id.birthDate);
    }


    // TO CLEAR THE FIELDS

    @Override
    protected void onPostResume() {
        super.onPostResume();
        month.setText(null);
        date.setText(null);
    }

    public void fingerPrintScan(){
        BiometricManager biometricManager=BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){

            case BiometricManager.BIOMETRIC_SUCCESS:break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE :Toast.makeText(this,"Your FingerPrint Scanner is not available"
                    ,Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE : Toast.makeText(this,"your device don't have a fingerprint scanner"
                    ,Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED : Toast.makeText(this,"Please save your fingerprint in your device"
                    ,Toast.LENGTH_SHORT).show();
                break;
        }
        if(BiometricManager.BIOMETRIC_SUCCESS==0) {
            Executor executor = ContextCompat.getMainExecutor(this);
            biometricPrompt = new androidx.biometric.BiometricPrompt(MainActivity.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(MainActivity.this, "there is an error ", Toast.LENGTH_SHORT).show();
                    finishAndRemoveTask();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull androidx.biometric.BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    Toast.makeText(MainActivity.this, "Authentication success", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    finishAndRemoveTask();
                }
            });
            promptInfo = new androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                    .setTitle("enter inside")
                    .setDescription("put your finger on the fingerPrint scanner")
                    .setNegativeButtonText("cancel")
                    .build();
        }else{
            entryDialog();
        }
    }

    public void entryDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("authentication");
        final EditText input = new EditText(MainActivity.this);

        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(input.getText().toString().equals(passwordToEnter)){

                }else{
                    finishAndRemoveTask();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAndRemoveTask();
                dialog.cancel();
            }
        });

        builder.show();
    }

}