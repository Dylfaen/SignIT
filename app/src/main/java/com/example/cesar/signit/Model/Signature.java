package com.example.cesar.signit.Model;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by cesar on 13/03/18.
 */

public class Signature {

    private ArrayList<Stroke> strokes;
    private String filename;

    public Signature(String filename) {
        this.strokes = new ArrayList<>();
        this.filename = filename;
    }

    public void add(Stroke s) {
        this.strokes.add(s);
    }

    public ArrayList<Stroke> getStrokes() {
        return strokes;
    }

    public void setStrokes(ArrayList<Stroke> strokes) {
        this.strokes = strokes;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        JSONArray strokes_json = new JSONArray(strokes);
        try {
            jsonObject.put("filename", filename)
            .put("strokes", strokes_json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray strokes_json = new JSONArray();
        for(Stroke stroke : strokes) {
            strokes_json.put(stroke.toJson());
        }
        jsonObject.put("strokes", strokes_json);
        jsonObject.put("filename", filename);
        return jsonObject;
    }
}
