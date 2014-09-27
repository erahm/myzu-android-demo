package com.therahm.myzudemo.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.therahm.myzudemo.models.Creature;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class CreatureController {

    private RequestTask requestTask;
    private String responseString;
    private Bitmap image;
    private URL photoUrl;

    public ArrayList<Creature> fetchCreatures(String uri) {
        ArrayList<Creature> creatures = new ArrayList<Creature>();

        JSONObject creaturesObject = retrieveGetResponse(uri);
        JSONArray jsonArray = null;
        try {
            jsonArray = creaturesObject.getJSONArray("creatures");
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        if (jsonArray != null) {
            for(int index = 0; index < jsonArray.length(); index++) {
                try {
                    Creature creature = populateCreature(jsonArray.getJSONObject(index));
                    creatures.add(creature);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return creatures;
    }

    protected Creature populateCreature(JSONObject jsonObject) {
        Creature creature = new Creature();

        try {
            creature.setName(jsonObject.getString("name"));
            creature.setPhoto(retrievePhoto(jsonObject.getString("photo")));
            creature.setType(jsonObject.getString("type"));
            creature.setAge(jsonObject.getInt("age"));
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return creature;
    }

    protected Bitmap retrievePhoto(String url) throws IOException, InterruptedException {
        photoUrl = new URL(url);
        Thread photoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    image = BitmapFactory.decodeStream(photoUrl.openConnection().getInputStream());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        photoThread.start();
        photoThread.join();
        return image;
    }

    protected JSONObject retrieveGetResponse(final String uri) {
        JSONObject jsonObject = null;

        Thread networkThread = new Thread(new Runnable() {
           @Override
            public void run() {
               requestTask = new RequestTask();
               responseString = requestTask.retrieveResponse(uri);
           }
        });

        networkThread.start();

        try {
            networkThread.join();
            jsonObject = new JSONObject(responseString);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }
}
