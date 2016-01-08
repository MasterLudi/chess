package Game;

import java.util.HashSet;


/**
 * Game class contains board, and rules needed for the game.
 */
public class Game {

    public Cell[][] board;

    public Color checked;

    public Color checkMate;

    public Cell whiteKingCell;
    public Cell blackKingCell;

    public HashSet<Cell> whiteCells;
    public HashSet<Cell> blackCells;

    public Color turn;

    private Piece removed;
    private boolean undoable = false;
    private boolean redoable = false;
    public Cell prevCell = null;
    public Cell currCell = null;

    public Game(boolean custom) {
        if (custom) {
            // initialize cells array
            whiteCells = new HashSet<Cell>();
            blackCells = new HashSet<Cell>();

            checkMate = null;
            board = newBoard();

            // set turn to white
            turn = Color.WHITE;
        } else
            initializeBoard();
    }

    public Game() {
//        BoardGUI gui = new BoardGUI();
        initializeBoard();
    }

    /* for testing */
    public Cell[][] newBoard() {

        board = new Cell[8][];
        for (int i=0; i<8; i++) {
            board[i] = new Cell[8];
            for (int j=0; j<8; j++)
                board[i][j] = new Cell(i, j);
        }
        return board;
    }

    /**
     * Move piece from board[oldX][oldY] to board[newX][newY].
     * Checks for invalid move and throws an invalid move exception.
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     */
    public Move movePiece(int oldX, int oldY, int newX, int newY) {

        Cell oldCell = board[oldX][oldY];
        Cell newCell = board[newX][newY];
        Cell kingCell = getKingCell();
        Boolean capture = false;

        // check if turn is right
        if (isWrongTurn(oldCell))
            return Move.WRONGTURN;

        if (isOccupiedInvalidMove(oldCell, newCell))
            return Move.INVALID;

        removed = null;

        // check if taking opponent's piece
        if (!newCell.isEmpty()) {
            removed = removePiece(newCell);
            capture = true;
        }

        movePiece(oldCell, newCell);

        //revert if new move get myself checked
        if(isSelfChecked()) {
            revertMove(oldCell, newCell);
            if (checked != null)
                return Move.CHECK;
            return Move.INVALID;
        }

        //check if new move gives the enemy in check
        setChecked();

        if (blackKingCell != null && blackKingCell.piece.reachableCells.isEmpty()
                && checked == Color.BLACK) {
            checkMate = Color.BLACK;
        }
        if (whiteKingCell != null && whiteKingCell.piece.reachableCells.isEmpty()
                && checked == Color.WHITE) {
            checkMate = Color.WHITE;
        }
        turn = (newCell.getColor() == Color.WHITE ? Color.BLACK : Color.WHITE);

        prevCell = oldCell;
        currCell = newCell;
        undoable = true;
        redoable = false;

        if (capture)
            return Move.CAPTURE;
        return Move.VALID;
    }

    public void restart() {

    }

    public boolean undoMove() {
        if (!undoable) return false;
        if (currCell.piece.pieceType == Type.PAWN) {
            if (prevCell.y == 1 && currCell.getColor() == Color.WHITE) {
                ((Pawn)currCell.piece).unmoved = true;
            } else if (prevCell.y == 6 && currCell.getColor() == Color.BLACK)
                ((Pawn)currCell.piece).unmoved = true;
        }
        revertMove(prevCell, currCell);
        redoable = true;
        undoable = false;
        turn = (turn == Color.WHITE ? Color.BLACK : Color.WHITE);
        return true;
    }

    public boolean redoMove() {
        if (!redoable) return false;
        movePiece(prevCell.x, prevCell.y, currCell.x, currCell.y);
        undoable = true;
        redoable = false;
        turn = (turn == Color.WHITE ? Color.BLACK : Color.WHITE);
        return true;
    }

    /**
     * Get King cell based on turn
     * @return king cell
     */
    private Cell getKingCell() {
        return (turn == Color.WHITE ? whiteKingCell : blackKingCell);
    }

//    private void throwInvalidMoveException() {
//        try {
//            throw new InvalidMoveException();
//        } catch (InvalidMoveException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Throws exception when King is in check and the new move doesn't break the check.
//     * @param cell
//     * @param kingCell
//     * @return
//     */
//    private boolean isCheckedInvalidMove(Cell cell, Cell kingCell) {
//
//        if (turn == checked && cell != kingCell) {
//            return true;
//        }
//        return false;
//    }

    private boolean isWrongTurn(Cell oldCell) {
        if (oldCell.getColor() != turn)
            return true;
        return false;
    }

    /**
     * Check if the new position is occupied or there is a piece in between.
     * @param oldCell
     * @param newCell
     * @return
     */
    private boolean isOccupiedInvalidMove(Cell oldCell, Cell newCell) {
        if (oldCell.piece == null
            || !oldCell.piece.isValidMove(this, oldCell.x, oldCell.y, newCell.x, newCell.y)
            || newCell == blackKingCell
            || newCell == whiteKingCell) {
            return true;
        }
        return false;
    }

    /**
     * Remove oldCell and add newCell to the HashSet<Cell>
     * @param oldCell
     * @param newCell
     */
    public void updateCellsArray(Cell oldCell, Cell newCell) {

        //remove old cell and add new to array list
        if(newCell.getColor() == Color.BLACK){
            blackCells.remove(oldCell);
            blackCells.add(newCell);
        }
        else{
            whiteCells.remove(oldCell);
            whiteCells.add(newCell);
        }
    }

