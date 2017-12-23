package com.company.GUI;

import com.company.Algorithm.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Gui extends JFrame {
    private JLabel label = new JLabel("Enter the height and the width of your labirinth:");
    private JLabel labelWidth = new JLabel("width:");
    private JLabel labelHeight = new JLabel("height:");

    private JTextField inputWidth = new JTextField("", 5);
    private JTextField inputHeight = new JTextField("", 6);
    private JButton buttonConfirmSize = new JButton("Confirm");
    private JPanel panel = new JPanel();
    private Graph graph;
    private Draw draw = new Draw();

    private JButton closedRoom = new JButton("Closed room");
    private JButton openRoom = new JButton("Open room");
    private JButton entrance = new JButton("Entrance");
    private JButton key = new JButton("Key");

    private Integer type = 0; //0 for open, 1 for closed, 2 for entrance, 3 for key
    private Integer nextStepIndex = 0;

    private JDialog dialog;

    private JButton beginWalk = new JButton("Begin my walk!");
    private JButton random = new JButton("Randomize");
    private JButton next = new JButton("Next Step");
    private JButton generateLab = new JButton("Generate labyrinth");

    private int gotKey = 0; //0 for entr-key, 1 for bur-key, 2 for bur+key, 3 for entr+key
    private ArrayList<Integer> path;
    private JComboBox<String> tests = new JComboBox();

    public Gui() throws IOException {
        super("BURATINO");
        this.setBounds(100, 100, 1000, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initDialog();
        initPanel();
        initButtons();
        initTests();
        getContentPane().add(panel);
        draw.setBounds(0, 60, getContentPane().getWidth(), getContentPane().getHeight());
        draw.addMouseListener(new MouseClickListener());
        getContentPane().add(draw);
        setPreferredSize(new Dimension(700, 1000));
    }

    private void initDialog() {
        dialog = new JDialog(this, "attention", true);
        dialog.setBounds(400, 300, 150, 100);
        dialog.add(new JLabel("Can`t find way out!"));
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void initPanel() {
        panel.setLayout(null);
        panel.setBackground(new Color(192, 110, 99));
        panel.setBounds(0, 0, 1000, 80);
        label.setBounds(10, 10, 300, 20);
        panel.add(label);
        labelWidth.setBounds(310, 10, 40, 20);
        panel.add(labelWidth);
        inputWidth.setBounds(355, 10, 40, 20);
        panel.add(inputWidth);
        labelHeight.setBounds(400, 10, 40, 20);
        panel.add(labelHeight);
        inputHeight.setBounds(445, 10, 40, 20);
        panel.add(inputHeight);
    }

    private void initButtons() {
        buttonConfirmSize.setBounds(10, 30, 100, 20);
        panel.add(buttonConfirmSize);
        random.setBounds(120, 30, 100, 20);
        random.addActionListener(new RandomButton());
        panel.add(random);
        generateLab.setBounds(230, 30, 200, 20);
        panel.add(generateLab);
        generateLab.addActionListener(new GenerateLabyrinthListener());
        closedRoom.setBounds(10, 55, 130, 20);
        panel.add(closedRoom);
        openRoom.setBounds(150, 55, 100, 20);
        panel.add(openRoom);
        entrance.setBounds(260, 55, 100, 20);
        panel.add(entrance);
        key.setBounds(370, 55, 100, 20);
        panel.add(key);

        beginWalk.setBounds(480, 55, 200, 20);
        panel.add(beginWalk);
        beginWalk.addActionListener(new PathBuilder());

        next.setBounds(690, 55, 100, 20);
        panel.add(next);

        next.addActionListener(new NextStepAction());
        buttonConfirmSize.addActionListener(new ButtonEventListener());
        closedRoom.addActionListener(e -> type = 1);
        openRoom.addActionListener(e -> type = 0);
        entrance.addActionListener(e -> type = 2);
        key.addActionListener(e -> type = 3);
    }

    class GenerateLabyrinthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beginWalk.setEnabled(true);
            next.setEnabled(true);
            ArrayList<ArrayList<Integer>> array = new ArrayList<>();
            Integer width = Integer.parseInt(inputWidth.getText());
            Integer height = Integer.parseInt(inputHeight.getText());
            Integer N =  width * height;

            for (int i = 0; i < height; ++i) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int j = 0; j < width; ++j) {
                    tmp.add(1);
                }
                array.add(tmp);
            }

            Integer beginI = ThreadLocalRandom.current().nextInt(0, height);
            Integer beginJ = ThreadLocalRandom.current().nextInt(0, width);
            Integer freeCells = 0;
            final Integer waysCount = 4;
            ArrayList<Integer> currX = new ArrayList<>();
            ArrayList<Integer> currY = new ArrayList<>();
            for (int k = 0; k < waysCount; ++k) {
                currX.add(beginI);
                currY.add(beginJ);
            }
            while (freeCells < N/2) {
                for (int k = 0; k < waysCount; ++k) {
                    int dx[] = {1, 0, -1, 0};
                    int dy[] = {0, 1, 0, -1};
                    Integer i = (ThreadLocalRandom.current().nextInt(0, 4));
                    Integer x = currX.get(k) + dx[i];
                    Integer y = currY.get(k) + dy[i];
                    if (x >= 0 && x < height && y >= 0 && y < width) {
                        array.get(x).set(y, 0);
                        currX.set(k, x);
                        currY.set(k, y);
                        ++freeCells;
                    }
                }
            }
            for (int k = 0; k < waysCount - 1; ++k) {
                if (currX.get(k) < currX.get(k+1)) {
                        for (int i = currX.get(k); i <= currX.get(k+1); ++i) {
                            array.get(i).set(currY.get(k), 0);
                        }
                    } else {
                        for (int i = currX.get(k); i >= currX.get(k+1); --i) {
                            array.get(i).set(currY.get(k), 0);
                        }
                }
                if (currY.get(k) < currY.get(k+1)) {
                    for (int i = currY.get(k); i <= currY.get(k+1); ++i) {
                        array.get(currX.get(k)).set(i, 0);
                    }
                } else {
                    for (int i = currY.get(k); i >= currY.get(k+1); --i) {
                        array.get(currX.get(k)).set(i, 0);
                    }
                }
            }
            graph = new Graph(array);
            graph.setBeginI(beginI);
            graph.setBeginJ(beginJ);
            graph.setEndI(currX.get(1));
            graph.setEndJ(currY.get(1));
            draw.drawGraph(array, graph);
        }
    }

    private void initTests() {
        String[] items = {" ", "Test 1", "Test 2", "Test 3"};
        tests = new JComboBox<>(items);

        tests.setBounds(800, 55, 150, 20);
        panel.add(tests);
        tests.addItemListener(new TestsListener());
    }

    private void doTest(String filename) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            for (int i = 0; i < n; ++i) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int j = 0; j < m; ++j) {
                    tmp.add(scanner.nextInt());
                }
                array.add(tmp);
            }
            graph = new Graph(array);
            graph.setBeginI(scanner.nextInt());
            graph.setBeginJ(scanner.nextInt());
            graph.setEndI(scanner.nextInt());
            graph.setEndJ(scanner.nextInt());
            draw.paintRect(graph);
            draw.drawGraph(array, graph);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        class TestsListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = e.getItem().toString();
                switch(item) {
                    case "Test 1":
                        doTest("test1.txt");
                        break;
                    case "Test 2":
                        doTest("test2.txt");
                        break;
                    case "Test 3":
                        doTest("test3.txt");
                        break;
                }
            }
        }
    }

    private void drawFeet(Integer coordX, Integer coordY, Integer coordXPrev, Integer coordYPrev) throws IOException {
        if (coordXPrev.equals(graph.getBeginJ()) && coordYPrev.equals(graph.getBeginI())) {
            draw.drawEntrance(coordXPrev, coordYPrev);
        } else {
            if (coordY.equals(coordYPrev)) {
                int compare = coordX.compareTo(coordXPrev);
                if (compare > 0) {
                    draw.drawFeetStraight(coordXPrev, coordYPrev, 0, true);
                } else if (compare < 0) {
                    draw.drawFeetStraight(coordXPrev, coordYPrev, 180, true);
                }
            } else if (coordY.compareTo(coordYPrev) > 0) {
                draw.drawFeetStraight(coordXPrev, coordYPrev, 180, false);
            } else if (coordY.compareTo(coordYPrev) < 0) {
                draw.drawFeetStraight(coordXPrev, coordYPrev, 0, false);
            }
        }
    }

    class NextStepAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (path == null) {
                path = graph.findPath();
                if (path.isEmpty() || path.contains(-1)) {
                    dialog.setVisible(true);
                    next.setEnabled(false);
                }
            }
            Integer coordX = path.get(nextStepIndex) % graph.getLabirinthWidth();
            Integer coordY = path.get(nextStepIndex) / graph.getLabirinthWidth();
            if (gotKey == 0) {
                draw.drawWithoutKeyEntrance(coordX, coordY);
                ++nextStepIndex;
                if (path.get(nextStepIndex).equals(graph.getVerticeNumber(graph.getEndI(), graph.getEndJ()))) {
                    gotKey = 2;
                } else {
                    gotKey = 1;
                }
            } else if (gotKey == 1) {
                draw.drawWithoutKey(coordX, coordY);
                Integer coordXPrev = path.get(nextStepIndex - 1) % graph.getLabirinthWidth();
                Integer coordYPrev = path.get(nextStepIndex - 1) / graph.getLabirinthWidth();
                try {
                    drawFeet(coordX, coordY, coordXPrev, coordYPrev);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ++nextStepIndex;
                if (path.get(nextStepIndex).equals(graph.getVerticeNumber(graph.getEndI(), graph.getEndJ()))) {
                    gotKey = 2;
                }
            } else if (gotKey == 2) {
                draw.drawWithKey(coordX, coordY);
                Integer coordXPrev = path.get(nextStepIndex - 1) % graph.getLabirinthWidth();
                Integer coordYPrev = path.get(nextStepIndex - 1) / graph.getLabirinthWidth();
                try {
                    drawFeet(coordX, coordY, coordXPrev, coordYPrev);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                ++nextStepIndex;
                if (path.get(nextStepIndex).equals(graph.getVerticeNumber(graph.getBeginI(), graph.getBeginJ()))) {
                    gotKey = 3;
                }
            } else if (gotKey == 3) {
                draw.drawWithKeyEntrance(coordX, coordY);
                Integer coordXPrev = path.get(nextStepIndex - 1) % graph.getLabirinthWidth();
                Integer coordYPrev = path.get(nextStepIndex - 1) / graph.getLabirinthWidth();
                try {
                    drawFeet(coordX, coordY, coordXPrev, coordYPrev);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                next.setEnabled(false);
                nextStepIndex = 0;
                path = null;
                gotKey = 0;
                beginWalk.setEnabled(false);
            }
        }
    }

    class RandomButton implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<ArrayList<Integer>> array = new ArrayList<>();
            Integer width = Integer.parseInt(inputWidth.getText());
            Integer height = Integer.parseInt(inputHeight.getText());
            for (int i = 0; i < height; ++i) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int j = 0; j < width; ++j) {
                    tmp.add(ThreadLocalRandom.current().nextInt(0, 2));
                }
                array.add(tmp);
            }
            graph = new Graph(array);
            graph.setBeginI(ThreadLocalRandom.current().nextInt(0, graph.getLabirinthHeight()));
            graph.setEndI(ThreadLocalRandom.current().nextInt(0, graph.getLabirinthHeight()));
            graph.setBeginJ(ThreadLocalRandom.current().nextInt(0, graph.getLabirinthWidth()));
            graph.setEndJ(ThreadLocalRandom.current().nextInt(0, graph.getLabirinthWidth()));
            graph.setVertex(graph.getBeginI(), graph.getBeginJ(), 0);
            graph.setVertex(graph.getEndI(), graph.getEndJ(), 0);
            draw.drawGraph(array, graph);
        }
    }

    class MouseClickListener implements  MouseListener {
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                Integer mouseX = e.getX();
                Integer mouseY = e.getY();
                if (type.equals(0)) {
                    try {
                        draw.drawOpenRoom(graph, mouseX, mouseY);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (type.equals(1)) {
                    try {
                        draw.drawClosedRoom(graph, mouseX, mouseY);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (type.equals(2)) {
                    try {
                        draw.drawEntrance(graph, mouseX, mouseY);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (type.equals(3)) {
                    try {
                        draw.drawKey(graph, mouseX, mouseY);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }
    }
    private Integer drawPathToKey(ArrayList<Integer> path, Graph graph) throws InterruptedException, IOException {
        Integer key = graph.getVerticeNumber(graph.getEndI(), graph.getEndJ());
        Integer index = 0;
        Integer coordY = path.get(index) / graph.getLabirinthWidth();
        Integer coordX = path.get(index) % graph.getLabirinthWidth();
        draw.drawWithoutKeyEntrance(coordX, coordY);
        while (!path.get(index).equals(key)) {
            ++index;
            coordY = path.get(index) / graph.getLabirinthWidth();
            coordX = path.get(index) % graph.getLabirinthWidth();
            Integer coordXPrev = path.get(index - 1) % graph.getLabirinthWidth();
            Integer coordYPrev = path.get(index - 1) / graph.getLabirinthWidth();
            drawFeet(coordX, coordY, coordXPrev, coordYPrev);
        }
        return index;
    }

    private void drawPathToEntrance(ArrayList<Integer> path, Graph graph, Integer index) throws InterruptedException, IOException {
        Integer coordX = path.get(index) % graph.getLabirinthWidth();
        Integer coordY = path.get(index) / graph.getLabirinthWidth();
        draw.drawWithKey(coordX, coordY);
        while(!path.get(index).equals(path.get(0))) {
            ++index;
            coordX = path.get(index) % graph.getLabirinthWidth();
            coordY = path.get(index) / graph.getLabirinthWidth();
            Integer coordXPrev = path.get(index - 1) % graph.getLabirinthWidth();
            Integer coordYPrev = path.get(index - 1) / graph.getLabirinthWidth();
            drawFeet(coordX, coordY, coordXPrev, coordYPrev);
        }
        draw.drawWithKeyEntrance(coordX, coordY);
    }
    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            beginWalk.setEnabled(true);
            next.setEnabled(true);
            Integer width = Integer.parseInt(inputWidth.getText());
            Integer height = Integer.parseInt(inputHeight.getText());
            ArrayList<ArrayList<Integer>> array = new ArrayList<>();
            for (int i = 0; i < height; ++i) {
                ArrayList<Integer> tmp = new ArrayList<>();
                for (int j = 0; j < width; ++j) {
                    tmp.add(0);
                }
                array.add(tmp);
            }
            graph = new Graph(array);
            draw.clear();
            draw.paintRect(graph);
        }
    }

    class PathBuilder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            beginWalk.setEnabled(false);
            next.setEnabled(false);
            Integer index = 0;
            ArrayList<Integer> resultingPath = graph.findPath();
            if (resultingPath.isEmpty()) {
                dialog.setVisible(true);
            } else {
                if (resultingPath.contains(-1)) {
                    try {
                        drawPathToKey(resultingPath, graph);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dialog.setVisible(true);
                } else {
                    try {
                        index = drawPathToKey(resultingPath, graph);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        drawPathToEntrance(resultingPath, graph, index);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
