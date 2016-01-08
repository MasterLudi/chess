package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;
import Exceptions.InvalidMoveException;

import java.util.HashSet;


public class KingTests {

    @Test
    public void eatEnemy() throws Exception {
        Game game = new Game(true);

        Cell[][] board = game.newBoard();

        board[3][3].piece = new King(Color.WHITE);
        board[4][3].piece = new Pawn(Color.BLACK);

        game.blackCells = new HashSet<Cell>();
        game.whiteCells = new HashSet<Cell>();

        game.whiteCells.add(board[3][3]);
        game.blackCells.add(board[4][3]);

        game.blackKingCell = board[6][5];
        game.blackKingCell.piece = new King(Color.BLACK);

        game.whiteKingCell = board[3][3];

        Piece king = board[3][3].piece;

        game.movePiece(3, 3, 4, 3);
        assertEquals(king, game.board[4][3].piece);

    }
/*
    @Test (expected = Exception.class)
    public void invalidChecked() {
        Game game = new Game();
        Cell[][] board = game.newBoard();

        board[3][3].piece = new King(Color.WHITE);
        board[4][5].piece = new Pawn(Color.BLACK);
        game.movePiece(3, 3, 3, 4);
    }*/
}
