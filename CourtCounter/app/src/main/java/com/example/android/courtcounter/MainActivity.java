package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int score = 0;
    int scoreB = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayForTeamA() {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void addMaxScore(View view){
        score+=3;
        displayForTeamA();
    }

    public void addScore(View view){
        score+=2;
        displayForTeamA();
    }

    public void addMinScore(View view) {
        score += 1;
        displayForTeamA();
    }

    public void displayForTeamB() {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(scoreB));
    }

    public void addMaxScoreB(View view){
        scoreB+=3;
        displayForTeamB();
    }

    public void addScoreB(View view){
        scoreB+=2;
        displayForTeamB();
    }

    public void addMinScoreB(View view) {
        scoreB += 1;
        displayForTeamB();
    }

    public void reset(View view) {
        scoreB = 0;
        score = 0;
        displayForTeamB();
        displayForTeamA();
    }
}
