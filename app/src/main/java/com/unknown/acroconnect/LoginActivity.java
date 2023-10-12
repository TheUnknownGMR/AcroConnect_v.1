package com.unknown.acroconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://acro-connect-v1-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText userID = findViewById(R.id.etUserid);
        EditText userPW = findViewById(R.id.etUserpw);
        Button loginBtn = findViewById(R.id.btnLogin);
        TextView loginError = findViewById(R.id.tvLoginerror);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userIDtext = userID.getText().toString();
                String userPWtext = userPW.getText().toString();

                if (userIDtext.isEmpty() || userPWtext.isEmpty()) {
                    loginError.setText("ID PASS fields must not be empty.");
                }

                dbref.child("teachers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild(userIDtext)) {
                            String getPasswordFromDB = snapshot.child(userIDtext).child("password").getValue(String.class);
                            if (getPasswordFromDB.equals(userPWtext)) {
                                loginError.setText("Logged in");

                                String getNameFromDB = snapshot.child(userIDtext).child("name").getValue(String.class);
                                String getSubjectFromDB = snapshot.child(userIDtext).child("subject").getValue(String.class);
                                String getEmailFromDB = snapshot.child(userIDtext).child("email").getValue(String.class);

                                SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();

                                editor.putString("name", getNameFromDB);
                                editor.putString("subject", getSubjectFromDB);
                                editor.putString("email", getEmailFromDB);

                                editor.putBoolean("isUserLoggedIn", true);

                                editor.apply();

                                Intent intent = new Intent(LoginActivity.this, SplashScreen.class);
                                startActivity(intent);
                                finish();

                            } else {loginError.setText(String.format("%s%s","Wrong Password. Correct: ", getPasswordFromDB));}
                        } else {loginError.setText("Wrong USERID");}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}