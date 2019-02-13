package com.otb.mvvmboilerplate.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by Mohit Rajput on 11/2/19.
 */
public class NetworkUtils {
    /**
     * Checks whether internet is connected in device or not
     *
     * @param context
     * @return true if connected, false otherwise
     */
    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }
        return false;
    }

    public static Request prepareInterceptorRequest(Interceptor.Chain chain) {
        Request.Builder builder = chain.request().newBuilder();
        return builder.build();
    }
}
