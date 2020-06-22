package com.example.rtrft;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageList extends BaseAdapter {
    ArrayList<Hit> hits;
    Context cont;
    Picasso picasso;

    public ImageList(Context cont, ArrayList<Hit> hits, Picasso picasso) {
        this.hits = hits;
        this.cont = cont;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return hits.size();
    }

    @Override
    public Object getItem(int position) {
        return hits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hit hit = hits.get(position);
        convertView = LayoutInflater.from(cont).inflate(R.layout.image, parent, false);
        ImageView hitUser = convertView.findViewById(R.id.IM);
        picasso.load(hit.webformatURL).into(hitUser);
        return convertView;
    }
}