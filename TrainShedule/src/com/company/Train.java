package com.company;

import java.util.ArrayList;

public class Train {
    private String name;
    private String finalStation;
    private ArrayList<String> passingStations;

    public Train(String name, String finalStation, ArrayList<String> passingStations) {
        this.name = name;
        this.finalStation = finalStation;
        this.passingStations = passingStations;
        if (this.passingStations == null) {
            this.passingStations = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", finalStation='" + finalStation + '\'' +
                ", passingStations=" + passingStations +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFinalStation() {
        return finalStation;
    }

    public void setFinalStation(String finalStation) {
        this.finalStation = finalStation;
    }

    public ArrayList<String> getPassingStations() {
        return passingStations;
    }

    public void setPassingStations(ArrayList<String> passingStations) {
        this.passingStations = passingStations;
    }
}
