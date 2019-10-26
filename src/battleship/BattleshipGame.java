package battleship;

import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main Class
 *
 * @author Kara Dmitry
 */
public class BattleshipGame {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            gameplay(createGame());

            if (!playAgain()) {
                System.out.println("See you!");
                break;
            }
            clearScreen();
        }

        System.out.println("Goodbye!");
    }

    public static Ocean createGame() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        return ocean;
    }

    public static void gameplay(Ocean ocean) {

        print(ocean);

        while (!ocean.isGameOver()) {
            int[] coordinates = parseInput();

            try {
                ocean.shootAt(coordinates[0], coordinates[1]);
                print(ocean);
            } catch (BattleShipGameException ex) {
                System.out.println(ex.getMessage());
                print(ocean);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Coordinates out of bounds");
            }
        }
    }

    public static boolean playAgain() {
        System.out.println("Play again? Y/n");
        String input;
        while (true) {
            input = SCANNER.nextLine().strip().toLowerCase();
            HashSet<String> agreeCollection =
                    new HashSet<String>(Arrays.asList("y", "yes", "да", "н"));
            HashSet<String> disagreeCollection =
                    new HashSet<String>(Arrays.asList("n", "no", "нет", "т"));
            if (agreeCollection.contains(input)) return true;
            if (disagreeCollection.contains(input)) return false;
            System.out.println("I don't understand :(");
        }
    }

    public static int[] parseInput() {
        System.out.println("Input 2 coordinates from 0 to 9: ");

        int[] coordinates = new int[2];

        while (true) {
            String[] numbers = new String[2];
            try {
                numbers = SCANNER.nextLine().strip().split("[ ,]+");
            } catch (NoSuchElementException skip) {
                System.out.println("Maybe next time");
                System.exit(0);
            }

            if (numbers.length < coordinates.length) {
                System.out.println("Input 2 coordinates please");
                continue;
            }
            try {
                for (int i = 0; i < coordinates.length; i++) {
                    coordinates[i] = Integer.parseInt(numbers[i]);
                }
            } catch (NumberFormatException skip) {
                System.out.println("Input integers please");
                continue;
            }
            return coordinates;
        }
    }

    /**
     * Cleans console screen
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(Ocean ocean) {
        clearScreen();
        ocean.print();
        System.out.printf("All shots:  %d \n", ocean.getShotsFired());
        System.out.printf("All hits:   %d \n", ocean.getHitCount());
        System.out.printf("Sunk ships: %d \n", ocean.getShipsSunk());
    }
}
