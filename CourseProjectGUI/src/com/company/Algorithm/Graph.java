package com.company.Algorithm;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
    private ArrayList<ArrayList<Vertex>> graph; //0 fo free cell and 1 for wall
    private Integer verticesNumber;
    private Integer labirinthWidth;
    private Integer labirinthHeight;
    private ArrayList<ArrayList<Integer>> weight;
    private Integer beginI;
    private Integer beginJ;
    private Integer endI;
    private Integer endJ;

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < graph.size(); ++i) {
            res += graph.get(i).toString() + "\n";
        }
        return res;
    }

    public void setVertex(Integer i, Integer j, Integer value) {
        Vertex vertex = new Vertex(value, i, j, getVerticeNumber(i, j));
        graph.get(i).set(j, vertex);
    }

    public void setBeginI(Integer beginI) {
        this.beginI = beginI;
    }

    public void setBeginJ(Integer beginJ) {
        this.beginJ = beginJ;
    }

    public void setEndI(Integer endI) {
        this.endI = endI;
    }

    public void setEndJ(Integer endY) {
        this.endJ = endY;
    }

    public Integer getBeginI() {
        return beginI;
    }

    public Integer getBeginJ() {
        return beginJ;
    }

    public Integer getEndI() {
        return endI;
    }

    public Integer getEndJ() {
        return endJ;
    }

    public Graph(ArrayList<ArrayList<Integer>> graph) {
        this.graph = new ArrayList<>(graph.size());
        beginI = beginJ = endI = endJ = 0;
        labirinthHeight = graph.size();
        labirinthWidth = graph.get(0).size();
        this.verticesNumber = labirinthHeight * labirinthWidth;
        weight = new ArrayList<>();
        for (int i = 0; i < verticesNumber; ++i) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < verticesNumber; ++j) {
                if (i == j) {
                    tmp.add(j, Integer.MAX_VALUE);
                } else {
                    tmp.add(j, 1);
                }
            }
            weight.add(tmp);
        }

        for (int i = 0; i < labirinthHeight; ++i) {
            ArrayList<Vertex> tmp = new ArrayList<>();
            for (int j = 0; j < labirinthWidth; ++j) {
                tmp.add(new Vertex(graph.get(i).get(j), i, j, getVerticeNumber(i, j)));
            }
            this.graph.add(tmp);
        }

    }

    public Integer getLabirinthWidth() {
        return labirinthWidth;
    }

    public Integer getLabirinthHeight() {
        return labirinthHeight;
    }

    public Integer getVerticeNumber(Integer x, Integer y) {
        return x * labirinthWidth + y;
    }

    public ArrayList<Vertex> findNeighbours(Vertex v) {
        ArrayList<Vertex> res = new ArrayList<>();
        int dx[] = {1, 0, -1, 0};
        int dy[] = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            Integer x = v.getX() + dx[i];
            Integer y = v.getY() + dy[i];
            if (x >= 0 && x < labirinthHeight && y >= 0 && y < labirinthWidth && graph.get(x).get(y).getValue() == 0) {
                res.add(graph.get(x).get(y));
            }
        }
        return res;
    }

    private Boolean breadthFirstSearch(ArrayList<Integer> finalPath,
                                       Integer beginI, Integer beginJ, Integer endI, Integer endY) {
        Vertex begin = graph.get(beginI).get(beginJ);
        Vertex end = graph.get(endI).get(endY);
        ArrayList<Integer> distancesFromBegin = new ArrayList<>(verticesNumber);
        ArrayList<Integer> path = new ArrayList<>(verticesNumber);
        ArrayList<Vertex> markedVertices = new ArrayList<>();
        for (int i = 0; i < verticesNumber; ++i) {
            distancesFromBegin.add(Integer.MAX_VALUE);
        }
        distancesFromBegin.set(getVerticeNumber(beginI, beginJ), 0);
        for (int i = 0; i < verticesNumber; ++i) {
            path.add(-1);
        }
        markedVertices.add(graph.get(beginI).get(beginJ));
        while (!markedVertices.isEmpty()) {
            ArrayList<Vertex> tmp = new ArrayList<>();
            for (Vertex u: markedVertices) {
                ArrayList<Vertex> neighbours = findNeighbours(u);
                for (Vertex v: neighbours) {
                    Integer wUV = weight.get(u.getNumber()).get(v.getNumber());
                    if (!wUV.equals(Integer.MAX_VALUE) &&
                            distancesFromBegin.get(v.getNumber()) > distancesFromBegin.get(u.getNumber()) + wUV) {
                        distancesFromBegin.set(v.getNumber(), distancesFromBegin.get(u.getNumber()) + wUV);
                        path.set(v.getNumber(), u.getNumber());
                        if (!tmp.contains(v)) {
                            tmp.add(v);
                        }
                    }
                }
            }
            markedVertices = tmp;
            markedVertices.remove(end);
        }
        if (distancesFromBegin.get(end.getNumber()).equals(Integer.MAX_VALUE)) {
            return false;
        }
        finalPath.add(end.getNumber());
        Integer u = end.getNumber();

        while (!u.equals(begin.getNumber())) {
            u = path.get(u);
            finalPath.add(u);
        }
        Collections.reverse(finalPath);
        return true;
    }

    public ArrayList<Integer> findPath() {
        ArrayList<Integer> firstPath = new ArrayList<>();
        Boolean existFirst = breadthFirstSearch(firstPath, beginI, beginJ, endI, endJ);
        for (int i = 0; i < firstPath.size() - 1; ++i) {
            Integer v1 = firstPath.get(i);
            Integer v2 = firstPath.get(i + 1);
            weight.get(v2).set(v1, -1);
            weight.get(v1).set(v2, Integer.MAX_VALUE);
        }
        ArrayList<Integer> secondPath = new ArrayList<>();
        Boolean existSecond = breadthFirstSearch(secondPath, beginI, beginJ, endI, endJ);

        ArrayList<Integer> resultingPath = new ArrayList<>();
        if (existFirst) {
            resultingPath.addAll(firstPath);
            if (existSecond) {
                Collections.reverse(secondPath);
                secondPath.remove(0);
                resultingPath.addAll(secondPath);
            } else {
                resultingPath.add(-1);
            }
        }
        return resultingPath;
    }
}
