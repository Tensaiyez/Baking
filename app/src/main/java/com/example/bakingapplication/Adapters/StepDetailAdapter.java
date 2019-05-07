package com.example.bakingapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapplication.R;
import com.example.bakingapplication.Utils.Steps;

import java.util.List;

public class StepDetailAdapter extends RecyclerView.Adapter<StepDetailAdapter.StepDetailViewHolder> {
    private List<Steps>stepsList;

    Context mContext;
    public class StepDetailViewHolder extends RecyclerView.ViewHolder{
        public TextView mDescription;
        public StepDetailViewHolder(@NonNull View itemView) {
            super(itemView);
//        mDescription=itemView.findViewById(R.id.stepDescription);
        }
    }
    public StepDetailAdapter(String stepsList, Context context){
        this.mContext=context;

    }
    @NonNull
    @Override
    public StepDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_step_details, viewGroup, false);
        return new StepDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepDetailViewHolder stepDetailViewHolder, int i) {
        Steps steps=stepsList.get(i);
        stepDetailViewHolder.mDescription.setText(steps.getDescription());
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }


}
