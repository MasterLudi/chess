package Game;

import java.util.HashSet;


/**
 * Fairy piece. Moves like Bishop but only goes 1 square.
 */
public class Ferz extends Piece {
    public Ferz(Color c) {
        super(c);
        pieceType = Type.FERZ;
    }

    @Override
    public boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY) {
        if (!isValidRange(newX, newY))
            return false;

        else if (Math.abs(oldX-newX) != 1 || Math.abs(oldY-newY) != 1)
            return false;

        return (game.board[oldX][oldY].getColor() != game.board[newX][newY].getColor());
    }

    @Override
    public void updateReachableCells(Game game, int x, int y) {
        //assuming no old cells are reachable so wiping data in reachablecells
        this.reachableCells = null;
        reachableCells = new HashSet<Cell>();

        Cell[][] board = game.board;

        //add reachable diagonals
        reachableDiagonal(game,x,y,1);

    }
}
