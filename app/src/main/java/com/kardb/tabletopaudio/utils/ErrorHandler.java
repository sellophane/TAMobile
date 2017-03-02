package com.kardb.tabletopaudio.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kardb on 2017-02-26.
 */

public class ErrorHandler {

    public static void handleError(Context context, Exception e) {
        displayError(context, e.getLocalizedMessage());
    }

    public static void displayError(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
