package com.kardb.tabletopaudio.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by kardb on 2017-02-26.
 */

public class NetUtils {

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }
}
