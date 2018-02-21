package com.example.lyz.backingapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        //TODO a lot
        return null;
    }

    @Override
    public void onBindViewHolder(BasicRecipeAdapter.BasicRecipeAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface RecipeAdapterOnClickHandler{
        void onClick(Recipe recipe);
    }

    public class BasicRecipeAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public BasicRecipeAdapterViewHolder(View itemView) {
            super(itemView);
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
