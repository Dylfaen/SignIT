package com.example.cesar.signit.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cesar on 13/03/18.
 */

public class TrainingProfile {
    private String title;
    private ArrayList<Signature> signatures;

    public TrainingProfile(String title) {
        this.title = title;
        this.signatures = new ArrayList<>();

    }

    public void add(Signature signature) {
        this.signatures.add(signature);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(ArrayList<Signature> signatures) {
        this.signatures = signatures;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        JSONArray signatures_json = new JSONArray(signatures);
        try {
            jsonObject.put("title", title)
            .put("signatures", signatures_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return signatures_json.toString();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray signatures_json = new JSONArray();
        for(Signature signature : signatures) {
            signatures_json.put(signature.toJson());
        }
        jsonObject.put("signatures", signatures_json);
        jsonObject.put("title", title);
        return jsonObject;
    }
}
