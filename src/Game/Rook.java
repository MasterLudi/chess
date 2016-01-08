package Game;

import java.util.HashSet;


public class Rook extends Piece {

    public Rook(Color c) {
        super(c);
        pieceType = Type.ROOK;
    }

    @Override
    public boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY) {

        if (!isValidRange(newX, newY))
            return false;

        return checkVertical(game, oldX, oldY, newX, newY)
                || checkHorizontal(game, oldX, oldY, newX, newY);
    }


    @Override
    public void updateReachableCells(Game game, int x, int y) {
        //assuming no old cells are reachable so wiping data in reachablecells
        this.reachableCells = null;
        reachableCells = new HashSet<Cell>();


        Cell[][] board = game.board;

        reachableAcross(game, x, y, 7);
    }

}
