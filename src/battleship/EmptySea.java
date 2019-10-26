package battleship;

public class EmptySea extends Ship {

    public EmptySea() {
        length = 1;
    }

    @Override
    public boolean shootAt(int row, int column) {
        return false;
    }

    /**
     * @return is ship type is EmptySea
     */
    @Override
    public boolean isEmptySea() {
        return true;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getShipType() {
        return null;
    }

    @Override
    public String toString() {
        return "*";
    }
}
