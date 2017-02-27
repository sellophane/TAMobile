package com.kardb.tabletopaudio.list.soundelement;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.kardb.tabletopaudio.R;
import com.kardb.tabletopaudio.utils.ColorUtils;
import com.kardb.tabletopaudio.utils.ErrorHandler;

import java.io.IOException;

/**
 * Created by kardb on 2017-02-26.
 */

public class SoundElementController {

    private SoundElement soundElement;
    private View.OnClickListener toggleRepeatListener;
    private SeekBar.OnSeekBarChangeListener changeVolumeListener;
    private MediaPlayer.OnPreparedListener onMediaPlayerPreparedListener;
    private View.OnTouchListener togglePlayListener;
    private SoundImageButton imageButton;
    private boolean isPressed;

    public View.OnClickListener getToggleRepeatListener() {
        return toggleRepeatListener;
    }

    public SeekBar.OnSeekBarChangeListener getChangeVolumeListener() { return changeVolumeListener; }

    public View.OnTouchListener getTogglePlayListener() {
        return togglePlayListener;
    }

    public SoundElementController(SoundElement element) {
        soundElement = element;
        soundElement.setOnPlayChangesListener(new WebMediaPlayer.OnPlayChangesListener() {

            @Override
            public void onPlayStarts() {
                isPressed = true;
                if (imageButton != null) {
                    imageButton.setPressedState(true);
                }
            }

            @Override
            public void onPlayStops() {
                isPressed = false;
                if (imageButton != null) {
                    imageButton.setPressedState(false);
                }
            }
        });
        onMediaPlayerPreparedListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                imageButton.setBackgroundResource(soundElement.getDrawable());
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
                    imageButton = (SoundImageButton) view;
                    isPressed = !view.isPressed();
                    imageButton.setPressedState(isPressed);
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

    public int getName() {
        return soundElement.getName();
    }

    public int getVolume() {
        return soundElement.getVolume();
    }

    public void adjustButton(SoundImageButton imgButton) {
        if (soundElement.isReady()) {
            imgButton.setBackgroundResource(soundElement.getDrawable());
        } else {
            imgButton.setDefaultBackgroundResource();
        }
        imgButton.setPressedState(isPressed);
        imgButton.setOnTouchListener(togglePlayListener);
    }
}
