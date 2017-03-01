package com.kardb.tabletopaudio.list.soundelement;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

import com.kardb.tabletopaudio.BR;
import com.kardb.tabletopaudio.R;
import com.kardb.tabletopaudio.utils.ErrorHandler;

import java.io.IOException;

/**
 * Created by kardb on 2017-02-26.
 */

public class SoundElementController extends BaseObservable {

    private SoundElement soundElement;
    private View.OnClickListener toggleRepeatListener;
    private SeekBar.OnSeekBarChangeListener changeVolumeListener;
    private MediaPlayer.OnPreparedListener onMediaPlayerPreparedListener;
    private View.OnTouchListener togglePlayListener;
    private boolean isPressed;

    public View.OnClickListener getToggleRepeatListener() {
        return toggleRepeatListener;
    }

    public SeekBar.OnSeekBarChangeListener getChangeVolumeListener() { return changeVolumeListener; }

    public View.OnTouchListener getTogglePlayListener() {
        return togglePlayListener;
    }

    @Bindable
    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean pressed) {
        isPressed = pressed;
        notifyPropertyChanged(BR.pressed);
    }

    @Bindable
    public int getIcon() {
        return soundElement.isReady() ? soundElement.getDrawable() : SoundImageButton.getDefaultBackgroundResource();
    }

    public int getName() {
        return soundElement.getName();
    }

    public int getVolume() {
        return soundElement.getVolume();
    }

    public boolean getRepeat() {
        return soundElement.isLooping();
    }

    public SoundElementController(SoundElement element) {
        soundElement = element;
        soundElement.setOnPlayChangesListener(new WebMediaPlayer.OnPlayChangesListener() {

            @Override
            public void onPlayStarts() {
                setPressed(true);
            }

            @Override
            public void onPlayStops() {
                setPressed(false);
            }
        });
        onMediaPlayerPreparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                SoundElementController.this.notifyPropertyChanged(BR.icon);
            }
        };
        changeVolumeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                soundElement.setVolume(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        };
        toggleRepeatListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundElement.toggleReplay();
            }
        };
        togglePlayListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    setPressed(!view.isPressed());
                    try {
                        if (!soundElement.togglePlay(view.getContext())) {
                            ErrorHandler.displayError(view.getContext(), view.getContext().getString(R.string.stream_unavailable_error));
                        }
                    } catch (IOException e) {
                        ErrorHandler.handleError(view.getContext(), e);
                    }
                }
                return true;
            }
        };
        soundElement.setOnPreparedListener(onMediaPlayerPreparedListener);
    }

}
