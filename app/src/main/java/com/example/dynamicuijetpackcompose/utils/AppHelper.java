package com.example.dynamicuijetpackcompose.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppHelper
{
    /*Method to check is internet available or not*/
    public static boolean isInternetConnected(Context context) {
        if (context == null) {
            return false;
        }
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = null;
            if (cm != null) {
                activeNetwork = cm.getActiveNetworkInfo();
            }
            return activeNetwork != null
                    && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {

            return true;

        }
    }
}
