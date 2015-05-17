package com.newsful5.android.list;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsful5.android.R;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    private Context context;
    final private List<String> sitelist;
    String tablename;
    MyDBHandler db;
    private View convertView;

    public CustomAdapter(Context context, List<String> objects, String table, MyDBHandler dbHandler) {
        this.context = context;
        this.sitelist = objects;
        this.tablename = table;
        this.db = dbHandler;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public int getCount() {
        return sitelist.size();
    }


    @Override
    public Object getItem(int position) {
        return sitelist.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.activity_news_list, null);
            holder.linkTitle = (TextView) convertView.findViewById(R.id.textview22);
            holder.iv = (ImageView) convertView.findViewById(R.id.imageview2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TextView title = holder.linkTitle;
        final ImageView image = holder.iv;
        holder.iv.setImageResource(R.drawable.icon_settings);
        title.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (isChecked(position)) {
                    title.setTextColor(Color.parseColor("#FFFFFF"));
                    image.setImageResource(R.drawable.icon_unchecked);
                    updateTable(position, 0);
                } else {
                    title.setTextColor(Color.parseColor("#b9f6ca"));
                    image.setImageResource(R.drawable.icon_checked);
                    updateTable(position, 1);
                }

            }
        });

        if (!isChecked(position)) {
            title.setTextColor(Color.parseColor("#FFFFFF"));
            image.setImageResource(R.drawable.icon_unchecked);
        } else {
            title.setTextColor(Color.parseColor("#b9f6ca"));
            image.setImageResource(R.drawable.icon_checked);
        }
        holder.linkTitle.setText(sitelist.get(position));
        return convertView;
    }

    Boolean isChecked(int id) {
        if (db.getLink(id + 1) == 1)
            return true;
        return false;
    }

    private void updateTable(int pos, int value) {
        String[] techlist = LinkAddress.getLink(LinkAddress.listno);
        db.updateLink(new Product(pos + 1, techlist[pos], value));
    }

    static class ViewHolder {
        TextView linkTitle;
        ImageView iv;
    }

}