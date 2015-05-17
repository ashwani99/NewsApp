package com.newsful5.android;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.etsy.android.grid.util.DynamicHeightTextView;

import java.util.List;
import java.util.Random;


public class RssAdapter extends BaseAdapter {

    public ImageLoader imageLoader;
    private final List<RssItem> items;
    private  Random mRandom;
    private final Context context;
    private static final SparseArray<Double> sPositionHeightRatios =new SparseArray<Double>();
    public RssAdapter(Context context, List<RssItem> items) {
        this.items = items;
        this.context = context;
       try {
           imageLoader = new ImageLoader(context.getApplicationContext());
       }catch(NullPointerException e)
       {

       }
       }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.rss_item, null);
            holder = new ViewHolder();
            holder.itemTitle = (DynamicHeightTextView) convertView.findViewById(R.id.Title);
            holder.iv = (DynamicHeightImageView) convertView.findViewById(R.id.itemImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemTitle.setText(items.get(position).getTitle());
        DynamicHeightImageView img = holder.iv;
        imageLoader.DisplayImage(items.get(position).getImageViewLink(), img);
        double positionHeight = getPositionRatio(position);
        holder.iv.setHeightRatio(positionHeight);


        return convertView;
    }





private double getPositionRatio(final int position) {
    double ratio = sPositionHeightRatios.get(position, 0.0);
    if (ratio == 0) {
        ratio = getRandomHeightRatio();
        sPositionHeightRatios.append(position, ratio);

    }
    return ratio;
}

    private double getRandomHeightRatio() {
        double next=1;
try {
    next=(mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
}catch(NullPointerException e)
{

}
        return next;
    }

    static class ViewHolder {
        DynamicHeightTextView itemTitle;
        DynamicHeightImageView iv;
    }
}