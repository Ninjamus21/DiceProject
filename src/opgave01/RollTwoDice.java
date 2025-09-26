package opgave01;

import java.util.Scanner;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int sum = 0;
    private static int sameDice = 0;
    private static int lowSum = 0;
    private static int highSum = 0;
    private static int[] count = new int[6];


    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul en terning.");
        printRules();
        System.out.println();

        int[] dice = rollDices();
        playTwoDie();
        System.out.println();
        System.out.println("Tak for at spille, rul en terning.");

    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller en terning, så længde man lyster.");
        System.out.println("=====================================================");
    }


    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

    private static void updateStatistics(int[] dice) {
        rollCount = rollCount + 2;
        sum += rollDices()[0] + rollDices()[1];
        lowSum = dice[0] + dice[1];
        if (lowSum > highSum){
            highSum = lowSum;
        }
        if(dice[0] == dice[1]){
            sameDice++;
        }

        for (int index = 0; index < dice.length; index++) {
            count[dice[index] - 1]++;
        }
    }

    public static void printArray(int[] array) {
        for (int index = 0; index < array.length; index++) {
            System.out.println("antal: " + (index + 1) + " er "+ array[index]);
        }
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);
        System.out.println("Sum: " + sum);
        System.out.println("Samme terning: " + sameDice);
        System.out.println("højeste øjne er: " + highSum);
        printArray(count);

    }

    private static void playTwoDie() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        while (!answer.equals("nej")) {
            int[] dice = rollDices();
            System.out.println("du rullede " + dice[0] + " og " + dice[1]);
            System.out.println();

            updateStatistics(dice);

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    public static int[] rollDices() {
        int dice1 = rollDie();
        int dice2 = rollDie();
        return new int[]{dice1, dice2};
    }
}

