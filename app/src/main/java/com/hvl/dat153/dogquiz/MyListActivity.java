package com.hvl.dat153.dogquiz;

import android.app.ListActivity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class MyListActivity extends ListActivity {
    private ListView listView;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Populating lists
        DbHelper dbHelper = new DbHelper(this);
        questions = dbHelper.getAllQuestions();

        // Setting header
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextSize(20);
        textView.setText("List of dog breeds");

        ListView listView=(ListView)findViewById(android.R.id.list);
        listView.addHeaderView(textView);

        // For populating list data
        CustomDogList customDogList = new CustomDogList(this, questions);
        listView.setAdapter(customDogList);

        // Selecting an item in list and holding it will delete, TODO: add confirmation dialog!
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                int id = questions.get(position - 1).getId();
                dbHelper.deleteId(id);
                customDogList.remove(customDogList.getItem(position - 1));
                customDogList.notifyDataSetChanged();
                return false;
            }
        });
    }
}