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


public class TwoFragment extends Fragment {
    RadioButton c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c21;

    int caseamount;
    String twofragcase;



    RadioGroup radioGroup;


    public TwoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View v = inflater.inflate(R.layout.fragment_two, container, false);

        c1 = v.findViewById(R.id.generalid);
        c2 = v.findViewById(R.id.rulesofid);
        c3 = v.findViewById(R.id.ticketlessid);
        c4 = v.findViewById(R.id.Disobedienid);
        c5 = v.findViewById(R.id.Drivingwithoutlicenseid);
        c6 = v.findViewById(R.id.Drivingdespitedisqualificationid);
        c7 = v.findViewById(R.id.Oversizevehiclesid);
        c8 = v.findViewById(R.id.Overspeedingid);
        c9 = v.findViewById(R.id.Dangerousdrivingpenaltyid);
        c10 = v.findViewById(R.id.Drunkendrivingid);
        c11 = v.findViewById(R.id.SpeedingRacingid);
        c12 = v.findViewById(R.id.Vehiclewithoutpermitid);
        c13 = v.findViewById(R.id.Aggregatorsid);
        c14 = v.findViewById(R.id.Overloadingid);
        c15 = v.findViewById(R.id.Overloadingofpassengersid);
        c16 = v.findViewById(R.id.Seatbeltid);
        c17 = v.findViewById(R.id.Overloadingoftwowheelersid);
        c18 = v.findViewById(R.id.Helmetsid);



        c19 = v.findViewById(R.id.firstgear2id);
        c20 = v.findViewById(R.id.secgear2id);
        c21 = v.findViewById(R.id.thgear2id);



        radioGroup = v.findViewById(R.id.radiogroupidfortwo);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(v.findViewById(checkedId) == c1)
                {
                    twofragcase = c1.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c2)
                {
                    twofragcase = c2.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c3)
                {
                    twofragcase = c3.getText().toString();
                    caseamount = 200;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c4)
                {
                    twofragcase = c4.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c5)
                {
                    twofragcase = c5.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c6)
                {
                    twofragcase = c6.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c7)
                {
                    twofragcase = c7.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c8)
                {
                    twofragcase = c8.getText().toString();
                    caseamount = 400;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c9)
                {
                    twofragcase = c9.getText().toString();
                    caseamount = 1000;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c10)
                {
                    twofragcase = c10.getText().toString();
                    caseamount = 2000;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c11)
                {
                    twofragcase = c11.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c12)
                {
                    twofragcase = c12.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c13)
                {
                    twofragcase = c13.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c14)
                {
                    twofragcase = c14.getText().toString();
                    caseamount = 2000;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c15)
                {
                    twofragcase = c15.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c16)
                {
                    twofragcase = c16.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }
                if(v.findViewById(checkedId) == c17)
                {
                    twofragcase = c17.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);
                }
                if(v.findViewById(checkedId) == c18)
                {
                    twofragcase = c18.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }



                if(v.findViewById(checkedId) == c19)
                {
                    twofragcase = c19.getText().toString();
                    caseamount = 100;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }

                if(v.findViewById(checkedId) == c20)
                {
                    twofragcase = c20.getText().toString();
                    caseamount = 300;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }

                if(v.findViewById(checkedId) == c21)
                {
                    twofragcase = c21.getText().toString();
                    caseamount = 500;
                    Toast.makeText(getContext(),twofragcase+" is Confirmed",Toast.LENGTH_LONG).show();
                    Wellcome wellcome = (Wellcome) getActivity();
                    wellcome.receivedatafromtwo(twofragcase,caseamount);

                }


            }
        });
        //

        // Inflate the layout for this fragment
        return v;
    }


}




/*



                    if(casename.equals(c1.getText().toString()))
                    {
                        twofragcase = casename;
                        caseamount = 100;

                    }
                    if(casename.equals(c2.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 100;

                    }

                    if(casename.equals(c3.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 200;

                    }
                    if(casename.equals(c4.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }


                    if(casename.equals(c5.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }
                    if(casename.equals(c6.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }

                    if(casename.equals(c7.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }
                    if(casename.equals(c8.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 400;

                    }


                    if(casename.equals(c9.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 1000;

                    }
                    if(casename.equals(c10.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 2000;

                    }

                    if(casename.equals(c11.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }
                    if(casename.equals(c12.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }


                    if(casename.equals(c13.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 100;

                    }
                    if(casename.equals(c14.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 2000;

                    }

                    if(casename.equals(c15.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }
                    if(casename.equals(c16.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 500;

                    }

                    if(casename.equals(c17.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 100;

                    }
                    if(casename.equals(c18.getText()))
                    {
                        twofragcase = casename;
                        caseamount = 100;

                    }

 */
