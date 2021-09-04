package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverAccountInfo extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    String name,mail,gender,vehicle,phn;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_account_info);
        t1 = findViewById(R.id.nameid);
        t2 = findViewById(R.id.mailid);
        t3 = findViewById(R.id.phnid);
        t4 = findViewById(R.id.genderid);
        t5 = findViewById(R.id.vinoid);

        toolbar = findViewById(R.id.drivertoolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vehicle Owner Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //adding back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        ///////////////////get data from firebase/////////////////////////
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Driver");
        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mail = dataSnapshot.child("email").getValue().toString();
                name=dataSnapshot.child("fullname").getValue().toString();
                gender=dataSnapshot.child("gender").getValue().toString();
                phn=dataSnapshot.child("phone").getValue().toString();
                vehicle=dataSnapshot.child("vehicle no").getValue().toString();

                t1.setText(name);
                t2.setText(mail);
                t3.setText(phn);
                t4.setText(gender);
                t5.setText(vehicle);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //////////////////////////////////////////


    }
}
