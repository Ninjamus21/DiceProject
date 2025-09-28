
package opgave03;

import examples.RollOneDie;

import java.util.ArrayList;
import java.util.Scanner;

public class Pigs {
    public static int player1Score = 0;
    public static int player2Score = 0;
    public static ArrayList<Integer> player1AveragePerTurn = new ArrayList<>();
    public static ArrayList<Integer> player2AveragePerTurn = new ArrayList<>();
    public static final int WINNING_SCORE = 100;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RollOneDie rollOneDie = new RollOneDie();

        System.out.println("Welcome to Pig! First to " + WINNING_SCORE + " wins.");
        boolean gameOver = false;
        while (!gameOver) {
            player1Score = playerTurn(scanner, rollOneDie, player1Score, "Player 1", player1AveragePerTurn);
            if (player1Score >= WINNING_SCORE) {
                System.out.println(ANSI_GREEN + "Player 1 wins!" + ANSI_RESET);
                break;
            }
            player2Score = playerTurn(scanner, rollOneDie, player2Score, "Player 2", player2AveragePerTurn);
            if (player2Score >= WINNING_SCORE) {
                System.out.println(ANSI_GREEN + "Player 2 wins!" + ANSI_RESET);
                break;
            }
        }
        printAverage(player1AveragePerTurn, "Player 1");
        printAverage(player2AveragePerTurn, "Player 2");
        scanner.close();
    }

    public static int playerTurn(Scanner scanner, RollOneDie rollOneDie, int score, String playerName, ArrayList <Integer> rollsTracker) {
        int temp = 0;
        int rollsThisTurn = 0;
        System.out.println("\n" + playerName + "'s turn. Current score: " + score);
        while (true) {
            System.out.print("Roll the die? (yes/no): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("no")) {
                score += temp;
                System.out.println(playerName + " holds. Total score: " + score);
                rollsTracker.add(rollsThisTurn);
                break;
            }
            int face = rollOneDie.rollDie();
            rollsThisTurn++;
            System.out.println("You rolled: " + face);
            if (face == 1) {
                System.out.println( ANSI_RED + "Rolled a 1! No points this turn." + ANSI_RESET);
                temp = 0;
                rollsTracker.add(rollsThisTurn);
                break;
            } else {
                temp += face;
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
        double average = (double) totalRolls / rollsTracker.size();
        System.out.println("\nStatistics:" + playername);
        System.out.println("Total rolls: " + totalRolls);
        System.out.printf("Average rolls per turn: %.2f\n", average);
    }
}
