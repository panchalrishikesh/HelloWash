package com.hw.hellowash.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hw.hellowash.fragments.LandingScreenFragment;
import com.hw.hellowash.fragments.ProfileFragment;
import com.hw.hellowash.fragments.TrackOrderFragment;

public class MyPagerAdapter extends FragmentPagerAdapter
{
    private static int NUM_ITEMS = 3;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LandingScreenFragment();
            case 1:
                return new TrackOrderFragment();
            case 2:
                return new ProfileFragment();
            default:
                return null;
        }
    }

    /*// Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }*/
}
