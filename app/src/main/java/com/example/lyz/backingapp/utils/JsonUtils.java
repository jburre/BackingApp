package com.example.lyz.backingapp.utils;

import android.util.Log;

import com.example.lyz.backingapp.entity.Ingredient;
import com.example.lyz.backingapp.entity.Recipe;
import com.example.lyz.backingapp.entity.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Lyz on 06.02.2018.
 */

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getName();

    private static final String RECIPEID="id";
    private static final String RECIPENAME="name";
    private static final String RECIPEINGREDIENTS="ingredients";
    private static final String RECIPESTEPS="steps";
    private static final String RECIPESERVINGS="servings";
    private static final String RECIPEIMAGE="image";

    private static final String INGREDIENTQUANTITY="quantity";
    private static final String INGREDIENTMEASURE="measure";
    private static final String INGREDIENT="ingredient";

    private static final String STEPSHORTDESCRIPTION="shortDescription";
    private static final String STEPDESCRIPTION="description";
    private static final String VIDEOURL="videoURL";
    private static final String THUMBNAIL="thumbnailURL";

    public Recipe[] getRecipesFromResource(){
        Recipe[] recipes=null;
        try {
            String response = NetworkUtils.getNetworkResponse();
            JSONArray totalResult= new JSONArray(response);
            recipes= parseJsonArrayToRecipes(totalResult);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG,"Unable to receive recipe");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "Failed to create objects from response");
        }
        return recipes;
    }

    private Recipe[] parseJsonArrayToRecipes(JSONArray totalResult) throws JSONException {
        Recipe[]recipes = null;
        if (totalResult!=null&&totalResult.length()>0){
            recipes = new Recipe[totalResult.length()];
            for (int i=0;i<totalResult.length();i++){
                recipes[i]=new Recipe();
                JSONObject tempJsonRecipe =(JSONObject) totalResult.get(i);
                recipes[i].setId(tempJsonRecipe.getInt(RECIPEID));
                recipes[i].setImage(tempJsonRecipe.getString(RECIPEIMAGE));
                recipes[i].setName(tempJsonRecipe.getString(RECIPENAME));
                recipes[i].setServings(tempJsonRecipe.getInt(RECIPESERVINGS));
                ArrayList<Step> steps = new ArrayList<>();
                JSONArray recipeSteps= tempJsonRecipe.getJSONArray(RECIPESTEPS);
                if (recipeSteps.length()>0){
                    for (int j=0;j<recipeSteps.length();j++){
                        JSONObject jsonStep = (JSONObject) recipeSteps.get(j);
                        Step step = new Step();
                        step.setId(jsonStep.getInt(RECIPEID));
                        step.setShortDescription(jsonStep.getString(STEPSHORTDESCRIPTION));
                        step.setDescription(jsonStep.getString(STEPDESCRIPTION));
                        step.setThumbNailUrl(jsonStep.getString(THUMBNAIL));
                        step.setVideoURL(jsonStep.getString(VIDEOURL));
                        steps.add(step);
                    }
                }
                //TODO add steps to recipe
                ArrayList<Ingredient>recipeIngredients=new ArrayList<>();
                JSONArray ingredientsList= tempJsonRecipe.getJSONArray(RECIPEINGREDIENTS);
                if (ingredientsList.length()>0){
                    for (int k=0;k<ingredientsList.length();k++){
                        JSONObject jsonIngredient =(JSONObject) ingredientsList.get(k);
                        Ingredient ingredient = new Ingredient();
                        ingredient.setQuantity(jsonIngredient.getInt(INGREDIENTQUANTITY));
                        ingredient.setIngredient(jsonIngredient.getString(INGREDIENT));
                        ingredient.setMeasure(jsonIngredient.getString(INGREDIENTMEASURE));
                        recipeIngredients.add(ingredient);
                    }
                }
                //TODO add ingredients to recipe
            }
        }
        return recipes;
    }
}
