package com.newsful5.android.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.newsful5.android.R;


public class SettingsMenu extends ActionBarActivity {

    private SettingsMenuAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsmenu_listview);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setIcon(R.drawable.icon_settings);

        ListView lv = (ListView) findViewById(R.id.settingsmenu_list);
        mAdapter=new SettingsMenuAdapter(this);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(SettingsMenu.this, TechList.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("value", position);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    LinkAddress.listno = position;
            }
        });
    }
}


class SettingsMenuAdapter extends BaseAdapter {
    String[] drawerOptions;
    int[] image = {
            R.drawable.icon_tech,
            R.drawable.icon_news,
            R.drawable.icon_game,
            R.drawable.icon_photo,
            R.drawable.icon_life,
            R.drawable.icon_sports,
            R.drawable.icon_movie,
            R.drawable.icon_books,
            R.drawable.icon_food,
    };
    private Context context;

    public SettingsMenuAdapter(Context context)
    {
        this.context=context;
        drawerOptions=context.getResources().getStringArray(R.array.tech_sites);
    }
    @Override
    public int getCount() {
        return drawerOptions.length;
    }

    @Override
    public Object getItem(int position) {
        return drawerOptions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=null;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.activity_settings_menu,parent,false);

        }else{
            row=convertView;
        }
        TextView title=(TextView)row.findViewById(R.id.textviewTitle);
        ImageView imagetitle=(ImageView)row.findViewById(R.id.imageviewSettings);
        title.setText(drawerOptions[position]);
        imagetitle.setImageResource(image[position]);

        return row;
    }
}