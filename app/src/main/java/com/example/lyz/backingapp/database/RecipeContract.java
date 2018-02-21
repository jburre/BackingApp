package com.example.lyz.backingapp.database;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Lyz on 07.02.2018.
 */

public class RecipeContract {
    public static final String AUTHORITY="com.example.lyz";
    public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+AUTHORITY);
    public static final String PATH_RECIPES = "recipes";



    public static final class RecipeEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_RECIPES).build();
        public static final String TABLE_NAME_RECIPES="recipes";
        public static final String COLUMN_RECIPEID="recipeId";
        public static final String COLUMN_RECIPE_IMAGE="recipeImage";
        public static final String COLUMN_RECIPE_NAME="name";
        public static final String COLUMN_RECIPE_SERVINGS="servings";
        //TODO write the columns
    }
}
