package Game;

import java.util.HashSet;


/**
 * Interface for chess pieces. Rules for checking valid move is implemented in each class.
 */
public abstract class Piece {

    public Color color;
    public Type pieceType;

    public HashSet<Cell> reachableCells;

    /**
     * Updates reachableCells for each piece everytime it makes a move.
     * @param game
     * @param x current x position
     * @param y current y position
     */
    public abstract void updateReachableCells(Game game, int x, int y);

    /**
     * Constructor for Piece class
     * @param c color
     */
    public Piece (Color c) {
        color = c;
        reachableCells = new HashSet<Cell>();
    }

    /**
     * Checks if it's a valid move from old position to the new position.
     * Checks if same color occupied in the destination, if there's pieces on the way, and if it's legal move.
     * @param game
     * @param oldX current x position
     * @param oldY current y position
     * @param newX new x position
     * @param newY new y position
     * @return
     */
    public abstract boolean isValidMove(Game game, int oldX, int oldY, int newX, int newY);

    protected boolean isValidRange(int x, int y) {
        return !(x > 7 || x < 0 || y > 7 || y < 0);
    }

    /**
     * Checks if there is any piece blocking the way in vertical line.
     * @param game
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
    protected boolean checkVertical(Game game, int oldX, int oldY, int newX, int newY) {
        int y1, y2;

        // false if two positions are same or not vertical
        if (oldY == newY || oldX != newX) {
            return false;
        }
        // check if new position is occupied with same color
        else if (game.board[oldX][oldY].getColor() != null
                && game.board[oldX][oldY].getColor() == game.board[newX][newY].getColor())
            return false;

        // y1 < y2
        y1 = (oldY < newY ? oldY+1 : newY+1);
        y2 = (oldY > newY ? oldY-1 : newY-1);

        if (y1==y2)
            return game.board[oldX][y1].isEmpty();

        // check if the path is blocked
        for (int i=0; i<=(y2-y1); i++) {
            if (!game.board[oldX][y1+i].isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Checks if there is any piece blocking the way in horizontal line.
     * @param game
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
    protected boolean checkHorizontal(Game game, int oldX, int oldY, int newX, int newY) {
        int x1, x2;

        // false if two positions are same or not horizontal
        if (oldX == newX || oldY != newY) {
            return false;
        }
        // check if new position is occupied with same color
        else if (game.board[oldX][oldY].getColor() != null
                && game.board[oldX][oldY].getColor() == game.board[newX][newY].getColor())
            return false;


        // x1 < x2
        x1 = (oldX < newX ? oldX+1 : newX+1);
        x2 = (oldX > newX ? oldX-1 : newX-1);


        if (x2 == x1)
            return game.board[x1][oldY].isEmpty();
        // check if the path is blocked
        for (int i=0; i<(x2-x1); i++) {
            if (!game.board[x1+i][oldY].isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Checks if there is any piece blocking the way in diagonal lines.
     * @param game
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
    protected boolean checkDiagonal(Game game, int oldX, int oldY, int newX, int newY) {
        int x1, x2, y1, y2;
        boolean slopePositive;

        // get slope and coordinates
        if (oldX == newX || oldY == newY || Math.abs(oldX-newX) != Math.abs(oldY-newY)) {
            return false;
        }
        // check if new position is occupied with same color
        else if (game.board[oldX][oldY].getColor() != null
                && game.board[oldX][oldY].getColor() == game.board[newX][newY].getColor())
            return false;

        // x1 < x2, y1 < y2
        x1 = (oldX < newX ? oldX + 1 : newX + 1);
        x2 = (oldX > newX ? oldX - 1 : newX - 1);
        y1 = (oldY < newY ? oldY + 1 : newY + 1);
        y2 = (oldY > newY ? oldY - 1 : newY - 1);
        slopePositive = (oldX < newX && oldY < newY) || (oldX > newX && oldY > newY);

        if (x2 == x1)
            return game.board[x1][y1].isEmpty();

        // check if the path is blocked
        if (slopePositive) {
            for (int i=0; i<Math.abs(x2-x1); i++) {
                if (!game.board[x1+i][y1+i].isEmpty())
                    return false;
            }
        } else {
            for (int i=0; i<Math.abs(x2-x1); i++) {
                if (!game.board[x1+i][y2-i].isEmpty())
                    return false;
            }
        }

        return true;
    }

    /**
     * Add cell in board[newX][newY] if it's a valid move
     * @param game
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return
     */
    public boolean addReachableCell(Game game, int oldX, int oldY, int newX, int newY){
        if( isValidMove(game, oldX, oldY, newX, newY)){
            Cell reachable = game.board[newX][newY];
            reachableCells.add(reachable);
            return true;
        }
        return false;
    }

    /**
     * Add reachable cells in diagonal lines.
     * @param game
     * @param x
     * @param y
     * @param max maximum number of cells to check
     */
    public void reachableDiagonal(Game game, int x, int y, int max){
        for(int i = 1; i <= max; i++ ){

            if(!addReachableCell(game, x, y, x + i, y + i)){
                break;
            }
        }
        for(int i = 1; i <= max; i++ ){
            if(!addReachableCell(game, x, y, x + i, y - i)){
                break;
            }
        }
        for(int i = 1; i <= max; i++ ){
            if(!addReachableCell(game, x, y, x - i, y + i)){
                break;
            }
        }
        for(int i = 1; i <= max; i++ ){
            if(!addReachableCell(game, x, y, x - i, y - i)){
                break;
            }
        }
    }

    /**
     * Add reachable cells in horizontal & vertical lines.
     * @param game
     * @param x
     * @param y
     * @param max maximum number of cells to check
     */
    public void reachableAcross(Game game, int x, int y, int max){
        for(int i = 1; i <= max; i++ ){

            if(!addReachableCell(game, x, y, x, y + i)){
                break;
            }
        }
        for(int i = 1; i <= max; i++ ){
            if(!addReachableCell(game, x, y, x, y - i)){
                break;
            }
        }
        for(int i = 1; i <= max; i++ ){
            if(!addReachableCell(game, x, y, x + i, y)){
                break;
            }
        }
        for(int i = 1; i <= max; i++ ){
            if(!addReachableCell(game, x, y, x - i, y)){
                break;
            }
        }
    }


}
