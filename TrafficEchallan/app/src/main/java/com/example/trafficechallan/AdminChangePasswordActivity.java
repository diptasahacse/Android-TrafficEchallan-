package com.example.trafficechallan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AdminChangePasswordActivity extends AppCompatActivity {
    DatabaseReference databaseReference1;
    DatabaseReference databaseReference2;

    Toolbar toolbar;


    EditText em,pa;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_change_password);

        em = findViewById(R.id.adminemailid);
        pa = findViewById(R.id.adminnewpassid);
        b = findViewById(R.id.adminchangepassbuttonid);

        toolbar = findViewById(R.id.adminchangepassid);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //adding back button
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        databaseReference1 = FirebaseDatabase.getInstance().getReference("Admin");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                em.setText(dataSnapshot.child("email").getValue().toString());
                pa.setText(dataSnapshot.child("pass").getValue().toString());
                em.setKeyListener(null);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference1 = FirebaseDatabase.getInstance().getReference("Admin");
                if(!em.getText().toString().trim().equals("") || !pa.getText().toString().isEmpty())
                {
                    HashMap<String , String> hashMap = new HashMap<>();
                    hashMap.put("email",em.getText().toString());
                    hashMap.put("pass",pa.getText().toString());

                    databaseReference1.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"Password is Changed Succefully",Toast.LENGTH_LONG).show();
                            }


                        }
                    });


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong admin email.or null password.You have to login again",Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}
