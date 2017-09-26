package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(3);
        arrayList.add(13);
        arrayList.add(7);
        arrayList.add(19);
        arrayList.add(1);
        arrayList.add(18);
        Solution solution = new Solution(arrayList);
        int[] answer = solution.findMaxSubArray(0, arrayList.size() - 2);
        if (answer != null) {
            System.out.println("left " + answer[0] + " right " + answer[1] + " sum " + answer[2]);
            System.out.println(answer[0] + " " + (answer[1] + 1));
        } else {
            System.out.println("Empty array");
        }
    }
}
