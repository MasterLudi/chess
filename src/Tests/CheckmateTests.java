package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;

import java.util.HashSet;


public class CheckmateTests {
    @Test
    public void checkmate1() throws Exception {
        Game game = new Game(true);
        Cell[][] board = game.newBoard();

        board[1][0].piece = new King(Color.BLACK);
        board[6][2].piece = new Rook(Color.WHITE);
        board[5][1].piece = new Rook(Color.WHITE);
        board[5][5].piece = new King(Color.WHITE);

        game.whiteCells.add(board[5][1]);
        game.whiteCells.add(board[6][2]);
        game.whiteCells.add(board[5][5]);
        game.blackCells.add(board[1][0]);

        game.blackKingCell = board[1][0];
        game.whiteKingCell = board[5][5];

        game.setTurn(Color.BLACK);
        game.movePiece(1, 0, 0, 0);
        System.out.println("passed this");
        game.setTurn(Color.WHITE);
        game.movePiece(6, 2, 6, 0);
        assertEquals(Color.BLACK, game.checkMate);
    }
}

