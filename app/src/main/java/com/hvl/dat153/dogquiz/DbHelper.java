package com.hvl.dat153.dogquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hvl.dat153.dogquiz.Contract.*;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DogQuiz.db";
    private static final int DATABASE_VERSION = 5;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " (" +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NO + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable(SQLiteDatabase db) {
        Question q1 = new Question("android.resource://com.hvl.dat153.dogquiz/" + R.drawable.bernhard, "Bear Dog", "Teddy Dog", "Saint Bernhard", 3);
        addQuestion(q1,db);
        Question q2 = new Question("android.resource://com.hvl.dat153.dogquiz/" + R.drawable.bichon, "Bichon Frisé", "Circus Dog", "Cotton Shepherd", 1);
        addQuestion(q2,db);
        Question q3 = new Question("android.resource://com.hvl.dat153.dogquiz/" + R.drawable.bordercollie, "Happy Hunter", "Border Collie", "Energy Dog", 2);
        addQuestion(q3,db);
        Question q4 = new Question("android.resource://com.hvl.dat153.dogquiz/" + R.drawable.goldenretriever, "Golden Retriever", "Yellow Ranger", "Cuddly Dog", 1);
        addQuestion(q4,db);
        Question q5 = new Question("android.resource://com.hvl.dat153.dogquiz/" + R.drawable.germanshepherd, "Greenland Shepherd", "German Shepherd", "Cuban Shepherd", 2);
        addQuestion(q5,db);
        Question q6 = new Question("android.resource://com.hvl.dat153.dogquiz/" + R.drawable.oldenglishsheepdog, "Fluffy Delight", "Labbetuss", "Old English Sheepdog", 3);
        addQuestion(q6,db);
    }

    public void addQuestion(Question question, SQLiteDatabase db) {
        if(db==null) {
            db = getWritableDatabase();
        }

        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getImageUri());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NO, question.getAnswerNo());

        try {
            db.beginTransaction();
            db.insertOrThrow(QuestionsTable.TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("Synne", e.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase  db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setImageUri(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNo(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NO)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }


















}
