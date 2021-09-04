package com.example.trafficechallan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DriverCaseAdapter extends RecyclerView.Adapter<DriverCaseAdapter.CaseViewHolder> {
    Context context;
    List<CaseModel> caselist;

    public DriverCaseAdapter(Context context, List<CaseModel> caselist) {
        this.context = context;
        this.caselist = caselist;
    }

    @NonNull
    @Override
    public CaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.case_lay,parent,false);
        CaseViewHolder caseViewHolder = new CaseViewHolder(view);


        return caseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CaseViewHolder holder, int position) {
        CaseModel caseModel = caselist.get(position);
        holder.name.setText(caseModel.getCase_name());
        holder.amount.setText(caseModel.getCase_amount());
        holder.date.setText(caseModel.getDate());
        holder.time.setText(caseModel.getTime());




    }

    @Override
    public int getItemCount() {
        return caselist.size();
    }

    public class CaseViewHolder  extends RecyclerView.ViewHolder{

        TextView name,amount,date,time;

        public CaseViewHolder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.firebasecasenameid);
            amount = itemView.findViewById(R.id.firebasecaseamountid);
            date = itemView.findViewById(R.id.firebasecasedateid);
            time = itemView.findViewById(R.id.firebasecasetimeid);
        }
    }
}
