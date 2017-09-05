package com.company;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

public class Station {
    private Map<String, Train> trains;
    private Map<String, Timestamp> shedule;

    @Override
    public String toString() {
        return "Station{" +
                "trains=" + trains +
                ", shedule=" + shedule +
                '}';
    }

    public Station(Map<String, Train> trains, Map<String, Timestamp> shedule) {
        this.trains = trains;
        this.shedule = shedule;
    }

    public void AddTrain(String name, Timestamp time, String finalStation){
        Train train = new Train(name, finalStation, new ArrayList<>());
        trains.put(name, train);
        shedule.put(name, time);
    }
    public void DeleteTrain(String name){
        trains.remove(name);
        shedule.remove(name);
    }

    public void AddPassStation(String trainName, String stationName){
        Train train = trains.get(trainName);
        train.getPassingStations().add(stationName);
    }
    public void DeletePassStation(String trainName, String stationName){
        Train train = trains.get(trainName);
        train.getPassingStations().remove(stationName);
    }
    public void FindClosestTrain(String stationName, Timestamp time){
        String fittingTrainName = "";
        Timestamp fittingTime = null;
        for (Train train: trains.values()) {
            if (shedule.get(train.getName()).compareTo(time) >  0 &&
                    (train.getPassingStations().contains(stationName) || train.getFinalStation().equals(stationName))) {
                if (fittingTime != null && (fittingTime.compareTo(shedule.get(train.getName())) > 0)) {
                    fittingTrainName = train.getName();
                    fittingTime = shedule.get(train.getName());
                } else if (fittingTime == null) {
                    fittingTrainName = train.getName();
                    fittingTime = shedule.get(train.getName());
                }

            }

        }
        if (fittingTime != null) {
            System.out.println(fittingTrainName + " " + fittingTime);
        } else {
            System.out.println("There is no fitting train. Ever.");
        }
    }
}
