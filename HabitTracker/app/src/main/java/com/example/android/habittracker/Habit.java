package com.example.android.habittracker;

/**
 * Created by Mathijs' on 29-Jun-16.
 */
public class Habit {
    String type;
    String freq;

    public String getFreq(){
        return freq;
    }

    public void setFreq(String freq){
        this.freq=freq;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }
}