    /**
     * Put piece from newCell to oldCell and place oldPiece back to oldCell
     * @param oldCell
     * @param newCell
     */
    private void revertMove(Cell oldCell, Cell newCell) {
        oldCell.addPiece(newCell.piece);
        newCell.releasePiece();
        if (removed != null) {
            newCell.addPiece(removed);
            removed = null;
        }
        updateCellsArray(newCell, oldCell);
        updateReachableList(blackCells);
        updateReachableList(whiteCells);
    }

    /**
     * check if the new move put myself to check
     * @return
     */
    private boolean isSelfChecked() {
        Cell kingCell = getKingCell();
        if(kingCell != null
                && kingCell.getColor() == Color.BLACK) {
            return isChecked(kingCell, whiteCells);
        }
        else if (kingCell != null
                && kingCell.getColor() == Color.WHITE) {
            return isChecked(kingCell, blackCells);
        }
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Set checked status.
     */
    private void setChecked() {
        if(isChecked(whiteKingCell, blackCells)) {
            checked = Color.WHITE;
        }
        else if(isChecked(blackKingCell, whiteCells)) {
            checked = Color.BLACK;
        }
        else
            checked = null;

    }

    /**
     * Moves the piece to the newCell and releases from the oldCell.
     * After the moving, it updates the cells array and reachable cells list.
     * @param oldCell
     * @param newCell
     */
    private void movePiece(Cell oldCell, Cell newCell) {
        //if king is moved update kingCell
        if (oldCell == blackKingCell) {
            blackKingCell = newCell;
        } else if (oldCell == whiteKingCell) {
            whiteKingCell = newCell;
        }

        // move the piece
        newCell.addPiece(oldCell.piece);
        oldCell.releasePiece();

        updateCellsArray(oldCell, newCell);

        //update reachable cells for each piece
        updateReachableList(blackCells);
        updateReachableList(whiteCells);
        blackKingCell.piece.updateReachableCells(this, blackKingCell.x, blackKingCell.y);
        whiteKingCell.piece.updateReachableCells(this, whiteKingCell.x, whiteKingCell.y);

    }


    /**
     * Remove piece from given cell and also from the array Cell[].
     * @param cell
     * @return
     */
    private Piece removePiece(Cell cell) {
        Piece pc = cell.piece;
        if(cell.getColor() == Color.BLACK)
            blackCells.remove(cell);
        else{
            whiteCells.remove(cell);
        }
        cell.releasePiece();
        return pc;
    }

    public void setTurn(Color c) {
        turn = c;
    }

    /**
     * Updates each reachable Cell[] of the pieces in argument cells
     * @param Cells
     */
    public void updateReachableList(HashSet<Cell> cells){
        for (Cell cell: cells){
            cell.piece.updateReachableCells(this, cell.x, cell.y);
        }
    }

    /**
     * Checks if opponent's pieces' reachable cell contains the king's cell
     * and set checked flag.
     * @param kingCell
     * @param oppCells
     * @return
     */
    public boolean isChecked(Cell kingCell, HashSet<Cell> oppCells){
        if (kingCell == null)
            return false;
        for(Cell c: oppCells ){
            for (Cell reachableCell: c.piece.reachableCells) {
                if (kingCell == reachableCell)
                    return true;
            }
        }
        return false;

    }

    /**
     * Initialize the cells in an empty board.
     */
    private void initializeCells(){
        board = new Cell[8][];
        for (int i=0; i<8; i++) {
            board[i] = new Cell[8];
            for (int j=0; j<8; j++)
                board[i][j] = new Cell(i, j);
        }

    }

    /**
     * Initialize pieces as in default chess board.
     */
    private void initializePiece(){
        // initialize pieces
        for (int i=0; i<8; i++) {
            board[i][1].piece = new Pawn(Color.WHITE);
            board[i][6].piece = new Pawn(Color.BLACK);
        }
        board[0][0].piece = new Rook(Color.WHITE);
        board[7][0].piece = new Rook(Color.WHITE);
        board[0][7].piece = new Rook(Color.BLACK);
        board[7][7].piece = new Rook(Color.BLACK);
        board[1][0].piece = new Knight(Color.WHITE);
        board[6][0].piece = new Knight(Color.WHITE);
        board[1][7].piece = new Knight(Color.BLACK);
        board[6][7].piece = new Knight(Color.BLACK);
        board[2][0].piece = new Bishop(Color.WHITE);
        board[5][0].piece = new Bishop(Color.WHITE);
        board[2][7].piece = new Bishop(Color.BLACK);
        board[5][7].piece = new Bishop(Color.BLACK);
        board[3][0].piece = new Queen(Color.WHITE);
        board[3][7].piece = new Queen(Color.BLACK);
        board[4][0].piece = new King(Color.WHITE);
        board[4][7].piece = new King(Color.BLACK);
        whiteKingCell = board[4][0];
        blackKingCell = board[4][7];

    }

    /**
     * Initializes whiteCells and blackCells in default chess board.
     */
    private void initializeCellsArray(){
        // initialize cells array
        whiteCells = new HashSet<Cell>();
        blackCells = new HashSet<Cell>();
        for(int i=0; i < 8; i++){
            whiteCells.add(board[i][0]);
            whiteCells.add(board[i][1]);
            blackCells.add(board[i][6]);
            blackCells.add(board[i][7]);
        }

    }


    /**
     * Initializes the board.
     */
    private void initializeBoard() {
        initializeCells();
        initializePiece();
        initializeCellsArray();
        checkMate = null;

        // set turn to white
        turn = Color.WHITE;

    }



}
