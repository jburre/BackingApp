package com.example.lyz.backingapp.asyncTasks;

/**
 * Created by Lyz on 07.02.2018.
 */

public interface AsyncTaskCompleteListener <T>{
    public void onPreExecuting();
    public void onTaskComplete(T result);
}
