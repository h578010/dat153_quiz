package com.hvl.dat153.dogquiz;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomDogList extends ArrayAdapter {

    private Activity context;
    private List<Question> questions;

    public CustomDogList(Activity context, List<Question> questions) {
        super(context, R.layout.row_item, questions);
        this.context = context;
        this.questions = questions;
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

        textViewDog.setText(questions.get(position).getCorrectOption());
        imageDog.setImageURI(Uri.parse(questions.get(position).getImageUri()));

        return row;
    }

}
