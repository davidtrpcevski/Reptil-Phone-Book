package com.david.reptil;

public class ExampleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mliceName;
    private String mnumber1;
    private String mnumber2;

    ExampleItem(int mImageResource, String mText1, String mText2, String mliceName, String mnumber1, String mnumber2) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.mliceName = mliceName;
        this.mnumber1 = mnumber1;
        this.mnumber2 = mnumber2;
    }

    public void changeText1(String text) {
        mText1 = text;
    }

    int getImageResource() {
        return mImageResource;
    }

    String getText1() {
        return mText1;
    }

    String getText2() {
        return mText2;
    }

    String getMliceName() {
        return mliceName;
    }

    String getMnumber1() {
        return mnumber1;
    }

    String getMnumber2() {
        return mnumber2;
    }
}