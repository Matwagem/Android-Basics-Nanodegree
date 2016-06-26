package com.example.android.dublinguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SightsFragment extends ListFragment {

    public SightsFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_museum2, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.guinness_title), getString(R.string.guinness_desc) , R.drawable.guinness));
        words.add(new Word(getString(R.string.jameson_title), getString(R.string.jameson_desc) , R.drawable.jameson));
        words.add(new Word(getString(R.string.spire_title), getString(R.string.spire_desc), R.drawable.spire));
        words.add(new Word(getString(R.string.stephen_title), getString(R.string.stephen_desc), R.drawable.stephen));
        words.add(new Word(getString(R.string.howth_title), getString(R.string.howth_desc), R.drawable.howth));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        setListAdapter(adapter);
        setRetainInstance(true);
        // Inflate the layout for this fragment
        return rootView;
    }
}
