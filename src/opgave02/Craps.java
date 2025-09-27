package opgave02;

import opgave01.RollTwoDice;

import java.util.Scanner;

public class Craps {
    private static int rollCount = 0;
    private static int sum = 0;

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
            System.out.println("Du har vundet!");
        } else if (sum == 2 || sum == 3 || sum == 12) {
            System.out.println("Du har tabt!");
        } else {
            System.out.println("Du har rullet " + sum + "du får den tilføjet til dine point, rul igen");
            int point = sum;
            System.out.println("dine points er: " + point);
            rollForPoints(point);

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
                System.out.println("Du har vundet!");
                return true;
            } else if (sum == 7) {
                System.out.println("Du har tabt!");
                return false;
            } else {
                System.out.println("Rul igen...");

            }
        }
    }
}
