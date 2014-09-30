package com.therahm.myzudemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.therahm.myzudemo.controllers.Adapter;
import com.therahm.myzudemo.controllers.CreatureController;
import com.therahm.myzudemo.fragments.CreatureFragment;
import com.therahm.myzudemo.models.Creature;

import java.util.ArrayList;


public class Main extends FragmentActivity {

    protected CreatureController creatureController;
    protected ViewPager viewPager;
    protected PagerAdapter pagerAdapter;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        creatureController = new CreatureController();
        viewPager = (ViewPager) findViewById(R.id.pager);
        Thread networkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String creaturesUri = "http://myzu-demo.herokuapp.com/creatures";

                ArrayList<Creature> creatures = creatureController.fetchCreatures(creaturesUri);
                ArrayList<Fragment> fragments = new ArrayList<Fragment>();

                for (Creature creature: creatures) {
                    fragments.add(CreatureFragment.newInstance(creature));
                }

                pagerAdapter = new Adapter(getSupportFragmentManager(), fragments);
            }
        });

        networkThread.start();

        try {
            networkThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        viewPager.setAdapter(pagerAdapter);
    }
}
