package com.example.cesar.signit.Model;

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


}
