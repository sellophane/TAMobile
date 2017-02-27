package com.kardb.tabletopaudio.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kardb.tabletopaudio.R;
import com.kardb.tabletopaudio.list.soundelement.SoundElement;
import com.kardb.tabletopaudio.list.soundelement.SoundElementController;
import com.kardb.tabletopaudio.list.soundelement.SoundImageButton;

/**
 * Created by kardb on 2017-02-26.
 */

public class SoundListViewAdapter extends ArrayAdapter<SoundElementController> {

    private LayoutInflater inflater;

    private static SoundElementController[] convertToControllersArray (SoundElement [] soundElements) {
        SoundElementController [] datasource = new SoundElementController[soundElements.length];
        for(int i = 0; i < soundElements.length; i++) {
            datasource[i] = new SoundElementController(soundElements[i]);
        }
        return datasource;
    }

    public SoundListViewAdapter(Context context, int id, SoundElement [] soundElements) {
        super(context, id, convertToControllersArray(soundElements));
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_view_element, null);
        }
        SoundElementController controller =  this.getItem(position);
        SoundImageButton imgButton = (SoundImageButton) convertView.findViewById(R.id.list_view_item_logo);
        controller.adjustButton(imgButton);
        TextView title = (TextView) convertView.findViewById(R.id.list_view_item_textView);
        title.setText(convertView.getContext().getString(controller.getName()));
        SeekBar seekBar = (SeekBar) convertView.findViewById(R.id.list_view_item_seekBar);
        seekBar.setOnSeekBarChangeListener(controller.getChangeVolumeListener());
        seekBar.setProgress(controller.getVolume());
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.list_view_item_checkBox);
        checkBox.setOnClickListener(controller.getToggleRepeatListener());
        return convertView;
    }
}
