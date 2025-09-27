package opgave02;

import opgave01.RollTwoDice;

import java.util.Scanner;

public class Craps {
    private static int rollCount = 0;
    private static int sum = 0;
    public static int wins = 0;
    public static int losses = 0;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    public static void main(String[] args) {
        playCraps();

    }

    public static void playCraps() {
        System.out.println("Velkommen til spillet, rul to terninger.");
        RollTwoDice rollTwoDice = new RollTwoDice();
        rollTwoDice.rollDices();
        int[] dice = rollTwoDice.rollDices();
        System.out.println("Du rullede: " + dice[0] + " og " + dice[1]);
        sum = dice[0] + dice[1];
        if (sum == 7 || sum == 11) {
            System.out.println(ANSI_GREEN + "Du har vundet!" + ANSI_RESET);
            wins++;
        } else if (sum == 2 || sum == 3 || sum == 12) {
            System.out.println(ANSI_RED + "Du har tabt!" + ANSI_RESET);
            losses++;
        } else {
            System.out.println("Du har rullet " + sum + " du får den tilføjet til dine point, rul igen");
            int point = sum;
            System.out.println("dine points er: " + point);
            rollForPoints(point);
        }
        System.out.println("Spil igen? (ja/nej)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("ja")) {
            playCraps();
        } else {
            System.out.println("Tak for spille farvel");
            statistics();
        }
    }
    // Roll the 2 dice until point or 7 is rolled.
    // Return true, if point is rolled.
    public static boolean rollForPoints(int point) {
        RollTwoDice rollTwoDice = new RollTwoDice();
        while (true) {
            int[] dice = rollTwoDice.rollDices();
            int sum = dice[0] + dice[1];
            System.out.println("Du rullede: " + dice[0] + " og " + dice[1]);
            if (sum == point) {
                System.out.println(ANSI_GREEN + "Du har vundet!" + ANSI_RESET);
                wins++;
                return true;
            } else if (sum == 7) {
                System.out.println(ANSI_RED + "Du har tabt!" + ANSI_RESET);
                losses++;
                return false;
            } else {
                System.out.println("Rul igen...");

            }
        }
    }
    public static void statistics(){
        System.out.println("Du har vundet " + wins + " gange");
        System.out.println("Du har tabt " + losses + " gange");
    }
}
