package com.kardb.tabletopaudio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.kardb.tabletopaudio.list.SoundListViewAdapter;
import com.kardb.tabletopaudio.list.SoundElementDatasource;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SoundElementDatasource ds = new SoundElementDatasource();
        ListView list = (ListView) findViewById(R.id.sound_listview);
        list.setAdapter(new SoundListViewAdapter(this, R.layout.list_view_element, ds.getSoundElements()));

    }
}