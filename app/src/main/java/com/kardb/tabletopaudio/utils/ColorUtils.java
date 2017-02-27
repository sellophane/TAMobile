package com.kardb.tabletopaudio.utils;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.kardb.tabletopaudio.R;

/**
 * Created by kardb on 2017-02-27.
 */

public class ColorUtils {

    public static void changeDrawableColor(View view, int colorId){
        int color = ContextCompat.getColor(view.getContext(), colorId);
        view.getBackground().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
    }
}

