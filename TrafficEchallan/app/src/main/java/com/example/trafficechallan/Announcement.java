package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Announcement extends AppCompatActivity {
    TextView t1,t2,t3,t4;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
        t1 = findViewById(R.id.ocuranceid);
        t2 = findViewById(R.id.stateid);
        t3 = findViewById(R.id.locationid);
        t4 = findViewById(R.id.roadid);


        toolbar = findViewById(R.id.announceenttoolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Announcement");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //adding back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Announcement");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    t1.setText(dataSnapshot.child("ocurance").getValue().toString());
                    t2.setText(dataSnapshot.child("state").getValue().toString());
                    t3.setText(dataSnapshot.child("location").getValue().toString());
                    t4.setText(dataSnapshot.child("road").getValue().toString());

                }
                else
                {
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
