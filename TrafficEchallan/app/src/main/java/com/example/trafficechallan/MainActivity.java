package com.example.trafficechallan;

import androidx.annotation.IntegerRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    CircularImageView circularImageView;
    EditText email,pass;
    TextView newregherebutton;
    Button signin;
    FirebaseAuth fireauth;
    boolean adminemail = false;
    boolean adminpass = false;

    boolean tempdriveristhere = false;
    boolean temppoliceisthere = false;
    boolean policeisthere = false;
    boolean useristhere = false;

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;


    LinearLayout linearLayout;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            linearLayout.setVisibility(View.VISIBLE);
            circularImageView.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.middle);



        //show after few munites
        handler.postDelayed(runnable,3000);




        //progressbar
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        //....................../////////////////////////////////////////





        //set gradiant background
        relativeLayout = findViewById(R.id.signinlayoutid);
        animationDrawable =(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
        //////////////////////////////////

        //circle image
        circularImageView = findViewById(R.id.circularImageView);
        // Set Circle color for transparent image
        circularImageView.setCircleColor(Color.WHITE);
        // Set Border
        circularImageView.setBorderColor(Color.RED);
        circularImageView.setBorderWidth(10);
        // Add Shadow with default param
        circularImageView.setShadowEnable(true);
        ///......................................./////

        //check user already logged in or not


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null)
        {
            String uid = firebaseUser.getUid();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Driver").child(uid);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists() && fireauth.getCurrentUser().isEmailVerified())
                    {



                        Intent intent  = new Intent(MainActivity.this,DriverActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        if(fireauth.getCurrentUser().isEmailVerified())
                        {
                            Intent intent  = new Intent(MainActivity.this,PoliceHomepage.class);
                            startActivity(intent);
                            finish();

                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




            //


        }








        //.................................///////////

        //find all view
        email = findViewById(R.id.signinemailid);
        pass = findViewById(R.id.signinpassid);
        newregherebutton = findViewById(R.id.newreghereid);
        signin = findViewById(R.id.signinid);
        fireauth = FirebaseAuth.getInstance();

        //signin button listener
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                final String emailid = email.getText().toString();
                final String password = pass.getText().toString();






                if(emailid.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"All Field are mendatory",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);

                }
                else
                {

                    DatabaseReference admindatabaseref;
                    admindatabaseref = FirebaseDatabase.getInstance().getReference("Admin");
                    admindatabaseref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.child("email").getValue().toString().equals(emailid) && dataSnapshot.child("pass").getValue().toString().equals(password))
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent intent  = new Intent(MainActivity.this,AdminPanel.class);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                PoliceDriverCheck(emailid,password);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });






                }

            }

            private void PoliceDriverCheck(final String emailid, final String password) {

                progressBar.setVisibility(View.VISIBLE);
                fireauth.signInWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            if(fireauth.getCurrentUser().isEmailVerified())
                            {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Driver");
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        boolean emailisthere = false;

                                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                        {
                                            String eemmaaiill = String.valueOf(dataSnapshot1.child("email").getValue());
                                            if(eemmaaiill.equals(emailid))
                                            {
                                                emailisthere = true;


                                            }



                                        }


                                        if(emailisthere)
                                        {
                                            FirebaseUser user = fireauth.getCurrentUser();
                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Driver").child(user.getUid());

                                            databaseReference.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String fullname,email,gender,phone,vehicleno,userid;
                                                    fullname =  dataSnapshot.child("fullname").getValue().toString();
                                                    email =  dataSnapshot.child("email").getValue().toString();
                                                    gender =  dataSnapshot.child("gender").getValue().toString();
                                                    phone =  dataSnapshot.child("phone").getValue().toString();
                                                    vehicleno =  dataSnapshot.child("vehicle no").getValue().toString();
                                                    userid =  dataSnapshot.child("userid").getValue().toString();


                                                    HashMap<String , String> hashMap = new HashMap<>();
                                                    hashMap.put("fullname",fullname);
                                                    hashMap.put("email",email);
                                                    hashMap.put("pass",password);
                                                    hashMap.put("gender",gender);
                                                    hashMap.put("phone",phone);
                                                    hashMap.put("vehicle no",vehicleno);
                                                    hashMap.put("userid",userid);

                                                    DatabaseReference databaseReference111 = FirebaseDatabase.getInstance().getReference("Driver").child(userid);
                                                    databaseReference111.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful())
                                                            {
                                                                progressBar.setVisibility(View.INVISIBLE);
                                                                Intent intent  = new Intent(MainActivity.this,DriverActivity.class);
                                                                startActivity(intent);
                                                                finish();

                                                            }


                                                        }
                                                    });




                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                                }
                                            });












                                        }

                                        else
                                        {


                                            FirebaseUser user = fireauth.getCurrentUser();
                                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Police").child(user.getUid());

                                            databaseReference.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    String fullname,email,gender,phone,vehicleno,userid;
                                                    fullname =  dataSnapshot.child("fullname").getValue().toString();
                                                    email =  dataSnapshot.child("email").getValue().toString();
                                                    gender =  dataSnapshot.child("gender").getValue().toString();
                                                    phone =  dataSnapshot.child("phone").getValue().toString();
                                                    vehicleno =  dataSnapshot.child("vehicle no").getValue().toString();
                                                    userid =  dataSnapshot.child("userid").getValue().toString();


                                                    HashMap<String , String> hashMap = new HashMap<>();
                                                    hashMap.put("fullname",fullname);
                                                    hashMap.put("email",email);
                                                    hashMap.put("pass",password);
                                                    hashMap.put("gender",gender);
                                                    hashMap.put("phone",phone);
                                                    hashMap.put("userid",userid);
                                                    hashMap.put("vehicle no",vehicleno);

                                                    DatabaseReference databaseReference111 = FirebaseDatabase.getInstance().getReference("Police").child(userid);
                                                    databaseReference111.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful())
                                                            {
                                                                progressBar.setVisibility(View.INVISIBLE);
                                                                Intent intent  = new Intent(MainActivity.this,PoliceHomepage.class);
                                                                startActivity(intent);
                                                                finish();

                                                            }


                                                        }
                                                    });




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
                            else
                            {
                                Toast.makeText(MainActivity.this,"Email Not Verify yet",Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.INVISIBLE);
                            }

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Email or password not match",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });





            }
        });
        //newregherebutton button listener
        newregherebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);



            }
        });




    }

    public void gotoForgotPasswordActivity(View view) {

        Intent intent = new Intent(getApplicationContext(),ForgetPasswordActivity.class);
        startActivity(intent);



    }
}



                        /*

                        if(task.isSuccessful())
                        {

                            ///////
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Driver");

                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    boolean emailisthere = false;

                                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                                    {
                                        String eemmaaiill = String.valueOf(dataSnapshot1.child("email").getValue());
                                        if(eemmaaiill.equals(emailid))
                                        {
                                            emailisthere = true;


                                        }



                                    }


                                    if(emailisthere)
                                    {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Intent intent  = new Intent(MainActivity.this,DriverActivity.class);
                                        startActivity(intent);
                                        finish();


                                    }

                                    else
                                    {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Intent intent  = new Intent(MainActivity.this,PoliceHomepage.class);
                                        startActivity(intent);
                                        finish();

                                    }



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                        else
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(MainActivity.this,"Email or password incorrect",Toast.LENGTH_LONG).show();
                        }

                       */
//
