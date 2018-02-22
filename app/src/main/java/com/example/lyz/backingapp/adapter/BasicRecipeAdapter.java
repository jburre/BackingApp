package com.example.lyz.backingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lyz.backingapp.R;
import com.example.lyz.backingapp.entity.Recipe;

/**
 * Created by Lyz on 07.02.2018.
 */

public class BasicRecipeAdapter extends RecyclerView.Adapter<BasicRecipeAdapter.BasicRecipeAdapterViewHolder>{

    private static final String TAG = BasicRecipeAdapter.class.getName();

    private Recipe[] recipes;
    private RecipeAdapterOnClickHandler mClickHandler;
    private Context context;

    public BasicRecipeAdapter(RecipeAdapterOnClickHandler clickHandler){
        mClickHandler=clickHandler;
    }

    @Override
    public BasicRecipeAdapter.BasicRecipeAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context= parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        boolean shouldAttachToParentImmediatly=false;
        int layoutIdForRecipe= R.layout.recipeoverview;
        View view = inflater.inflate(layoutIdForRecipe,parent, shouldAttachToParentImmediatly);
        return new BasicRecipeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BasicRecipeAdapter.BasicRecipeAdapterViewHolder holder, int position) {
        int recipeNumber=position+1;
        String recipedescription = "Recipe "+recipeNumber;
        TextView textView = holder.recipeView;
        textView.setText(recipedescription);
    }

    @Override
    public int getItemCount() {
        if (recipes==null){
            return 0;
        }
        return recipes.length;
    }

    public interface RecipeAdapterOnClickHandler{
        void onClick(Recipe recipe);
    }

    public class BasicRecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView recipeView;

        public BasicRecipeAdapterViewHolder(View itemView) {
            super(itemView);
            recipeView=itemView.findViewById(R.id.main_recipe_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Recipe recipe =recipes[position];
            mClickHandler.onClick(recipe);
        }
    }

    public Recipe[] getRecipes() {
        return recipes;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }
}
