package com.example.lyz.backingapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lyz.backingapp.adapter.BasicRecipeAdapter;
import com.example.lyz.backingapp.asyncTasks.AsyncTaskCompleteListener;
import com.example.lyz.backingapp.asyncTasks.FetchRecipesTask;
import com.example.lyz.backingapp.entity.Recipe;

public class MainActivity extends AppCompatActivity implements BasicRecipeAdapter.RecipeAdapterOnClickHandler{

    private static final String TAG = MainActivity.class.getName();
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private ProgressBar progressBar;
    private BasicRecipeAdapter basicRecipeAdapter;
    private ImageView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview_recipes_overview);
        progressBar=findViewById(R.id.pg_main);
        errorView=findViewById(R.id.main_error_image);
        basicRecipeAdapter=new BasicRecipeAdapter(this);
        if (savedInstanceState!=null){
            //TODO
        } else {
            ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            boolean connectedToNetwork = networkInfo!=null&&networkInfo.isConnectedOrConnecting();
            if (connectedToNetwork){
                new FetchRecipesTask(this,new FetchRecipiesTaskListener()).execute();
            } else {
                showNoConnection();
            }
        }
    }

    private void showNoConnection() {
        this.errorView.setVisibility(View.VISIBLE);
        Toast.makeText(this, getString(R.string.noWiFi),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(Recipe recipe) {
        Context context = this;
        Class destination = RecipeDetails.class;
        Intent startIntent= new Intent(context,destination);
        startIntent.putExtra("recipe", recipe);
        startActivity(startIntent);
    }

    private class FetchRecipiesTaskListener implements AsyncTaskCompleteListener<Recipe[]>{

        @Override
        public void onPreExecuting() {
            showLoadingScreen();
        }

        @Override
        public void onTaskComplete(Recipe[] recipes) {
            endLoadingAndShowRecipes();
        }
    }

    private void endLoadingAndShowRecipes() {
        this.progressBar.setVisibility(View.INVISIBLE);
    }

    private void showLoadingScreen() {
        this.progressBar.setVisibility(View.VISIBLE);
    }
}
