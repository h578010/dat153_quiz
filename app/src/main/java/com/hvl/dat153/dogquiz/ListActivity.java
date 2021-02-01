package com.hvl.dat153.dogquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private String dogNames[];
    private String imageUri[];

    private String countryNames[] = {
            "Saint Bernhard",
            "Bichon Frisé",
            "Border Collie",
            "Old English Sheepdog"
    };

    private Integer images[] = {
            R.drawable.bernhard,
            R.drawable.bichon,
            R.drawable.bordercollie,
            R.drawable.oldenglishsheepdog

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Populating lists
//        DbHelper dbHelper = new DbHelper(this);
//        List<Question> dogList = dbHelper.getAllQuestions();
//        for (int i = 0; i < dogList.size(); i++) {
//            imageUri[i] = dogList.get(i).getImageUri();
//        }

        // Setting header
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("List of dog breeds");

        ListView listView=(ListView)findViewById(android.R.id.list);
        listView.addHeaderView(textView);

        // For populating list data
        CustomDogList customDogList = new CustomDogList(this, countryNames, images);
        listView.setAdapter(customDogList);
    }
}