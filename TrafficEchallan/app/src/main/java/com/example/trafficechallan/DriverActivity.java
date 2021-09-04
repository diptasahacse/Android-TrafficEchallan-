package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DriverActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;

    DriverCaseAdapter driverCaseAdapter;
    List<CaseModel> caselists;



    TextView hello;
    String name,mail,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);


        toolbar = findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //recyycler view
        recyclerView = findViewById(R.id.driverrecyclerviewid);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        caselists = new ArrayList<>();














//get case all case from firebase

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = fireUser.getUid();
        DatabaseReference casedatabasereferrence = FirebaseDatabase.getInstance().getReference("Driver Case File");
        casedatabasereferrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    caselists.clear();
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        if(dataSnapshot1.child("userid").getValue().equals(userid))
                        {

                            CaseModel caseModel = dataSnapshot1.getValue(CaseModel.class);
                            caselists.add(caseModel);

                        }




                    }
                    driverCaseAdapter = new DriverCaseAdapter(DriverActivity.this,caselists);
                    recyclerView.setAdapter(driverCaseAdapter);
                }
                else
                {
                    Toast.makeText(DriverActivity.this,"Record Not Found",Toast.LENGTH_LONG).show();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        hello = findViewById(R.id.hellouserid);
        ///////////////////get data from firebase/////////////////////////
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Driver");
        reference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                gender=dataSnapshot.child("gender").getValue().toString();
                name = dataSnapshot.child("fullname").getValue().toString();
                mail = dataSnapshot.child("email").getValue().toString();

                if(gender.equals("Male"))
                {
                    hello.setText("Wellcome Mr. "+name);

                }
                else
                {
                    hello.setText("Wellcome Mis. "+name);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //////////////////////////////////////////




        //check driving license is uploded or not
        DatabaseReference licensedatabase = FirebaseDatabase.getInstance().getReference("Driver License No");
        licensedatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean licenseupload = false;

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.child("email").getValue().toString().equals(mail))
                    {
                        licenseupload = true;



                    }





                }

                if(licenseupload == false)
                {
                    hello.setTextColor(Color.RED);
                    Toast.makeText(getApplicationContext(),"Driving License is not uploaded,goto admin,and upload by himself",Toast.LENGTH_LONG).show();
                    recyclerView.setVisibility(View.INVISIBLE);

                    /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Caution");
                    builder.setIcon(R.drawable.ic_block_black_24dp);
                    builder.setMessage("Your Driving License is not uploaded,Thats why you can not use your account");
                    builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.create().show();

                     */

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });











    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drivermenuitem,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.driverlogoutid)
        {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }
        if(item.getItemId() == R.id.driverinfoid)
        {

            Intent intent = new Intent(getApplicationContext(),DriverAccountInfo.class);
            startActivity(intent);


        }



        return true;
    }








}
