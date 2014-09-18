package com.therahm.myzudemo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.therahm.myzudemo.controllers.Adapter;
import com.therahm.myzudemo.controllers.CreatureController;
import com.therahm.myzudemo.fragments.CreatureFragment;
import com.therahm.myzudemo.models.Creature;

import java.util.ArrayList;


public class Main extends FragmentActivity {

    protected CreatureController creatureController;
    protected ViewPager viewPager;
    protected PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatureController = new CreatureController();

        ArrayList<Creature> creatures = creatureController.fetchCreatures("http://myzu-demo.herokuapp.com/creatures");

        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        ArrayList<CreatureFragment> fragments = new ArrayList<CreatureFragment>();

        for (Creature creature: creatures) {
            fragments.add(CreatureFragment.newInstance(creature));
        }

    }
}
