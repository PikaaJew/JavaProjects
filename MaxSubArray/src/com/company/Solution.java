package com.company;

import java.util.ArrayList;

public class Solution {
    private ArrayList<Integer> diffArray = new ArrayList<>();

    public Solution(ArrayList<Integer> array) {
        if (array.size() <= 1) {
            diffArray.add(0);
        } else {
            for (int i = 1; i < array.size(); i++) {
                diffArray.add(array.get(i) - array.get(i - 1));
            }
        }
    }

    public int[] findMaxSubArray (int left, int right){
        if (left == right) {
            return new int[]{left, right, diffArray.get(left)};
        } else if (left < right) {
            int mid = (right + left) / 2;
            int[] answerLeft = findMaxSubArray(left, mid);
            int[] answerRight = findMaxSubArray(mid + 1, right);
            int[] answerCross = findMaxCrossSubArray(left, mid, right);
            if (answerLeft[2] >= answerCross[2] && answerLeft[2] >= answerRight[2]) {
                return answerLeft;
            } else if (answerCross[2] >= answerLeft[2] && answerCross[2] >= answerRight[2]) {
                return answerCross;
            } else {
                return answerRight;
            }
        } else return null;

    }
    public int[] findMaxCrossSubArray (int left, int mid, int right){
        int sumLeft = Integer.MIN_VALUE;
        int sumRight = Integer.MIN_VALUE;
        int indexLeft = mid;
        int indexRight = mid + 1;
        int sum = 0;
        for (int i = mid; i >= left; i--){
            sum += diffArray.get(i);
            if (sum > sumLeft) {
                sumLeft = sum;
                indexLeft = i;
            }
        }
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += diffArray.get(i);
            if (sum > sumRight) {
                sumRight = sum;
                indexRight = i;
            }
        }
        return new int[]{indexLeft, indexRight, sumLeft + sumRight};
    }
}
