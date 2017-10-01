package com.company;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> diffArray = new ArrayList<>();

    public Solution(List<Integer> array) {
        for (int i = 1; i < array.size(); i++) {
            diffArray.add(array.get(i) - array.get(i - 1));
        }
    }
    public Result findSolution(){
        Result result = findMaxSubArray(0, diffArray.size() - 1);
        result.setRight(result.getRight() + 1);
        return result;
    }


    private Result findMaxSubArray (int left, int right){
        if (left == right) {
            return new Result(left, right, diffArray.get(left));
        } else if (left < right) {
            int mid = (right + left) / 2;
            Result answerLeft = findMaxSubArray(left, mid);
            Result answerRight = findMaxSubArray(mid + 1, right);
            Result answerCross = findMaxCrossSubArray(left, mid, right);
            if (answerLeft.getSum() >= answerCross.getSum() && answerLeft.getSum() >= answerRight.getSum()) {
                return answerLeft;
            } else if (answerCross.getSum() >= answerLeft.getSum() && answerCross.getSum() >= answerRight.getSum()) {
                return answerCross;
            } else {
                return answerRight;
            }
        } else throw new IllegalArgumentException();
    }
    private Result findMaxCrossSubArray (int left, int mid, int right){
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
        return new Result(indexLeft, indexRight, sumLeft + sumRight);
    }
}
