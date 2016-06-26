package com.example.android.dublinguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends ListFragment {

    public FoodFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_museum2, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.queen_title), getString(R.string.queen_desc), R.drawable.queen));
        words.add(new Word(getString(R.string.chapter_title),getString(R.string.chapter_desc), R.drawable.chapter));
        words.add(new Word(getString(R.string.patrick_title),getString(R.string.patrick_desc), R.drawable.patrick));
        words.add(new Word(getString(R.string.pitt_title), getString(R.string.pitt_desc), R.drawable.pitt));
        words.add(new Word(getString(R.string.sweet_title), getString(R.string.sweet_desc), R.drawable.sweet));

        WordAdapter adapter = new WordAdapter(getActivity(), words);
        setListAdapter(adapter);
        setRetainInstance(true);
        return rootView;
    }
}
