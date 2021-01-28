package com.hvl.dat153.dogquiz;

import android.provider.BaseColumns;

public final class Contract {

    private Contract() {}

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTIONS1 = "option1";
        public static final String COLUMN_OPTIONS2 = "option2";
        public static final String COLUMN_OPTIONS3 = "option3";
        public static final String COLUMN_ANSWER_NO = "answer_no";
    }


}
