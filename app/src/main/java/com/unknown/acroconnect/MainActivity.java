package com.unknown.acroconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    boolean isMoreOptionFabActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView teachersName = findViewById(R.id.tvName);
        TextView teachersSubject = findViewById(R.id.tvSubject);
        TextView fabAddCanceltxt = findViewById(R.id.fabAddCanceltxt);
        EditText searchbar = findViewById(R.id.etSearchbar);
        Button logoutbtn = findViewById(R.id.logoutbtn);
        ImageView searchbtn = findViewById(R.id.btnSearch);
        FloatingActionButton moreOptionFab = findViewById(R.id.fabMoreOptiondbtn);
        FloatingActionButton addGroupFab = findViewById(R.id.fabAddGroupbtn);
        FloatingActionButton attendanceFab = findViewById(R.id.fabAddAttendancebtn);
        LinearLayoutCompat addGroupLayout = findViewById(R.id.addGroupLayout);
        LinearLayoutCompat attendanceLayout = findViewById(R.id.attendanceLayout);

        Animation rotateClockwise = AnimationUtils.loadAnimation(this, R.anim.clockwise_rotation);
        Animation rotateAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.anit_clockwise_rotation);
        Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottomfab);
        Animation toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottomfab);

        replaceFragment(new HomeFragment());


        String name = Objects.requireNonNull(getIntent().getExtras()).getString("name");
        String email = Objects.requireNonNull(getIntent().getExtras()).getString("email");
        String subject = Objects.requireNonNull(getIntent().getExtras()).getString("subject");
        teachersName.setText(name);
        teachersSubject.setText(subject);


        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.apply();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SearchFragment());
            }
        });

        moreOptionFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMoreOptionFabActive) {
                    closeMoreOptionFab(moreOptionFab,
                            addGroupFab,
                            attendanceFab,
                            addGroupLayout, attendanceLayout,
                            fabAddCanceltxt,
                            rotateAntiClockwise, fromBottom);
                } else {
                    openMoreOptionFab(moreOptionFab,
                            addGroupFab,
                            attendanceFab,
                            addGroupLayout, attendanceLayout,
                            fabAddCanceltxt,
                            rotateClockwise, toBottom);
                }
            }
        });

    }

    private void replaceFragment(androidx.fragment.app.Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framLayout , fragment);
        fragmentTransaction.commit();
    }

    private void closeMoreOptionFab(FloatingActionButton moreOptionfab,
                                    FloatingActionButton addGroupFab,
                                    FloatingActionButton attendanceFab,
                                    LinearLayoutCompat addGroupLayout,
                                    LinearLayoutCompat attendanceLayout,
                                    TextView fabAddCanceltxt,
                                    Animation rotateAntiClockwise, Animation fromBottom) {
        isMoreOptionFabActive = false;
        moreOptionfab.startAnimation(rotateAntiClockwise);
        addGroupLayout.startAnimation(fromBottom);
        attendanceLayout.startAnimation(fromBottom);
        fabAddCanceltxt.startAnimation(fromBottom);
    }
    private void openMoreOptionFab(FloatingActionButton moreOptionfab,
                                   FloatingActionButton addGroupFab,
                                   FloatingActionButton attendanceFab,
                                   LinearLayoutCompat addGroupLayout,
                                   LinearLayoutCompat attendanceLayout,
                                   TextView fabAddCanceltxt,
                                   Animation rotateClockwise, Animation toBottom) {
        isMoreOptionFabActive = true;
        moreOptionfab.startAnimation(rotateClockwise);
        addGroupLayout.startAnimation(toBottom);
        attendanceLayout.startAnimation(toBottom);
        fabAddCanceltxt.startAnimation(toBottom);
    }

}