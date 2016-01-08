package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;
import Exceptions.InvalidMoveException;

import java.util.HashSet;


public class BishopTests {
    @Test
    public void validMove() throws Exception {
        Game game = new Game();
        Piece bishop = game.board[2][0].piece;

        game.movePiece(3, 1, 3, 3);
        game.movePiece(2, 0, 3, 1);
        assertEquals(bishop, game.board[3][1].piece);
    }

    @Test
    public void eatEnemy() throws Exception {
        Game game = new Game(true);
        Cell[][] board = game.newBoard();

        board[2][0].piece = new Bishop(Color.WHITE);
        board[5][3].piece = new Queen(Color.BLACK);

        game.blackCells = new HashSet<Cell>();
        game.whiteCells = new HashSet<Cell>();

        Piece bishop = board[2][0].piece;

        game.setTurn(Color.WHITE);
        game.whiteCells.add(board[2][0]);
        game.blackCells.add(board[5][3]);

        game.whiteKingCell = board[6][6];
        game.whiteKingCell.piece = new King(Color.WHITE);
        game.blackKingCell = board[6][5];
        game.blackKingCell.piece = new King(Color.BLACK);

        game.movePiece(2, 0, 5, 3);
        assertEquals(bishop, game.board[5][3].piece);

    }
}
