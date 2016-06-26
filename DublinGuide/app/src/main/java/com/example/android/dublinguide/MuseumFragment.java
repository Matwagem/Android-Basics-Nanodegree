package com.example.android.dublinguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MuseumFragment extends ListFragment {

    public MuseumFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_museum2, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(getString(R.string.kil_title), getString(R.string.kil_desc), R.drawable.kil));
        words.add(new Word(getString(R.string.national_title), getString(R.string.national_desc), R.drawable.national));
        words.add(new Word(getString(R.string.writers_title), getString(R.string.writers_desc), R.drawable.writers));
        words.add(new Word(getString(R.string.wax_title), getString(R.string.wax_desc), R.drawable.wax));

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
