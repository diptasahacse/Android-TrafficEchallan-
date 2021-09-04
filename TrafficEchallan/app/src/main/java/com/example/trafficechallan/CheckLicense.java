package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckLicense extends AppCompatActivity {
    ImageButton searchbutton;
    EditText enterlicense;
    RecyclerView recyclerView;
    List<CaseModel> caselists;
    String  uid;
    DriverCaseAdapter driverCaseAdapter;

    Toolbar toolbar;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_license);
        searchbutton = findViewById(R.id.searchbuttonid);
        enterlicense = findViewById(R.id.enterlicenseid);
        toolbar = findViewById(R.id.checklicensetoolbarid);


        //progressbar
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);

        Sprite doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Check License");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //adding back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





        recyclerView = findViewById(R.id.driverchecklicenserecyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        caselists = new ArrayList<>();

        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                DatabaseReference casedatabasereferrence = FirebaseDatabase.getInstance().getReference("Driver License No");
                casedatabasereferrence.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String temp = "Licence not Found" ;

                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            if(dataSnapshot1.child("licenseno").getValue().toString().equals(enterlicense.getText().toString()))
                            {
                                String driveremail = dataSnapshot1.child("email").getValue().toString();
                                temp = new String(driveremail);
                                showLicenseAllCase(driveremail);
                                recyclerView.setVisibility(View.VISIBLE);
                                //Toast.makeText(getApplicationContext(),driveremail,Toast.LENGTH_LONG).show();

                            }




                        }
                        if(temp.equals("Licence not Found"))
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            caselists.clear();
                            driverCaseAdapter = new DriverCaseAdapter(CheckLicense.this,caselists);
                            recyclerView.setAdapter(driverCaseAdapter);

                            Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }





            private void showLicenseAllCase(final String driveremail) {
                Toast.makeText(getApplicationContext(),driveremail,Toast.LENGTH_LONG).show();
                if(driveremail.length() > 3)
                {
                    DatabaseReference showLicenseref = FirebaseDatabase.getInstance().getReference("Driver");
                    showLicenseref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                if(dataSnapshot1.child("email").getValue().equals(driveremail))
                                {
                                    uid = dataSnapshot1.child("userid").getValue().toString();
                                    showLicensespecificCase(uid);
                                    //Toast.makeText(getApplicationContext(),uid,Toast.LENGTH_LONG).show();



                                }




                            }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }







            }



            private void showLicensespecificCase(final String uid) {

                DatabaseReference showspecificData = FirebaseDatabase.getInstance().getReference("Driver Case File");


                showspecificData.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                        if(dataSnapshot.exists())
                        {

                            caselists.clear();
                            for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                            {
                                if(dataSnapshot1.child("userid").getValue().equals(uid))
                                {

                                    CaseModel caseModel = dataSnapshot1.getValue(CaseModel.class);
                                    caselists.add(caseModel);


                                }




                            }
                            progressBar.setVisibility(View.INVISIBLE);
                            driverCaseAdapter = new DriverCaseAdapter(CheckLicense.this,caselists);
                            recyclerView.setAdapter(driverCaseAdapter);



                        }







                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });












            }




        });








    }
}
