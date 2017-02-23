package com.project.bbtools;

/**
 * Created by bbouzaiene on 23/12/2016.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private List<String> images;
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        this.mContext = c;
    }

    public ImageAdapter(Context c, List<String> images) {
        this.mContext = c;
        this.images = images;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        //imageView.setImageResource(mThumbIds[position]);
  //      imageView.setImageResource(Integer.parseInt(images.get(position)));
        Picasso.with(mContext).load(images.get(position))
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE).fit()
                .into(imageView);
        return imageView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.balance_badge, R.drawable.balance_badge,
            R.drawable.balance_badge, R.drawable.balance_badge,
            R.drawable.balance_badge, R.drawable.balance_badge,
            R.drawable.balance_badge, R.drawable.balance_badge,
            R.drawable.balance_badge, R.drawable.balance_badge,
            R.drawable.balance_badge, R.drawable.balance_badge,
            R.drawable.balance_badge, R.drawable.balance_badge
    };
}