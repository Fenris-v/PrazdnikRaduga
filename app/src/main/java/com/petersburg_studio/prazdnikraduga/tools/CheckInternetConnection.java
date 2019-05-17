package com.petersburg_studio.prazdnikraduga.tools;

import android.content.Context;
import android.net.ConnectivityManager;

public class CheckInternetConnection {
    public static boolean checkConnection(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }
}
