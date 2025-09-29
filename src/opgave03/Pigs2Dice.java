// src/opgave03/Pigs.java
package opgave03;

import opgave01.RollTwoDice;

import java.util.ArrayList;
import java.util.Scanner;

public class Pigs2Dice {
    public static int player1Score = 0;
    public static int player2Score = 0;
    public static ArrayList<Integer> player1AveragePerTurn = new ArrayList<>();
    public static ArrayList<Integer> player2AveragePerTurn = new ArrayList<>();
    public static final int WINNING_SCORE = 100;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RollTwoDice RollTwoDices = new RollTwoDice();

        System.out.println("Welcome to Pig! First to " + WINNING_SCORE + " wins.");
        boolean gameOver = false;
        while (!gameOver) {
            player1Score = playerTurn(scanner, RollTwoDices, player1Score, "Player 1", player1AveragePerTurn);
            if (player1Score >= WINNING_SCORE) {
                System.out.println(ANSI_GREEN + "Player 1 wins!" + ANSI_RESET);
                break;
            }
            player2Score = playerTurn(scanner, RollTwoDices, player2Score, "Player 2", player2AveragePerTurn);
            if (player2Score >= WINNING_SCORE) {
                System.out.println(ANSI_GREEN + "Player 2 wins!" + ANSI_RESET);
                break;
            }
        }
        printAverage(player1AveragePerTurn, "Player 1");
        printAverage(player2AveragePerTurn, "Player 2");
        scanner.close();
    }

    public static int playerTurn(Scanner scanner, RollTwoDice rollOneDie, int score, String playerName, ArrayList <Integer> rollsTracker) {
        int temp = 0;
        int rollsThisTurn = 0;
        System.out.println("\n" + playerName + "'s turn. Current score: " + score);
        while (true) {
            System.out.print("Roll the die? (yes/no): ");
            String answer = scanner.nextLine();
            try {
                if (!answer.equalsIgnoreCase("yes") && !answer.equalsIgnoreCase("no")) {
                    throw new IllegalArgumentException("Invalid input. Please enter 'yes' or 'no'.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (answer.equalsIgnoreCase("no")) {
                score += temp;
                System.out.println(playerName + " holds. Total score: " + score);
                rollsTracker.add(rollsThisTurn);
                break;
            }
            int[] face = RollTwoDice.rollDices();
            rollsThisTurn++;
            System.out.println("You rolled: " + face[0] + " and " + face[1]);
            if (face[0] == 1 && face[1] == 1) {
                System.out.println(ANSI_RED + "Rolled two 1s! Score reset to 0." + ANSI_RESET + "\uD83D\uDCA3");
                score = 0;
                temp = 0;
                rollsTracker.add(rollsThisTurn);
                break;
            } else if (face[0] == 1 || face[1] == 1) {
                System.out.println(ANSI_YELLOW + "You rolled a singular 1. No points this turn." + ANSI_RESET);
                temp = 0;
                rollsTracker.add(rollsThisTurn);
                break;
            } else {
                temp += face[0] + face[1];
                System.out.println("Turn points: " + temp);
                if (score + temp >= WINNING_SCORE) {
                    score += temp;
                    rollsTracker.add(rollsThisTurn);
                    break;
                }
            }
        }
        return score;
    }
    public static void printAverage(ArrayList<Integer> rollsTracker, String playername) {
        int totalRolls = rollsTracker.stream().mapToInt(Integer::intValue).sum();
        //syntax sugar ^^ for the boys
        double average = rollsTracker.size() > 0 ? (double) totalRolls / rollsTracker.size() : 0.0;
        System.out.println("\nStatistics:" + playername);
        System.out.println("Total rolls: " + totalRolls);
        System.out.printf("Average rolls per turn: %.2f\n", average);
    }
}
