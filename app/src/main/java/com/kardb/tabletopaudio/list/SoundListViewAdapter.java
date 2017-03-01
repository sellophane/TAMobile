package com.kardb.tabletopaudio.list;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.kardb.tabletopaudio.databinding.ListViewElementBinding;
import com.kardb.tabletopaudio.list.soundelement.SoundElement;
import com.kardb.tabletopaudio.list.soundelement.SoundElementController;

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
        ListViewElementBinding binding;
        if (convertView == null) {
            binding = ListViewElementBinding.inflate(inflater, parent, false);
            convertView = binding.getRoot();
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setSoundElementController(this.getItem(position));
        return convertView;
    }
}
