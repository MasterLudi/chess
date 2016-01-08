package Game;

import java.util.HashSet;


public class King extends Piece {
    public King(Color c) {
        super(c);
        pieceType = Type.KING;
    }

    @Override
    public boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY) {
        if (!isValidRange(newX, newY))
            return false;

        // false if the same color is occupied
        if ((!game.board[newX][newY].isEmpty())
                && (game.board[oldX][oldY].getColor() == game.board[newX][newY].getColor()))
            return false;

        else if (!(Math.abs(oldX-newX)==1 && Math.abs(oldY-newY)==0)
                && !(Math.abs(oldX-newX)==0 && Math.abs(oldY-newY)==1)
                && !(Math.abs(oldX-newX)==1 && Math.abs(oldY-newY)==1))
            return false;
        return true;
    }


    @Override
    public void updateReachableCells(Game game, int x, int y) {
        //assuming king not in check
        this.reachableCells = null;
        reachableCells = new HashSet<Cell>();
        HashSet<Cell> opponentCells;
        if (this.color == Color.WHITE) {
            opponentCells = game.blackCells;
        } else
            opponentCells = game.whiteCells;

        Cell[][] board = game.board;
        for (int xDirection = -1; xDirection != 2; xDirection++) {
            for(int yDirection = -1; yDirection != 2; yDirection++) {
                if (isValidMove(game, x, y, x + xDirection, y + yDirection)
                        && !containOppReachableCell(board[x + xDirection][y + yDirection], opponentCells)) {
                    Cell reachable = board[x + xDirection][y + yDirection];
                    reachableCells.add(reachable);

                }
            }
        }
    }

    /**
     * Check if opponent's reachable cells contain given cell
     * @param cell
     * @param oppCells
     * @return
     */
    private boolean containOppReachableCell(Cell cell, HashSet<Cell> oppCells) {
        for (Cell c: oppCells) {
            for (Cell reachable: c.piece.reachableCells) {
                if (reachable == cell)
                    return true;
            }
        }
        return false;

    }
}
