package com.example.lyz.backingapp.utils;

import com.example.lyz.backingapp.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Lyz on 06.02.2018.
 */

public class NetworkUtils {

    public static String getNetworkResponse() throws IOException{
        String recipesSource = BuildConfig.THE_BAKING_RECIPES_SOURCE;
        URL url = new URL (recipesSource);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput=scanner.hasNext();
            if (hasInput){
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
