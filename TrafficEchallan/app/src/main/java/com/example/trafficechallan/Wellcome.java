package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;




public class Wellcome extends AppCompatActivity {
    Spinner spinner;
    String[] vehiclestypeslist;
    TwoFragment fragment2;
    FourFragment fragment4;
    TextView TotalAmount;
    EditText phnno,vehicle,emailaddress,comment;
    DatabaseReference databaseReference;
    HashMap<String,String> driverFinalcaseData = new HashMap<>();

    int caseamount;
    String casename = "";
    ProgressBar progressBar;


    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        TotalAmount = findViewById(R.id.totalamountid);

        phnno = findViewById(R.id.wellcomeacphnid);
        vehicle = findViewById(R.id.wellcomeacvehicleid);
        emailaddress = findViewById(R.id.wellcomeacemailid);
        comment = findViewById(R.id.wellcomeaccommentid);

        //progressbar
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new DoubleBounce(); // RotatingPlane,DoubleBounce,Wave,WanderingCubes,Pulse,ChasingDots,ThreeBounce,Circle,CubeGrid,FadingCircle,FoldingCube,RotatingCircle
        progressBar.setIndeterminateDrawable(doubleBounce);


        /////////////tool bar///////////////
        toolbar = findViewById(R.id.wellcometoolbarid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //adding back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });







        spinner = findViewById(R.id.vehiclelistid);

        vehiclestypeslist = getResources().getStringArray(R.array.vehicletype);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.samplevehiclestypeview,R.id.sampletextid,vehiclestypeslist);
        spinner.setAdapter(adapter);

        //fragment
        fragment2 = new TwoFragment();
        fragment4 = new FourFragment();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                    {
                        case 0:
                            setFragment(fragment2);

                            break;

                        case 1:
                            setFragment(fragment4);

                            break;
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }



    public void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayoutid,fragment);
        fragmentTransaction.commit();
    }


    public void receivedatafromtwo(String  casenameeee, int caseeamount) {
        casename = casenameeee;
        caseamount = caseeamount;
        TotalAmount.setText("Total Amount : "+caseeamount+" INR");




    }

    public void receivedatafromfour(String  casenameeee, int caseeamount) {
        casename = casenameeee;
        caseamount = caseeamount;
        TotalAmount.setText("Total Amount : "+caseeamount+" INR");




    }




    public void submit(View view) {
        progressBar.setVisibility(View.VISIBLE);


        if(phnno.getText().toString() != null && vehicle.getText().toString() != null && emailaddress.getText().toString() != null)
        {


            uploadTheFirebase(phnno.getText().toString(),vehicle.getText().toString(),emailaddress.getText().toString());


        }
        else
        {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

        }


    }

    private void uploadTheFirebase(final String phn, final String vehi, final String email) {
        //check email is valid in firebase or not
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Driver");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean emailmatch = false;
                boolean phnmatch = false;
                boolean vehiclematch = false;



                String driveruid = null;

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    String eemmaaiill = String.valueOf(dataSnapshot1.child("email").getValue());
                    String pphhnn = String.valueOf(dataSnapshot1.child("phone").getValue());
                    String vviiee = String.valueOf(dataSnapshot1.child("vehicle no").getValue());


                    String uid = String.valueOf(dataSnapshot1.child("userid").getValue());
                    if(eemmaaiill.equals(email) && pphhnn.equals(phn) && vviiee.equals(vehi))
                    {
                        driveruid = uid;

                    }

                    if(eemmaaiill.equals(email))
                    {
                        emailmatch = true;

                    }
                    if(pphhnn.equals(phn))
                    {
                        phnmatch = true;

                    }

                    if(vviiee.equals(vehi))
                    {
                        vehiclematch = true;

                    }




                }
                if(phnmatch != true)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Phone Not match",Toast.LENGTH_LONG).show();

                }
                else if(vehiclematch != true)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"vehicle no Not match",Toast.LENGTH_LONG).show();

                }
                else if(emailmatch != true)
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Email Not match",Toast.LENGTH_LONG).show();

                }
                else if(casename.equals(""))
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Select Atleast one case",Toast.LENGTH_LONG).show();
                }

                else if(emailmatch == true && phnmatch == true && vehiclematch == true)
                {
                    progressBar.setVisibility(View.VISIBLE);

                    databaseReference = FirebaseDatabase.getInstance().getReference("Driver Case File");
                    //get current date and time
                    Calendar calendar = Calendar.getInstance();
                    //time
                    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm aa");
                    String time = simpleTimeFormat.format(calendar.getTime());
                    //date
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                    String date = simpleDateFormat.format(calendar.getTime());


                    driverFinalcaseData.put("userid",driveruid);
                    driverFinalcaseData.put("time",time);
                    driverFinalcaseData.put("date",date);
                    driverFinalcaseData.put("case_name",casename);
                    driverFinalcaseData.put("case_amount", String.valueOf(caseamount));


                    databaseReference.push().setValue(driverFinalcaseData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(),"Your info has been uploaded",Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Somethings went wrong" ,Toast.LENGTH_LONG).show();
                            }

                        }
                    });



                }
                else
                {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"email,phone,vehicle no is wrong",Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }






}
