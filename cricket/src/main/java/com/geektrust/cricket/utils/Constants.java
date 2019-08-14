package com.geektrust.cricket.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String kohliName = "Kirat Boli";
    public static List<Integer> kohliProbabilities = new ArrayList<>(Arrays.asList(
            5, 30, 25, 10, 15, 1, 9, 5
    ));
    public static final String dhoniName = "NS Nodhi";
    public static List<Integer> dhoniProbabilities = new ArrayList<>(Arrays.asList(
            10, 40, 20, 5, 10, 1, 4, 10
    ));
    public static final String bumrahName = "R Rumrah";
    public static List<Integer> bumrahProbabilities = new ArrayList<>(Arrays.asList(
            20, 30, 15, 5, 5, 1, 4, 20
    ));
    public static final String nehraName = "Shashi Henra";
    public static List<Integer> nehraProbabilities = new ArrayList<>(Arrays.asList(
            30, 25, 5, 0, 5, 1, 4, 30
    ));

    public static int ballsInAnOver = 6;
    public static int numberOfPlayers = 4;
    public static int numberOfOvers = 4;
    public static int runsRequired = 40;
    public static int initialRuns = 0;
    public static int wicketsInHand = 3;

    public static String battingTeamName = "Lengaburu";
    public static String bowlingTeamName = "Enchai";
}
