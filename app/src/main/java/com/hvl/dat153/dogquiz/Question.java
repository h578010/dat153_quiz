package com.hvl.dat153.dogquiz;

public class Question {
    int id;
    private String imageUri;
    private String option1;
    private String option2;
    private String option3;
    private int answerNo;

    public Question() {

    }

    public Question(String imageUri, String option1, String option2, String option3, int answerNo) {
        this.imageUri = imageUri;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNo = answerNo;
    }

    public Question(int id, String imageUri, String option1, String option2, String option3, int answerNo) {
        this.id = id;
        this.imageUri = imageUri;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNo = answerNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String resourceId) {
        this.imageUri = resourceId;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(int answerNo) {
        this.answerNo = answerNo;
    }

    public String getOption(int i) {
        if (i == 1) {
            return this.option1;
        }
        if (i == 2) {
            return this.option2;
        }
        return this.option3;
    }

    public String getCorrectOption() {
        int no = this.getAnswerNo();
        return this.getOption(no);
    }
}
