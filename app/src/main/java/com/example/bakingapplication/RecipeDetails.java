package com.example.bakingapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bakingapplication.Adapters.IngredientsAdapter;
import com.example.bakingapplication.Adapters.StepsAdapter;
import com.example.bakingapplication.Utils.Recipe;

public class RecipeDetails extends AppCompatActivity {
Recipe recipe;
RecyclerView recyclerView;
Context mContext;
IngredientsAdapter ingredientsAdapter;
StepsAdapter stepsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        mContext =this;

        recyclerView= (RecyclerView) findViewById(R.id.stepsList_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            recipe =(Recipe) bundle.getSerializable("recipeDetails");
            stepsAdapter=new StepsAdapter(recipe.getSteps(),mContext);
            recyclerView.setAdapter(stepsAdapter);

//            ingredientsAdapter= new IngredientsAdapter(recipe.getIngredients(),mContext);
//            recyclerView.setAdapter(ingredientsAdapter);

        }


    }

    public void onClickBtn(View v)
    {
        Intent intent = new Intent(mContext, IngredientsActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("recipeDetails", recipe);
        intent.putExtras(mBundle);
        mContext.startActivity(intent );
    }
}
