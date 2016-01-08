package Game;
import java.util.HashSet;


public class Bishop extends Piece {
    public Bishop(Color c) {
        super(c);
        pieceType = Type.BISHOP;
    }

    @Override
    public boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY) {

        if (!isValidRange(newX, newY))
            return false;

        return checkDiagonal(game, oldX, oldY, newX, newY);

    }

    @Override
    public void updateReachableCells(Game game, int x, int y) {
        //assuming no old cells are reachable so wiping data in reachablecells
        this.reachableCells = null;
        reachableCells = new HashSet<Cell>();

        Cell[][] board = game.board;

        //add reachable diagonals
        reachableDiagonal(game, x, y, 7);

    }

}
