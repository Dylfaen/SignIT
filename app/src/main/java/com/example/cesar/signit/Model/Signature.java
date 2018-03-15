package com.example.cesar.signit.Model;

import java.util.ArrayList;

/**
 * Created by cesar on 13/03/18.
 */

public class Signature {

    private ArrayList<Stroke> strokes;

    public Signature() {
        this.strokes = new ArrayList<>();
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
}
