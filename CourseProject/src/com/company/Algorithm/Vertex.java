package com.company.Algorithm;

public class Vertex {
    private Integer x;
    private Integer y;
    private Integer number;
    private Integer value;

    Vertex(Integer value, Integer x, Integer y, Integer number) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.number = number;
    }

    Integer getX() {
        return x;
    }

    Integer getY() {
        return y;
    }

    Integer getValue() { return value; }

    Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
