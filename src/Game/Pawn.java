package Game;

import java.util.HashSet;


public class Pawn extends Piece {
    public boolean unmoved = true;

    public boolean isUnmoved() {
        return unmoved;
    }

    public Pawn(Color c) {
        super(c);
        pieceType = Type.PAWN;
    }

    @Override
    public boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY) {

        // checks the range
        if (!isValidRange(newX, newY))
            return false;

        // checks for the diagonal, only when the color is different
        if (((newY - oldY == 1 && color == Color.WHITE) || (newY - oldY == -1 && color == Color.BLACK))
                && (Math.abs(oldX - newX) == 1)) {
            return ((game.board[oldX][oldY].getColor() != game.board[newX][newY].getColor())
                    && game.board[newX][newY].piece != null);
        }

        if (!checkVertical(game, oldX, oldY, newX, newY))
            return false;

        if (game.board[newX][newY].piece != null)
            return false;

        if (newY-oldY == 1 && color == Color.WHITE) {
            return true;
        } else if (newY-oldY == -1 && color == Color.BLACK) {
            return true;
        } else if (unmoved && (newY-oldY == 2) && color == Color.WHITE) {
            unmoved = false;
            return true;
        } else if (unmoved && (newY-oldY == -2) && color == Color.BLACK) {
            unmoved = false;
            return true;
        }

        return false;
    }

    public void updateReachableCells(Game game, int x, int y) {
        //assuming no old cells are reachable so wiping data in reachablecells
        this.reachableCells = null;
        reachableCells = new HashSet<Cell>();

        Cell[][] board = game.board;

        int yDirection = board[x][y].getColor() == Color.BLACK ? -1 : 1;

        for (int i = -1; i != 2; i++) {
            if (isValidMove(game, x, y, x + i, y + yDirection)) {
                Cell reachable = board[x + i][y + yDirection];
                reachableCells.add(reachable);

            }
        }


    }

}
