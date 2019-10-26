package battleship;

import java.util.Random;

/**
 * Ocean - class that contains game field and works with Ship classes
 *
 * @author Kara Dmitry
 */
public class Ocean {

    private static final int SIZE = 10;
    private static final char EMPTY = '?';
    private static final Random random = new Random();

    private static final int BATTLESHIP_COUNT = 1;
    private static final int CRUISER_COUNT = 2;
    private static final int DESTROYER_COUNT = 3;
    private static final int SUBMARINE_COUNT = 4;

    Ship[][] ships = new Ship[SIZE][SIZE];
    int shotsFired;
    int hitCount;
    int shipsSunk;

    /**
     * Boolean table which sells are already hit
     */
    boolean[][] visible = new boolean[SIZE][SIZE];

    Ocean() {
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;

        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[i].length; j++) {
                ships[i][j] = new EmptySea();
            }
        }

        for (int i = 0; i < visible.length; i++) {
            for (int j = 0; j < visible[i].length; j++) {
                visible[i][j] = false;
            }
        }
    }

    /**
     * Place one ship on the Ocean table
     */
    private void placeOneShipRandomly(Ship ship) {
        int row, column;
        boolean horizontal;

        while (true) {
            row = random.nextInt(9);
            column = random.nextInt(9);
            horizontal = random.nextBoolean();

            if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
                ship.placeShipAt(row, column, horizontal, this);
                return;
            }
        }
    }

    /**
     * Fill the Ocean table with ships
     */
    public void placeAllShipsRandomly() {
        for (int i = 0; i < BATTLESHIP_COUNT; i++) {
            placeOneShipRandomly(new Battleship());
        }
        for (int i = 0; i < CRUISER_COUNT; i++) {
            placeOneShipRandomly(new Cruiser());
        }
        for (int i = 0; i < DESTROYER_COUNT; i++) {
            placeOneShipRandomly(new Destroyer());
        }
        for (int i = 0; i < SUBMARINE_COUNT; i++) {
            placeOneShipRandomly(new Submarine());
        }
    }

    /**
     * @param row    row of Ocean table
     * @param column column of Ocean table
     * @return is there is a ship, not EmptySea
     */
    public boolean isOccupied(int row, int column) {
        return ships[row][column].isEmptySea();
    }

    /**
     * @param row    row of Ocean table
     * @param column column of Ocean table
     * @return can we shoot here
     */
    public boolean shootAt(int row, int column) throws BattleShipGameException {
        if (visible[row][column]){
            ++shotsFired;
            throw new BattleShipGameException("Already hit");
        }

        visible[row][column] = true;
        if (ships[row][column].isEmptySea()) {
            ++shotsFired;
            return false;
        }
        ships[row][column].shootAt(row, column);
        ++shotsFired;
        ++hitCount;
        if (ships[row][column].isSunk())
            ++shipsSunk;
        return true;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }

    public boolean isGameOver() {
        return shipsSunk == 10;
    }

    public Ship[][] getShipArray() {
        return ships;
    }

    public void print() {
        System.out.print("  ");
        for (int j = 0; j < 10; j++) {
            System.out.printf(" %d", j);
        }
        System.out.println();
        for (int i = 0; i < ships.length; i++) {
            System.out.printf(" %d", i);
            for (int j = 0; j < ships[i].length; j++) {
                if (visible[i][j])
                    System.out.printf(" %s", ships[i][j].toString());
                else
                    System.out.printf(" %s", EMPTY);
            }
            System.out.printf("\n");
        }
    }
}
