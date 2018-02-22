package com.example.lyz.backingapp.asyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.lyz.backingapp.entity.Recipe;
import com.example.lyz.backingapp.utils.JsonUtils;
import com.example.lyz.backingapp.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Lyz on 07.02.2018.
 */

public class FetchRecipesTask extends AsyncTask<Void,Void,Recipe[]>{

    private static final String TAG = FetchRecipesTask.class.getName();
    private AsyncTaskCompleteListener<Recipe[]>listener;
    private Context context;

    public FetchRecipesTask(Context context, AsyncTaskCompleteListener listener){
        this.context=context;
        this.listener=listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onPreExecuting();
    }

    @Override
    protected Recipe[] doInBackground(Void... voids) {
        Recipe[] recipes= null;
        try{
            String jsonResponse= NetworkUtils.getNetworkResponse();
            recipes =JsonUtils.getRecipesFromResource(jsonResponse);
        } catch (IOException e){
            Log.d(TAG,"Error in trying to get the jsonResponse");
        }
        return recipes;
    }

    @Override
    protected void onPostExecute(Recipe[] recipes) {
        super.onPostExecute(recipes);
        listener.onTaskComplete(recipes);
    }
}
