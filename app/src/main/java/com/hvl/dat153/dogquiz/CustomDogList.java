package com.hvl.dat153.dogquiz;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomDogList extends ArrayAdapter {

    private String[] dogNames;
    private Integer[] imageId;
    private Activity context;
    private Uri [] imageUris;

    public CustomDogList(Activity context, String[] dogNames, Integer[] imageId) {
        super(context, R.layout.row_item, dogNames);
        this.context = context;
        this.dogNames = dogNames;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if (convertView == null) {
            row = inflater.inflate(R.layout.row_item, null, true);
        }
        TextView textViewDog = (TextView) row.findViewById(R.id.textViewDog);
        ImageView imageDog = (ImageView) row.findViewById(R.id.imageViewDog);

        textViewDog.setText(dogNames[position]);
        imageDog.setImageResource(imageId[position]);

        return row;
    }

}
