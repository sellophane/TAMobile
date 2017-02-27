package com.kardb.tabletopaudio.list;

import com.kardb.tabletopaudio.R;
import com.kardb.tabletopaudio.list.soundelement.SoundElement;

/**
 * Created by kardb on 2017-02-27.
 */

public class SoundElementDatasource {

    private static final SoundElement [] soundElements = new SoundElement[] {
            new SoundElement(R.string.footsteps, R.drawable.boots, "dungeon2/footsteps.ogg"),
            new SoundElement(R.string.rain, R.drawable.rain_icon, "dark_forest2/rain1_lp.ogg"),
            new SoundElement(R.string.wind, R.drawable.wind_icon2, "dark_forest2/wind1_lp.ogg"),
            new SoundElement(R.string.thunder, R.drawable.thunder_icon, "dark_forest2/thunder1.ogg"),
            new SoundElement(R.string.horsecart, R.drawable.horse_icon, "dark_forest2/horsecart1_lp.ogg"),
            new SoundElement(R.string.ethereal_dark, R.drawable.bass_tone, "dark_forest2/ethereal_dark1_lp.ogg"),
            new SoundElement(R.string.drums, R.drawable.drum_icon, "dark_forest2/distant_drums1_lp.ogg"),
            new SoundElement(R.string.victrola, R.drawable.music, "house2/victrola1.ogg")
    };

    public SoundElement[] getSoundElements() {
        return soundElements;
    }
}
