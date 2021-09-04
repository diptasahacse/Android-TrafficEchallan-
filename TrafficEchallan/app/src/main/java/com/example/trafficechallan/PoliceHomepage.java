package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;

public class PoliceHomepage extends AppCompatActivity {

    ViewFlipper viewFlipper;
    Toolbar toolbar;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police_homepage);
        viewFlipper = findViewById(R.id.viewflipperid);
        linearLayout = findViewById(R.id.policehomepageactivityid);
        int sliderimage[] = {R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5};




        /////////////tool bar///////////////
        toolbar = findViewById(R.id.policehomepagetoolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Police Home Page");


        for(int image : sliderimage)
        {
            flipper(image);

        }
    }



    public void flipper(int image)
    {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);


        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);


        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    public void gotoActivity(View view) {

        if(view.getId() == R.id.goticketid)
        {
            Intent intent = new Intent(PoliceHomepage.this,Wellcome.class);
            startActivity(intent);

        }
        if(view.getId() == R.id.checklicenceid)
        {
            Intent intent = new Intent(PoliceHomepage.this,CheckLicense.class);
            startActivity(intent);

        }
        if(view.getId() == R.id.announcementid)
        {
            Intent intent = new Intent(PoliceHomepage.this,Announcement.class);
            startActivity(intent);

        }
    }


    //menu and listener
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menultem,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logoutid)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
    }
}
