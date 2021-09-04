package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
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

public class RegisterActivity extends AppCompatActivity {


    EditText fullname,email,pass,conpass,phonenumber,vichels;
    RadioGroup radioGroup;
    RadioButton genderans;
    Button registerbutton;
    TextView signinhere;
    boolean ischek = false;

    Switch police;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable;
    ProgressBar progressBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //set gradiant background
        relativeLayout = findViewById(R.id.registerlayid);
        animationDrawable =(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(3000);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();


        fullname = findViewById(R.id.signupnameid);
        email = findViewById(R.id.signupemailid);
        pass = findViewById(R.id.signuppassid);
        conpass = findViewById(R.id.signupconpassid);
        phonenumber = findViewById(R.id.phonenumberid);
        radioGroup = findViewById(R.id.radiogroupid);
        registerbutton = findViewById(R.id.signupid);
        signinhere = findViewById(R.id.signinhereid);
        police = findViewById(R.id.policeverid);
        vichels = findViewById(R.id.vichecleid);

        firebaseAuth = FirebaseAuth.getInstance();

        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);


        signinhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        police.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    ischek = true;
                    vichels.setVisibility(View.INVISIBLE);


                }
                else
                {
                    vichels.setVisibility(View.VISIBLE);
                }

            }
        });





        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                //get gender
                int genderid = radioGroup.getCheckedRadioButtonId();
                genderans = findViewById(genderid);

                String gender = genderans.getText().toString();
                String fulname = fullname.getText().toString();
                String emailid = email.getText().toString().trim();
                String password = pass.getText().toString();
                String conpassword = conpass.getText().toString();
                String phn = phonenumber.getText().toString();
                String viechelsno = vichels.getText().toString();


                if(emailid.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    email.setError("Enter an email address"); //print message in email edit text
                    email.requestFocus(); //cursore is go error location
                    return;

                }
                else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailid).matches())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    email.setError("Enter a valid email address"); //if not match the email validity
                    email.requestFocus(); //cursore is go error location
                    return;

                }
                else if(fulname.isEmpty())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    fullname.setError("Enter Full Name"); //print message in email edit text
                    fullname.requestFocus(); //cursore is go error location
                    return;

                }

                else if(password.isEmpty())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    pass.setError("Password required"); //print message in email edit text
                    pass.requestFocus(); //cursore is go error location
                    return;

                }
                else if(conpassword.isEmpty())
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    conpass.setError("Confirm Password required"); //print message in email edit text
                    conpass.requestFocus(); //cursore is go error location
                    return;

                }
                else if(!password.equals(conpassword))
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(RegisterActivity.this,"both password field not match",Toast.LENGTH_LONG).show();

                }
                else if(password.length() < 6)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(RegisterActivity.this,"Password length atleast 6 character",Toast.LENGTH_LONG).show();

                }
                else if(ischek == true)
                {
                    progressBar.setVisibility(View.VISIBLE);
                    register(fulname,emailid,password,phn,gender);

                }
                else
                {

                    if(viechelsno.isEmpty())
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        vichels.setError("vichels is Required");
                        vichels.requestFocus(); //cursore is go error location
                        return;

                    }
                    else if(viechelsno.length() != 6)
                    {
                        progressBar.setVisibility(View.INVISIBLE);
                        vichels.setError("vichels no length can not be less then 6");
                        vichels.requestFocus(); //cursore is go error location
                        return;

                    }
                    else
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        register(fulname,emailid,password,phn,gender,viechelsno);

                    }




                }





            }
        });









    }

    private void register(final String fulname, final String emailid, final String password, final String phn, final String gender, final String viechelsno) {
        firebaseAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //
                firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            databaseReference1 = FirebaseDatabase.getInstance().getReference("Driver").child(userid);

                            HashMap<String , String> hashMap = new HashMap<>();

                            hashMap.put("fullname",fulname);
                            hashMap.put("email",emailid);
                            hashMap.put("pass",password);
                            hashMap.put("gender",gender);
                            hashMap.put("phone",phn);
                            hashMap.put("vehicle no",viechelsno);
                            hashMap.put("userid",userid);

                            databaseReference1.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(getApplicationContext(),"You registered successfully..verify email before signin",Toast.LENGTH_LONG).show();

                                    }
                                    else
                                    {
                                        progressBar.setVisibility(View.INVISIBLE);
                                        Toast.makeText(RegisterActivity.this,"Something wrong in Database",Toast.LENGTH_LONG).show();
                                    }

                                }
                            });




                        }

                    }
                });


            }
        });

    }

    private void register(final String fullname, final String email, final String pass, final String phone, final String gender)
    {
        progressBar.setVisibility(View.VISIBLE);

        final String e = email;
        databaseReference2 = FirebaseDatabase.getInstance().getReference("PoliceValidEmail");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean policeverifiedemail = false;
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    if(dataSnapshot1.child("email").getValue().equals(e))
                    {
                        policeverifiedemail = true;

                    }

                }

                if(policeverifiedemail == false)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"This Email is not for police",Toast.LENGTH_LONG).show();

                }
                else
                {
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                        assert firebaseUser != null;
                                        String userid = firebaseUser.getUid();

                                        databaseReference2 = FirebaseDatabase.getInstance().getReference("Police").child(userid);

                                        HashMap<String , String> hashMap = new HashMap<>();

                                        hashMap.put("fullname",fullname);
                                        hashMap.put("email",email);
                                        hashMap.put("pass",pass);
                                        hashMap.put("gender",gender);
                                        hashMap.put("phone",phone);
                                        hashMap.put("userid",userid);
                                        hashMap.put("vehicle no","null");

                                        databaseReference2.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                    Toast.makeText(getApplicationContext(),"You registered successfully..verify email before signin",Toast.LENGTH_LONG).show();

                                                }
                                                else
                                                {
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(RegisterActivity.this,"Something wrong in Database",Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        });

                                    }

                                }
                            });

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}


/*
if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userid = firebaseUser.getUid();

                    databaseReference2 = FirebaseDatabase.getInstance().getReference("Police").child(userid);

                    HashMap<String , String> hashMap = new HashMap<>();

                    hashMap.put("fullname",fullname);
                    hashMap.put("email",email);
                    hashMap.put("pass",pass);
                    hashMap.put("gender",gender);
                    hashMap.put("phone",phone);
                    hashMap.put("userid",userid);
                    hashMap.put("vehicle no","null");

                    databaseReference2.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(RegisterActivity.this,Wellcome.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                Toast.makeText(RegisterActivity.this,"Something wrong",Toast.LENGTH_LONG).show();
                            }

                        }
                    });



                }

 */
