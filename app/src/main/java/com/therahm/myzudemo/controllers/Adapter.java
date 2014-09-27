package com.therahm.myzudemo.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Adapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    public Adapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
        super(fragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int index) {
        return fragments.get(index);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
