package com.hw.hellowash.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hw.hellowash.fragments.LandingScreenFragment;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter
{
    int addCount;
    public ScreenSlidePagerAdapter(FragmentManager fm, int addCount) {
        super(fm);
        this.addCount = addCount;
    }

    @Override
    public Fragment getItem(int position) {
        return new LandingScreenFragment();
    }

    @Override
    public int getCount() {
        return addCount;
    }
}
