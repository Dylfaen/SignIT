package com.example.cesar.signit.Model;

import android.content.res.Resources;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.Serializable;
import com.example.cesar.signit.Helpers.Writer;
import java.util.ArrayList;

/**
 * Created by cesar on 13/03/18.
 */

public class Data implements Serializable {
    private static Data INSTANCE = new Data();

    private ArrayList<TrainingProfile> data;

    public ArrayList<TrainingProfile> getData() {
        return this.data;
    }

    public static void saveData() throws JSONException, FileNotFoundException {
        String json = Data.getInstance().toJson().toString().replace("\\", "");
        Log.d("Data", json);
        Writer.writeToFile(Data.getInstance().toJson().toString().replace("\\", ""), "data.txt");


    }

    public void addTrainingProfile(TrainingProfile trainingProfile) throws IllegalArgumentException{
        if(this.exists(trainingProfile)) {
            throw new IllegalArgumentException("Same title as already existing profile");
        } else {
            this.data.add(trainingProfile);
            try {
                Data.saveData();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean exists(TrainingProfile trainingProfile) {
        boolean exists;
        try {
            this.getTrainingProfileIndex(trainingProfile.getTitle());
            exists = true;
        } catch (Resources.NotFoundException e) {
            exists = false;
        }
        return exists;
    }

    public void removeTrainingProfile(int index) {
        this.data.remove(index);
        try {
            Data.saveData();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeTrainingProfile(String title) {
        this.data.remove(this.getTrainingProfileIndex(title));
        try {
            Data.saveData();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private TrainingProfile getTrainingProfile(int index) {
        return this.data.get(index);
    }

    private int getTrainingProfileIndex(String title) throws Resources.NotFoundException{
        boolean found = false;
        int size = this.getData().size();
        int i = 0;
        int result = -1;
        while(!found && i < size) {
            if(this.getTrainingProfile(i).getTitle().equals(title) ) {
                found = true;
                result = i;
            }
            i++;
        }
        if(!found) {
            throw new Resources.NotFoundException();
        }
        return result;
    }

    public void loadData(ArrayList<TrainingProfile> trainingProfiles) {
        this.data = trainingProfiles;
    }

    private Data() {
        this.data = new ArrayList<TrainingProfile>();
    }

    public static Data getInstance() {
        if (INSTANCE == null)
        {
            synchronized(Data.class)
            {
                if (INSTANCE == null)
                {   INSTANCE = new Data();
                }
            }
        }
        return INSTANCE;
    }

    private Object readResolve() {
        return INSTANCE;
    }

    private JSONObject toJson() throws JSONException {
        JSONObject jo = new JSONObject();
        JSONArray data_json = new JSONArray();
        for(TrainingProfile t : data) {
            data_json.put(t.toJson());
        }

        jo.put("trainingProfiles", data_json);

        return jo;
    }
}
