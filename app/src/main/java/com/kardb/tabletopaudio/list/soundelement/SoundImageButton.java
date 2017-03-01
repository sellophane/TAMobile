package com.kardb.tabletopaudio.list.soundelement;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.kardb.tabletopaudio.R;
import com.kardb.tabletopaudio.utils.ColorUtils;

/**
 * Created by bkardys on 27/02/2017.
 */
@InverseBindingMethods({
        @InverseBindingMethod(type = SoundImageButton.class, attribute = "app:backgroundIcon"),
})
public class SoundImageButton extends ImageButton {

    @BindingAdapter({"app:backgroundIcon"})
    public static void setBackgroundIcon(SoundImageButton button, int value) {
        button.setBackgroundResource(value);
    }

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

    public static int getDefaultBackgroundResource() {
        return R.drawable.play_icon;
    }
}
