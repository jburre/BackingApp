package com.example.lyz.backingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lyz on 07.02.2018.
 */

public class RecipeDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="recipes.db";

    private static final String TABLE_RECIPE="recipes";
    private static final String TABLE_STEPS="steps";
    private static final String TABLE_INGREDIENTS="ingredients";
    private static final String CREATE_TABLE_RECIPES="CREATE TABLE "+ RecipeContract.RecipeEntry.TABLE_NAME_RECIPES+" ("+
            RecipeContract.RecipeEntry._ID+" INTEGER PRIMARY KEY, "+
            RecipeContract.RecipeEntry.
    private static final int VERSION=1;

    public RecipeDbHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+RecipeContract.RecipeEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
