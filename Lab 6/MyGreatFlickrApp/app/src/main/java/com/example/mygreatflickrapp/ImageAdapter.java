package com.example.mygreatflickrapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mygreatflickrapp.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class ImageAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<String> imageUrls;

    public ImageAdapter(@NonNull Context context, int resource, ArrayList<String> imageUrls) {
        super(context, resource, imageUrls);
        Log.d("debug", "ImageAdapter: creating iumageadater");
        this.context = context;
        this.imageUrls = imageUrls;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("debug", "getView: ");
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.flickr_image, parent, false);
        }

        Picasso.get()
                .load(imageUrls.get(position))
                .placeholder(R.mipmap.ic_launcher)
                .fit() // will explain later
                .into((ImageView)convertView);

        return convertView;
    }
}
