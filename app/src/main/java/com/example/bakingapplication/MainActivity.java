package com.example.bakingapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.bakingapplication.API.RetrofitService;
import com.example.bakingapplication.API.ServiceInterface;
import com.example.bakingapplication.Adapters.RecipeAdapter;

import com.example.bakingapplication.Utils.Ingredients;
import com.example.bakingapplication.Utils.Recipe;
import com.example.bakingapplication.Utils.Steps;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tv;
    private List<Recipe> recipes = new ArrayList<>();
    private RecipeAdapter recipeAdapter;
    RecyclerView recyclerView;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RecipeListFragment recipeListFragment= new RecipeListFragment();
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.placeholder,recipeListFragment);

        setContentView(R.layout.activity_main);
        mContext=this;

        tv = findViewById(R.id.RecipeName_tv);
        recyclerView= (RecyclerView) findViewById(R.id.RecipeList2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        String t = "";
        db.collection("Recipe")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            //iterates through recipies
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("no error", document.getId() + " => " + document.getData());
                                System.out.println(document.getId());

                                List<Ingredients> ingredients = new ArrayList<>();
                                List<Steps> steps = new ArrayList<>();

                                List<Map> ingredientMap = (List<Map>) document.get("ingredients");

                                for (Map ing : ingredientMap) {

                                    ingredients.add(new Ingredients((Long) ing.get("quantity"), (String) ing.get("measure"), (String) ing.get("ingredient")));
                                }
                                List<Map> stepsMap = (List<Map>) document.get("steps");
                                for (Map ing : stepsMap) {
                                    steps.add(new Steps((Long) ing.get("id"), (String) ing.get("shortDescription"), (String) ing.get("description"), (String) ing.get("videoURL"), (String) ing.get("thumbnailURL")));
                                }

                                recipes.add(new Recipe((String)document.get("name"),(String)document.get("image"),(Long)document.get("id"),(Long)document.get("servings"),ingredients,steps));
                                recipeAdapter= new RecipeAdapter(recipes,mContext);
                                recyclerView.setAdapter(recipeAdapter);
                            }
                        } else {
                            Log.w("error", "Error getting documents.", task.getException());
                        }
                    }
                });



    }


}
