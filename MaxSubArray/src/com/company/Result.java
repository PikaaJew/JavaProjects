package com.company;

public class Result {
    private int left;
    private int right;
    private int sum;

    public Result(int left, int right, int sum) {
        this.left = left;
        this.right = right;
        this.sum = sum;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getSum() {
        return sum;

    }
}
