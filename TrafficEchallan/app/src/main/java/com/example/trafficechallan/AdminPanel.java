package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminPanel extends AppCompatActivity {

    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;

    DatabaseReference databaseReference3;
    DatabaseReference databaseReference4;
    boolean policereg = false;
    boolean emailpre = false;

    boolean policeemailpresentindriveremailglobal = false;


    boolean licpre = false;
    boolean emaipreeeee = false;

    EditText o1,o2,o3,o4;






    boolean driverlice = false;
    boolean driverlicepresent = false;


    EditText e2,e3,e4;
    Button b1,b2;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        e2 = findViewById(R.id.policeregemailid);
        b1 = findViewById(R.id.uploadthedatabaseid);

        e3 = findViewById(R.id.driverlicenseid);
        e4 = findViewById(R.id.driverregemailid);
        b2 = findViewById(R.id.uploaddriverdatabaseid);

        o1 = findViewById(R.id.ocurancenameid);
        o2 = findViewById(R.id.statenameid);
        o3 = findViewById(R.id.locationnameid);
        o4 = findViewById(R.id.roadnoid);


        toolbar = findViewById(R.id.policehomepagetoolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin Panel");






        /////////// for police
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check that email is already store in driver or not

                DatabaseReference databaseReference12 = FirebaseDatabase.getInstance().getReference("Driver");
                databaseReference12.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean policeemailstoreindriver = false;

                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                        {
                            if(dataSnapshot1.child("email").getValue().equals(e2.getText().toString()))
                            {
                                policeemailstoreindriver = true;



                            }

                        }

                        if(policeemailstoreindriver)
                        {
                            showToast("This email already store in driver database");
                        }
                        else
                        {
                            DatabaseReference databaseReference4 = FirebaseDatabase.getInstance().getReference("PoliceValidEmail");
                            databaseReference4.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    boolean alreadystoreemail = false;

                                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                    {
                                        if(dataSnapshot1.child("email").getValue().equals(e2.getText().toString()))
                                        {
                                            alreadystoreemail = true;


                                        }
                                    }
                                    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(e2.getText()).matches())
                                    {
                                        showToast("input valid email");
                                    }
                                    if(e2.getText().toString().equals(""))
                                    {
                                        showToast("input valid email");
                                    }
                                    else if(!alreadystoreemail)
                                    {
                                        DatabaseReference databaseReference5 = FirebaseDatabase.getInstance().getReference("PoliceValidEmail");
                                        HashMap<String,String> hashMap = new HashMap<>();
                                        hashMap.put("email",e2.getText().toString());
                                        databaseReference5.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    showToast("This Email has been uploaded");
                                                    e2.setText("");
                                                }

                                            }
                                        });
                                    }
                                    else
                                    {
                                        showToast("Already store");
                                    }


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });




        //for driver


        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                driverlice = false;
                driverlicepresent = false;

                final String emails = e4.getText().toString();
                final String driverlicenses = e3.getText().toString();


                //check all ready police email valid or not

                if(e3.getText().toString().length() != 8)
                {
                    showToast("This License Number should be 8 digits");


                }
                else
                {
                    CheckEmailisMatchinDriverDatabase(e4.getText().toString(),e3.getText().toString());


                }







            }
        });


    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }

    private void CheckEmailisMatchinDriverDatabase(final String email, final String li) {

        emailpre = false;


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Driver");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.child("email").getValue().toString().equals(email))
                    {

                        emailpre = true;


                    }

                }

                if(emailpre == true)
                {

                    CheckEmailAlreadyHavinginDriver_License_NoDatabase(email,li);

                }
                else
                {
                    showToast(email+" is not present in driver database");
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void CheckEmailAlreadyHavinginDriver_License_NoDatabase(final String email, final String li) {
        licpre = false;
        emaipreeeee = false;



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Driver License No");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.child("licenseno").getValue().toString().equals(li))
                    {

                        licpre = true;


                    }
                    if(dataSnapshot1.child("email").getValue().toString().equals(email))
                    {

                        emaipreeeee = true;


                    }

                }

                if(licpre)
                {
                    showToast("This License no is match for other driver license no..please input correctly");

                }
                else if(emaipreeeee)
                {
                    showToast("This email's license is already registered");
                }
                if(licpre == false && emaipreeeee == false)
                {

                    databaseReference3 = FirebaseDatabase.getInstance().getReference("Driver License No");
                    HashMap<String , String> hashMap = new HashMap<>();
                    hashMap.put("email",email);
                    hashMap.put("licenseno",li);


                    databaseReference3.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                showToast("Data Uploaded");

                            }
                            else
                            {
                                showToast("Something went wrong");

                            }

                        }
                    });


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    //menu and listener
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.adminmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.adminlogoutid)
        {

            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();

        }
        if(item.getItemId() == R.id.changepasswordid)
        {
            Intent intent = new Intent(getApplicationContext(),AdminChangePasswordActivity.class);
            startActivity(intent);


        }
        return true;
    }

    public void Ocuranceupdate(View view) {
        if(o1.getText().toString().isEmpty() || o2.getText().toString().isEmpty() || o3.getText().toString().isEmpty()  || o4.getText().toString().isEmpty() )
        {
            showToast("Fill All The Field");

        }
        else
        {
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("ocurance",o1.getText().toString());
            hashMap.put("state",o2.getText().toString());
            hashMap.put("location",o3.getText().toString());
            hashMap.put("road",o4.getText().toString());

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Announcement");
            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        showToast("Announcement Updated");
                    }

                }
            });
        }


    }
}


/*
 databaseReference4 = FirebaseDatabase.getInstance().getReference("Driver License No");

                    databaseReference4.addValueEventListener(new ValueEventListener() {


                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if(dataSnapshot.exists())
                                {

                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                    {


                                        if(dataSnapshot1.child("email").getValue().equals(emails))
                                        {

                                            driverlice = true;




                                        }
                                        if(dataSnapshot1.child("licenseno").getValue().equals(driverlicenses))
                                        {

                                            driverlicepresent = true;





                                        }


                                    }




                                }

                            if(driverlice)
                            {
                                Toast.makeText(getApplicationContext(),"This License Email allready exist",Toast.LENGTH_LONG).show();


                            }
                            if(driverlicepresent)
                            {
                                Toast.makeText(getApplicationContext(),"This License is same as to other driver",Toast.LENGTH_LONG).show();

                            }

                            else
                            {
                                databaseReference3 = FirebaseDatabase.getInstance().getReference("Driver License No");
                                HashMap<String , String> hashMap = new HashMap<>();
                                hashMap.put("email",emails);
                                hashMap.put("licenseno",driverlicenses);


                                databaseReference3.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(getApplicationContext(),"Data Uploaded",Toast.LENGTH_LONG).show();

                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

                                        }

                                    }
                                });




                            }

                            //









                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });




 */
