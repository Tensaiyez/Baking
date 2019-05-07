package com.example.bakingapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.bakingapplication.Adapters.IngredientsAdapter;
import com.example.bakingapplication.Adapters.StepsAdapter;
import com.example.bakingapplication.Utils.Recipe;

public class IngredientsActivity extends AppCompatActivity {
    Recipe recipe;
    RecyclerView recyclerView;
    Context mContext;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        mContext = this;
        recyclerView = (RecyclerView) findViewById(R.id.IngredientList_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            recipe = (Recipe) bundle.getSerializable("recipeDetails");
            ingredientsAdapter = new IngredientsAdapter(recipe.getIngredients(), mContext);
            recyclerView.setAdapter(ingredientsAdapter);
        }
    }
}
