package com.example.trafficechallan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class FourFragment extends Fragment {
    RadioButton c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,c19,c20,c21;

    int caseamount;
    String fourfragcase;


    RadioGroup radioGroup;

    public FourFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_four, container, false);
        c1 = v.findViewById(R.id.generalid);
        c2 = v.findViewById(R.id.ViolationofRoadRulesid);
        c3 = v.findViewById(R.id.Ticketlesstravel4id);
        c4 = v.findViewById(R.id.OversizedVehiclesid);
        c5 = v.findViewById(R.id.DrivingWhenMentallyPhysicallyUnfitid);
        c6 = v.findViewById(R.id.AccidentRelatedOffencesid);
        c7 = v.findViewById(R.id.DrivingUninsuredVehicleid);
        c8 = v.findViewById(R.id.VehicleWithoutPermitid);
        c9 = v.findViewById(R.id.NotWearingSeatbeltid);
        c10 = v.findViewById(R.id.Drunkendriving4id);
        radioGroup = v.findViewById(R.id.radiogroupidforfour);


        c19 = v.findViewById(R.id.firstgear4id);
        c20 = v.findViewById(R.id.secgear4id);
        c21 = v.findViewById(R.id.thgear4id);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if(v.findViewById(checkedId) == c1)
                {
                    fourfragcase = c1.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }


                if(v.findViewById(checkedId) == c2)
                {
                    fourfragcase = c2.getText().toString();
                    caseamount = 300;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c3)
                {
                    fourfragcase = c3.getText().toString();
                    caseamount = 200;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c4)
                {
                    fourfragcase = c4.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c5)
                {
                    fourfragcase = c5.getText().toString();
                    caseamount = 300;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c6)
                {
                    fourfragcase = c6.getText().toString();
                    caseamount = 200;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c7)
                {
                    fourfragcase = c7.getText().toString();
                    caseamount = 1000;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c8)
                {
                    fourfragcase = c8.getText().toString();
                    caseamount = 5000;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c9)
                {
                    fourfragcase = c9.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }

                if(v.findViewById(checkedId) == c10)
                {
                    fourfragcase = c10.getText().toString();
                    caseamount = 2000;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromfour(fourfragcase,caseamount);
                }


                if(v.findViewById(checkedId) == c19)
                {
                    fourfragcase = c19.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(fourfragcase,caseamount);

                }

                if(v.findViewById(checkedId) == c20)
                {
                    fourfragcase = c20.getText().toString();
                    caseamount = 300;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(fourfragcase,caseamount);

                }

                if(v.findViewById(checkedId) == c21)
                {
                    fourfragcase = c21.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),fourfragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(fourfragcase,caseamount);

                }



















            }
        });



        return v;
    }
}
