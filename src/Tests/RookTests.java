package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;
import Exceptions.InvalidMoveException;

public class RookTests {
    @Test
    public void validMove() throws Exception {
        Game game = new Game();
        Piece rook = game.board[0][0].piece;

        game.movePiece(0, 1, 0, 3);
        game.movePiece(0, 0, 0, 2);
        assertEquals(rook, game.board[0][2].piece);
    }

    @Test
    public void eatEnemy() throws Exception {
        Game game = new Game(true);
        Cell[][] board = game.newBoard();

        board[2][0].piece = new Rook(Color.WHITE);
        board[2][2].piece = new Queen(Color.BLACK);
        game.whiteCells.add(board[2][0]);
        game.blackCells.add(board[2][2]);

        game.whiteKingCell = board[6][6];
        game.whiteKingCell.piece = new King(Color.WHITE);
        game.blackKingCell = board[6][5];
        game.blackKingCell.piece = new King(Color.BLACK);

        Piece rook = board[2][0].piece;

        game.setTurn(Color.WHITE);
        game.movePiece(2, 0, 2, 2);
        assertEquals(rook, game.board[2][2].piece);

    }
}
