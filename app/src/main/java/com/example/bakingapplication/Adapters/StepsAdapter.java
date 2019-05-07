package com.example.bakingapplication.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapplication.R;
import com.example.bakingapplication.RecipeDetails;
import com.example.bakingapplication.StepDetails;
import com.example.bakingapplication.Utils.Ingredients;
import com.example.bakingapplication.Utils.Steps;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
private List<Steps>stepsList;
private Context mContext;

    public class StepsViewHolder extends RecyclerView.ViewHolder {
    public TextView mShortDescription;

        public CardView StepsCard;

        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            mShortDescription=itemView.findViewById(R.id.StepsShortDescription);

            StepsCard = itemView.findViewById(R.id.stepsCard);
        }
    }
    public StepsAdapter(List<Steps>stepsList,Context context){
        this.mContext=context;
        this.stepsList=stepsList;

    }
    @NonNull
    @Override
    public StepsAdapter.StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.steps, viewGroup, false);
        return new StepsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.StepsViewHolder stepsViewHolder, int i) {
        Steps steps=stepsList.get(i);
        final int position=i;
        stepsViewHolder.mShortDescription.setText(steps.getShortDescription());

        stepsViewHolder.StepsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, StepDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("stepsDetails", stepsList.get(position));
                intent.putExtras(mBundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stepsList.size();
    }


}
