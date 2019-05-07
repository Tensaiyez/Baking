package com.example.bakingapplication;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

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

import static android.content.Intent.getIntent;

public class BakingService extends RemoteViewsService {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Recipe> recipes=new ArrayList<>();


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingWidgetFactory(this.getApplicationContext(),intent) {


        };
    }
    class BakingWidgetFactory implements RemoteViewsFactory{
        private Context context;
        private int appWidgetId;


        public BakingWidgetFactory(Context applicationContext, Intent intent ) {
            this.context=applicationContext;
            appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);

        }

        @Override
        public void onCreate() {
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

                                }
                            } else {
                                Log.w("error", "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
        @Override
        public void onDataSetChanged() {
           
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return recipes.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views=new RemoteViews(context.getPackageName(),R.layout.widget_item);
            views.setTextViewText(R.id.widget_item_text,recipes.get(position).getName());
            Log.d("Widgets","Get view at"+position);

            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

}
