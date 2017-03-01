package com.kardb.tabletopaudio.list.soundelement;

import android.content.Context;
import android.media.MediaPlayer;

import com.kardb.tabletopaudio.utils.NetUtils;

import java.io.IOException;

/**
 * Created by kardb on 2017-02-26.
 */

public class SoundElement {

    private int name;
    private int drawable;
    private String soundUrl;
    private WebMediaPlayer player;
    private float volume;
    private boolean replay;

    public int getDrawable() {
        return drawable;
    }

    public void setOnPlayChangesListener(WebMediaPlayer.OnPlayChangesListener playChangesListener) {
        player.setOnPlayChangesListener(playChangesListener);
    }

    public boolean isLooping() {
        return replay;
    }

    private enum STATUS {UNINITIALIZED, PREPARING, READY};
    private STATUS currentStatus = STATUS.UNINITIALIZED;

    public SoundElement (int nameId, int drawableId, String fileName) {
        player = new WebMediaPlayer();
        soundUrl = fileName;
        name = nameId;
        replay = false;
        volume = 0.1f;
        drawable = drawableId;
    }

    public int getVolume() {
        return Math.round(volume * 100);
    }

    public void setVolume(int i) {
        volume=i/100.f;
        player.setVolume(volume, volume);
    }

    public void toggleReplay() {
        replay = !replay;
        this.player.setLooping(replay);
    }

    public boolean togglePlay(Context context) throws IOException {
        if (NetUtils.isConnected(context)) {
            if (currentStatus != STATUS.READY) {
                if (currentStatus == STATUS.UNINITIALIZED) {
                    currentStatus = STATUS.PREPARING;
                    player.setSoundFile(soundUrl);
                    player.prepareAsync();
                }
            } else if (player.isPlaying()) {
                player.pause();
            } else {
                player.start();
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean isReady() {
        return currentStatus == STATUS.READY;
    }

    public void setOnPreparedListener(final MediaPlayer.OnPreparedListener preparedListener) {
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                preparedListener.onPrepared(mediaPlayer);
                mediaPlayer.setLooping(replay);
                mediaPlayer.setVolume(volume, volume);
                currentStatus = STATUS.READY;
                player.start();
            }
        });
    }

    public int getName() {
        return name;
    }

}
