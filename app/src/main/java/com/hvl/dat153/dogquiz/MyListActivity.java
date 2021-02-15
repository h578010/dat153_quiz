package com.hvl.dat153.dogquiz;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MyListActivity extends ListActivity {
    private ListView listView;
    private List<Dog> questions;
    private ListActivity self;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        self = this;

        // Populating lists
        DogRoomDB db = DogRoomDB.getDatabase(this);
        questions = db.dogDao().getAllDogs();


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

        // Selecting an item in list and holding it will make an alert dialog ask if you want to delete question
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                new AlertDialog.Builder(self)
                        .setMessage("Do you really want to delete this question?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                int id = questions.get(position - 1).getId();
                                db.dogDao().deleteDog(id);
                                customDogList.remove(customDogList.getItem(position - 1));
                                customDogList.notifyDataSetChanged();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });
    }
}