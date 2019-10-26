package battleship;

public class BattleShipGameException extends Exception {

    public BattleShipGameException() { super(); }

    public BattleShipGameException(String message) { super(message); }

    public BattleShipGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public BattleShipGameException(Throwable cause) {
        super(cause);
    }
}
