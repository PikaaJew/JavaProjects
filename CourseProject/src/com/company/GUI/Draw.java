package com.company.GUI;

import com.company.Algorithm.Graph;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Draw extends JComponent {
    private Image image;
    private Graphics2D graphics2D;

    private Integer cellWidth;
    private Integer cellHeight;

    private Integer imageWidth = 1000;
    private Integer imageHeight = 900;

    private BufferedImage grass;
    private BufferedImage key;
    private BufferedImage entrance;
    private BufferedImage wall;
    private BufferedImage burGrass = ImageIO.read(new File("bur_grass.jpg"));
    private BufferedImage burEntrance = ImageIO.read(new File("bur_entr.jpg"));
    private BufferedImage burKeyEntrance = ImageIO.read(new File("bur_key_entr.jpg"));
    private BufferedImage burKeyGrass = ImageIO.read(new File("bur_key_grass.jpg"));

    private BufferedImage feetStraightHor = ImageIO.read(new File("feet_strn1.jpg"));
    private BufferedImage feetStraight = ImageIO.read(new File("feet_strn.jpg"));

    private AffineTransformOp rotate(BufferedImage im, double angle) {
        double rotationRequired = Math.toRadians (angle);
        double locationX = (double)im.getWidth() / 2;
        double locationY = (double)im.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        return op;
    }

    void drawFeetStraight(Integer coordX, Integer coordY, double angle, Boolean isHor) {
        Integer x1 = 50 + cellWidth * coordX;
        Integer y1 = 100 + cellHeight * coordY;
        if (isHor) {
            graphics2D.drawImage(rotate(feetStraightHor, angle).filter(feetStraightHor, null),
                    x1, y1, cellWidth, cellHeight, this);
        } else {
            graphics2D.drawImage(rotate(feetStraight, angle).filter(feetStraight, null),
                    x1, y1, cellWidth, cellHeight, this);
        }
    }

    Draw() throws IOException {
        setDoubleBuffered(true);
        cellHeight = 0;
        cellWidth = 0;
        key = ImageIO.read(new File("key.jpg"));
        grass = ImageIO.read(new File("grass.jpg"));
        entrance = ImageIO.read(new File("entrance.jpg"));
        wall = ImageIO.read(new File("wall.jpg"));
    }

    public void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(imageWidth, imageHeight);
            graphics2D = (Graphics2D)image.getGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }
    void clear(){
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        repaint();
    }

    void paintRect(Graph graph) {
        int x = 50;
        int y =  100;
        cellWidth = 700 / graph.getLabirinthWidth();
        cellHeight = 450 / graph.getLabirinthHeight();
        int x1 = x;
        int y1 = y;
        int x2 = x;
        int y2 = y + 450;
        for (int i = 0; i <= graph.getLabirinthWidth(); ++i) {
            graphics2D.drawLine(x1, y1, x2, y2);
            x1 += cellWidth;
            x2 += cellWidth;
        }
        x1 = x;
        y1 = y;
        x2 = x + 700;
        y2 = y;
        for (int i = 0; i <= graph.getLabirinthHeight(); ++i) {
            graphics2D.drawLine(x1, y1, x2, y2);
            y1 += cellHeight;
            y2 += cellHeight;
        }
        repaint();
    }

    void drawClosedRoom(Graph graph, int x, int y) throws IOException {
        Integer nVert = (y - 100) / cellHeight;
        Integer nHor = (x - 50) / cellWidth;

        Integer x1 = 50 + nHor * cellWidth;
        Integer y1 = 100 + nVert * cellHeight;
        if (nVert >= 0 && nVert < graph.getLabirinthHeight()
                && nHor >=0 && nHor < graph.getLabirinthWidth()) {
            graphics2D.drawImage(wall, x1, y1, cellWidth, cellHeight, null);
            graph.setVertex(nVert, nHor, 1);
        }
        repaint(new Rectangle(x1, y1, cellWidth, cellHeight));
    }

    void drawOpenRoom(Graph graph, int x, int y) throws IOException {
        Integer nVert = (y - 100) / cellHeight;
        Integer nHor = (x - 50) / cellWidth;

        Integer x1 = 50 + nHor * cellWidth;
        Integer y1 = 100 + nVert * cellHeight;
        if (nVert >= 0 && nVert < graph.getLabirinthHeight()
                && nHor >=0 && nHor < graph.getLabirinthWidth()) {
            graphics2D.drawImage(grass, x1, y1, cellWidth, cellHeight, null);
            graph.setVertex(nVert, nHor, 0);
        }
        repaint(new Rectangle(x1, y1, cellWidth, cellHeight));
    }

    void drawEntrance(Graph graph, int x, int y) throws IOException {
        Integer nVert = (y - 100) / cellHeight;
        Integer nHor = (x - 50) / cellWidth;

        Integer x1 = 50 + nHor * cellWidth;
        Integer y1 = 100 + nVert * cellHeight;
        if (nVert >= 0 && nVert < graph.getLabirinthHeight()
                && nHor >=0 && nHor < graph.getLabirinthWidth()) {
            graphics2D.drawImage(entrance, x1, y1, cellWidth, cellHeight, null);
        }
        super.paintComponent(graphics2D);
        graph.setBeginX(nVert);
        graph.setBeginY(nHor);
        repaint(new Rectangle(x1, y1, cellWidth, cellHeight));
    }

    void drawKey(Graph graph, int x, int y) throws IOException {
        Integer nVert = (y - 100) / cellHeight;
        Integer nHor = (x - 50) / cellWidth;

        Integer x1 = 50 + nHor * cellWidth;
        Integer y1 = 100 + nVert * cellHeight;

        if (nVert >= 0 && nVert < graph.getLabirinthHeight()
                && nHor >=0 && nHor < graph.getLabirinthWidth()) {
            graphics2D.drawImage(key, x1, y1, cellWidth, cellHeight, null);
        }
        graph.setEndX(nVert);
        graph.setEndY(nHor);
        repaint(new Rectangle(x1, y1, cellWidth, cellHeight));
    }

    void drawWithoutKey(Integer coordX, Integer coordY) {
        Integer y1 = 100 + cellHeight * coordY;
        Integer x1 = 50 + cellWidth * coordX;
        graphics2D.drawImage(burGrass, x1, y1, cellWidth, cellHeight, null);
        repaint();
    }

    void drawWithoutKeyEntrance(Integer coordX, Integer coordY) {
        Integer y1 = 100 + cellHeight * coordY;
        Integer x1 = 50 + cellWidth * coordX;
        graphics2D.drawImage(burEntrance, x1, y1, cellWidth, cellHeight, null);
        repaint();
    }

    void drawWithKey(Integer coordX, Integer coordY) {
        Integer x1 = 50 + cellWidth * coordX;
        Integer y1 = 100 + cellHeight * coordY;
        graphics2D.drawImage(burKeyGrass, x1, y1, cellWidth, cellHeight, null);
        repaint();
    }

    void drawWithKeyEntrance(Integer coordX, Integer coordY) {
        Integer x1 = 50 + cellWidth * coordX;
        Integer y1 = 100 + cellHeight * coordY;
        graphics2D.drawImage(burKeyEntrance, x1, y1, cellWidth, cellHeight, null);
        repaint();
    }

    void drawEntrance(Integer coordX, Integer coordY) {
        Integer x1 = 50 + cellWidth * coordX;
        Integer y1 = 100 + cellHeight * coordY;
        graphics2D.drawImage(entrance, x1, y1, cellWidth, cellHeight, null);
        repaint();
    }

    void drawGraph(ArrayList<ArrayList<Integer>> array, Graph graph) {
        for (int i = 0; i < array.size(); ++i) {
            for (int j = 0; j < array.get(0).size(); ++j) {
                Integer x1 = 50 + j * cellWidth;
                Integer y1 = 100 + i * cellHeight;
                if (array.get(i).get(j).equals(0)) {
                    graphics2D.drawImage(grass, x1, y1, cellWidth, cellHeight, null);
                } else if (array.get(i).get(j).equals(1)) {
                    graphics2D.drawImage(wall, x1, y1, cellWidth, cellHeight, null);
                }
            }
        }
        Integer x1 = 50 + graph.getBeginY() * cellWidth;
        Integer y1 = 100 + graph.getBeginX() * cellHeight;
        graphics2D.drawImage(entrance, x1, y1, cellWidth, cellHeight, null);
        x1 = 50 + graph.getEndY() * cellWidth;
        y1 = 100 + graph.getEndX() * cellHeight;
        graphics2D.drawImage(key, x1, y1, cellWidth, cellHeight, null);
        repaint();
    }
}
