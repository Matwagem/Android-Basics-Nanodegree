package com.example.android.dublinguide;

public class Word {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String mTitle;
    private String mDescription;
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    public String getmDescription() {
        return mDescription;
    }

    public String getmTitle() {
        return mTitle;
    }

    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public Word(String description, String title, int imageResourceId){
        mDescription = title;
        mTitle = description;
        mImageResourceId = imageResourceId;
    }

    public Word(String description, String title){
        mDescription = title;
        mTitle = description;
    }
}