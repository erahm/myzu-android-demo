package com.therahm.myzudemo.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.therahm.myzudemo.R;
import com.therahm.myzudemo.models.Creature;


public class CreatureFragment extends Fragment {
    private Creature creature;


    public static CreatureFragment newInstance(Creature creature) {
        CreatureFragment fragment = new CreatureFragment();
        Bundle args = new Bundle();
        fragment.setCreature(creature);
        fragment.setArguments(args);
        return fragment;
    }

    public CreatureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_creature, container, false);
        ImageView creatureImage = (ImageView) view.findViewById(R.id.creature_image);
        TextView creatureName = (TextView) view.findViewById(R.id.creature_name);
        TextView creatureType = (TextView) view.findViewById(R.id.creature_type);
        creatureImage.setImageBitmap(creature.getPhoto());
        creatureName.setText(creature.getName());
        creatureType.setText(creature.getType());

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}
