package Game;

import java.util.HashSet;


public class Knight extends Piece {
    public Knight(Color c) {
        super(c);
        pieceType = Type.KNIGHT;
    }

    @Override
    public boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY) {
        if (!isValidRange(newX, newY))
            return false;

        // false if new position is occupied with same colored piece
        if (game.board[oldX][oldY].getColor() == game.board[newX][newY].getColor())
            return false;

        return (Math.abs(oldX-newX)==1 && Math.abs(oldY-newY)==2)
                || (Math.abs(oldX-newX)==2 && Math.abs(oldY-newY)==1);
    }


    @Override
    public void updateReachableCells(Game game, int x, int y) {
        //assuming king not in check
        this.reachableCells = null;
        reachableCells = new HashSet<Cell>();

        addReachableCell(game, x, y, x-2, y-1);
        addReachableCell(game, x, y, x-2, y+1);
        addReachableCell(game, x, y, x-1, y-2);
        addReachableCell(game, x, y, x-1, y+2);
        addReachableCell(game, x, y, x+2, y-1);
        addReachableCell(game, x, y, x+2, y+1);
        addReachableCell(game, x, y, x+1, y-2);
        addReachableCell(game, x, y, x+1, y+2);

    }

}
