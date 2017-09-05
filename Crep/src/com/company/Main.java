package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void findWord(Boolean isRegex, Boolean isInverted, Boolean isIgnored, String word, String filename) throws IOException {
        String str;
        BufferedReader in = new BufferedReader(new FileReader(filename));
        File file = new File("output.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
        while ((str = in.readLine()) != null) {
            String regularWord = "";
            if (!isRegex) {
                regularWord = "(\\W|^)(" + word + ")(\\W|$)";
            } else {
                regularWord = word;
            }
            if (isIgnored) {
                regularWord = "(?i)" + regularWord;
            }
            Pattern p = Pattern.compile(regularWord);
            Matcher m = p.matcher(str);
            Boolean find = m.find();
            if ((find && !isInverted) || (!find && isInverted)) {
                out.print(str + System.lineSeparator());
            }
        }
        out.close();
    }
    public static void main(String[] args) throws IOException {
        String filename = args[args.length - 1];
        String word = args[args.length - 2];
        Boolean isRegex = false;
        Boolean isInverted = false;
        Boolean isIgnored = false;
        for (int i = args.length - 3; i >= 0; i--) {
            switch (args[i]) {
                case "-r":
                    isRegex = true;
                    break;
                case "-v":
                    isInverted = true;
                    break;
                case "-i":
                    isIgnored = true;
                    break;
            }
        }
        findWord(isRegex, isInverted, isIgnored, word, filename);
    }
}

