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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bakingapplication.R;
import com.example.bakingapplication.RecipeDetails;
import com.example.bakingapplication.Utils.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> recipeList;
    private Context mcontext;
    private int layoutID;

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public TextView mRecipeName;
        public CardView recipeCard;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecipeName = itemView.findViewById(R.id.RecipeName_tv);
            recipeCard = itemView.findViewById(R.id.RecipeCard);
        }
    }

    public RecipeAdapter(List<Recipe> recipeList, Context context) {
        this.mcontext = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_card, viewGroup, false);

        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe = recipeList.get(i);
        final int position=i;
        recipeViewHolder.mRecipeName.setText(recipe.getName());
        recipeViewHolder.recipeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mcontext, RecipeDetails.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("recipeDetails", recipeList.get(position));
                intent.putExtras(mBundle);
            mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


}
