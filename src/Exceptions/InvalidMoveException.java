package Exceptions;

/**
 * Thrown when the move is invalid.
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        System.out.println("Invalid move!");
    }
}

