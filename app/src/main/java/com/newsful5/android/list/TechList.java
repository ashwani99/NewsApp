package com.newsful5.android.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import com.newsful5.android.R;


public class TechList extends Activity {

    ListView listView;
    ArrayAdapter<String> adapter;
    String[] table_name = {"tech_table", "news_table", "game_table", "photo_table", "lifestyle_table",
            "sports_table", "hollywood_table", "books_table", "food_table", "music_table"};
    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int intvalue = getIntent().getExtras().getInt("value");

        //TODO : check if any other options are available to create or update database smoothly


        setContentView(R.layout.activity_common_list);
        findViewsById();
        createOrCheckDatabase(table_name[intvalue], intvalue);
        //TODO: we are getting link address from another java file. Move them to string files. site_list.xml
        String[] list = LinkAddress.getLink(intvalue);
        //String[] desc = LinkAddress.getDescription(intvalue);

        // if (intvalue == 0) {
        ArrayList<String> myList = new ArrayList();
        // ArrayList<String> myDesc = new ArrayList();
        for (int i = 0; i < list.length; i++) {
            // myDesc.add(desc[i]);
            myList.add(list[i]);
            //   }
            CustomAdapter myAdapter = new CustomAdapter(this, myList, table_name[intvalue], db);
            listView.setAdapter(myAdapter);
        } /*else if (intvalue == 1) {*/


    }

    private void createOrCheckDatabase(String name, int pos) {
        db = new MyDBHandler(this);
        db.TABLE_NAME = name;
        String[] list = LinkAddress.getLink(pos);
        db.createTable(list);

    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.listView3);
    }

}
