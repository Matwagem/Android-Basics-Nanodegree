package com.example.android.dublinguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class TravelFragment extends ListFragment {

    public TravelFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_museum2, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.dart_title), getString(R.string.dart_desc), R.drawable.dart));
        words.add(new Word(getString(R.string.bus_title), getString(R.string.bus_desc), R.drawable.bus));
        words.add(new Word(getString(R.string.luas_title),getString(R.string.luas_desc), R.drawable.luas));
        words.add(new Word(getString(R.string.hailo_title), getString(R.string.hailo_desc) , R.drawable.hailo));
        words.add(new Word(getString(R.string.uber_title), getString(R.string.uber_desc), R.drawable.uber));

        WordAdapter adapter = new WordAdapter(getActivity(), words);
        setListAdapter(adapter);
        setRetainInstance(true);

        return rootView;
    }
}
