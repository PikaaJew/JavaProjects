package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        /*arrayList.add(3);
        arrayList.add(13);
        arrayList.add(7);
        arrayList.add(19);
        arrayList.add(8);*/
        arrayList.add(18);
        Solution solution = new Solution(arrayList);
        Result answer = solution.findSolution();
        System.out.println("buy day: " + answer.getLeft() + "\nsell day: " + answer.getRight() + "\nsum: " + answer.getSum());
    }
}
