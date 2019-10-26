package battleship;

public class Submarine extends Ship {

    public Submarine() {
        length = 1;
        hit = new boolean[1];
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getShipType() {
        return "submarine";
    }
}
