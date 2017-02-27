package com.kardb.tabletopaudio.list.soundelement;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.kardb.tabletopaudio.R;
import com.kardb.tabletopaudio.utils.ColorUtils;

/**
 * Created by bkardys on 27/02/2017.
 */
public class SoundImageButton extends ImageButton {

    private SoundElementController controller;

    public SoundImageButton(Context context) {
        super(context);
        setPressedState(false);
    }

    public SoundImageButton(Context context, AttributeSet set) {
        super(context, set);
        setPressedState(false);
    }

    public void setPressedState(boolean isPressed) {
        int color = R.color.dark_grey;
        if(isPressed) {
            color = R.color.white;
        }
        ColorUtils.changeDrawableColor(this, color);
        setPressed(isPressed);
    }

    public void setDefaultBackgroundResource() {
        setBackgroundResource(R.drawable.play_icon);
    }
}
