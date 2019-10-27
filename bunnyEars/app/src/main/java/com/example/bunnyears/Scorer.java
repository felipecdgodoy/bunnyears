package com.example.bunnyears;

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Scorer {

    private static Random rand = new Random();

    public static int score(List<String> solutionAL, List<String> answerAL) {
        replace(solutionAL);
        replace(answerAL);
        int score = 0;
        // Comparision: word per word
        for (int i = 0; i < answerAL.size(); i++) {
            if (solutionAL.contains(answerAL.get(i))) {
                score += 1; // +1 point for contains word
                if (answerAL.get(i).equals(solutionAL.get(Math.min(i, solutionAL.size() - 1)))) { // prevents out of bonds
                    score += 1.05; // +1 point for correct position
                } else if (i - 1 >= 0 && i + 1 < solutionAL.size()) {
                    if (answerAL.get(i).equals(solutionAL.get(i - 1)) || answerAL.get(i).equals(solutionAL.get(i + 1))) {
                        score += 0.95; // +0.95 point for one position off
                    }
                } else if (i - 2 >= 0 && i + 2 < solutionAL.size()) {
                    if (answerAL.get(i).equals(solutionAL.get(i - 2)) || answerAL.get(i).equals(solutionAL.get(i + 2))) {
                        score += 0.85; // +0.85 point for two positions off
                    }
                } else if (i - 3 >= 0 && i + 3 < solutionAL.size()) {
                    if (answerAL.get(i).equals(solutionAL.get(i - 3)) || answerAL.get(i).equals(solutionAL.get(i + 3))) {
                        score += 0.75; // +0.75 point for three positions off
                    }
                }
            } // close if contains
        } // close for loop
        double mult;
        if (solutionAL.size() >= 10) { // fixed bias towards longer texts
            mult = 1.6;
        } else if (solutionAL.size() >= 7) {
            mult = 1.8;
        } else {
            mult = 2.0;
        }
        double result = Math.ceil((double) 1181.0 * (score / (mult * Math.min(solutionAL.size(), answerAL.size()))));
        result = Math.min(result, 1000); // max score is 1000
        result = Math.max(result, 200 + (Math.max(rand.nextInt(201), result) / 10)); // min score is between [200, 220]
        return (int) result;
    }
    public static void replace(List<String> strings)
    {
        ListIterator<String> iterator = strings.listIterator();
        while (iterator.hasNext())
        {
            iterator.set(iterator.next().toLowerCase());
        }
    }
}
