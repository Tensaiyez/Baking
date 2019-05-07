package com.example.bakingapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapplication.R;
import com.example.bakingapplication.Utils.Ingredients;
import com.example.bakingapplication.Utils.Recipe;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private List<Ingredients>ingredientsList;
    private Context mContext;

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {

        public TextView mQuantity;
        public TextView mMeasure;
        public TextView mIngredients;
        public CardView ingredientsCard;

        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            mQuantity = itemView.findViewById(R.id.quantity_tv);
            mMeasure = itemView.findViewById(R.id.measure_tv);
            mIngredients = itemView.findViewById(R.id.ingredients_tv);
            ingredientsCard = itemView.findViewById(R.id.ingredientsCardView);
        }
    }
        public IngredientsAdapter(List<Ingredients>ingredientsList,Context context){
            this.mContext=context;
            this.ingredientsList=ingredientsList;

        }


    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredients, viewGroup, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder ingredientsViewHolder, int i) {
        Ingredients ingredients=ingredientsList.get(i);
        ingredientsViewHolder.mIngredients.setText(ingredients.getIngredient());
        ingredientsViewHolder.mMeasure.setText(ingredients.getMeasure());
        ingredientsViewHolder.mQuantity.setText(ingredients.getQuantity().toString());

    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }


}
