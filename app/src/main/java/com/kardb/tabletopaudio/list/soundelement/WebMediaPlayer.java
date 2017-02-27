package com.kardb.tabletopaudio.list.soundelement;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by kardb on 2017-02-27.
 */

public class WebMediaPlayer extends MediaPlayer {

    public interface OnPlayChangesListener {
        void onPlayStarts();
        void onPlayStops();
    }

    private static final String PAGE = "https://sounds.tabletopaudio.com/";
    private OnPlayChangesListener onPlayChangesListener;

    public void setOnPlayChangesListener(OnPlayChangesListener onPlayChangesListener) {
        this.onPlayChangesListener = onPlayChangesListener;
    }

    public void setSoundFile(String soundUrl) throws IOException {
        this.setDataSource(PAGE + soundUrl);
    }

    @Override
    public void start() throws IllegalStateException {
        super.start();
        if (onPlayChangesListener != null) {
            onPlayChangesListener.onPlayStarts();
        }
    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        if (onPlayChangesListener != null) {
            onPlayChangesListener.onPlayStops();
        }
    }

    public WebMediaPlayer() {
        super();
        this.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (!mediaPlayer.isPlaying() && onPlayChangesListener != null) {
                    onPlayChangesListener.onPlayStops();
                }
            }
        });

    }
}
