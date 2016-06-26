package com.example.android.dublinguide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentManager;

public class PagerAdapter extends FragmentStatePagerAdapter{
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new MuseumFragment();
                break;
            case 1:
                frag=new SightsFragment();
                break;
            case 2:
                frag=new FoodFragment();
                break;
            case 3:
                frag=new TravelFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Museums";
                break;
            case 1:
                title="Sights";
                break;
            case 2:
                title="Food";
                break;
            case 3:
                title="Travel";
                break;
        }

        return title;
    }
}
