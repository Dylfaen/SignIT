package com.example.cesar.signit.Model;

/**
 * Created by cesar on 13/03/18.
 */

public class Stroke {
    private long startTime;
    private long stopTime;

    public Stroke(long startTime, long stopTime) {
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }
}
