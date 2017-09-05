package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<String> passSt = new ArrayList<>();
        passSt.add("st1");
        passSt.add("st2");

        //int index = passSt.lastIndexOf("st1");
        passSt.remove("st1");


        Train train = new Train("deutsche bahn","st3", passSt);
        System.out.println(train.getFinalStation());
        train.setFinalStation("st5");
        System.out.println(train.getFinalStation());
        System.out.println(train.getPassingStations());
    }
}
