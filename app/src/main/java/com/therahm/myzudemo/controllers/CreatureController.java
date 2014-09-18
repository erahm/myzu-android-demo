package com.therahm.myzudemo.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.therahm.myzudemo.models.Creature;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CreatureController {

    public ArrayList<Creature> fetchCreatures(String uri) {
        ArrayList<Creature> creatures = new ArrayList<Creature>();

        JSONArray jsonArray = retrieveGetResponse(uri);

        for(int index = 0; index < jsonArray.length(); index++) {
            try {
                Creature creature = populateCreature(jsonArray.getJSONObject(index));
                creatures.add(creature);
            }
            catch (JSONException e) {
                e.printStackTrace();
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

        return creature;
    }

    protected Bitmap retrievePhoto(String url) throws IOException {
        URL photoUrl = new URL(url);
        return BitmapFactory.decodeStream(photoUrl.openConnection().getInputStream());
    }

    protected JSONArray retrieveGetResponse(String uri) {
        String response = new RequestTask().execute(uri);
        JSONArray jsonArray = null;

        try {
            jsonArray = new JSONArray(response);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonArray;

    }
}
