package com.unknown.acroconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        boolean isUserLoggedIn = prefs.getBoolean("isUserLoggedIn", false);

        if (isUserLoggedIn) {
            String name = prefs.getString("name", "Username");
            String subject = prefs.getString("subject", "Subject");
            String email = prefs.getString("email", "email");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("subject", subject);
                    intent.putExtra("email", email);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }

        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }


/*
        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        boolean isUserLoggedIn = prefs.getBoolean("isUserLoggedIn", false);

        if (isUserLoggedIn) {
            String name = prefs.getString("isUserLoggedIn", "Username");
            String subject = prefs.getString("isUserLoggedIn", "Subject");
            String email = prefs.getString("isUserLoggedIn", "email");

            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("subject", subject);
            intent.putExtra("email", email);
            startActivity(intent);
            finish();
        }

        else {
            Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
            startActivity(intent);
            finish(); }*/

/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
                boolean isUserLoggedIn = prefs.getBoolean("isUserLoggedIn", false);

                if (isUserLoggedIn) {
                    String name = prefs.getString("isUserLoggedIn", "Username");
                    String subject = prefs.getString("isUserLoggedIn", "Subject");
                    String email = prefs.getString("isUserLoggedIn", "email");

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
*/
        /*                    intent.putExtra("name", name);
                    intent.putExtra("subject", subject);
                    intent.putExtra("email", email);*/
        /*
                    startActivity(intent);
                    finish();
                }

                else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);*/
    }
}