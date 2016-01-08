package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;


public class CheckTests {
    @Test
    public void checkedByPawn() throws Exception {
        Game game = new Game(true);
        Cell[][] board = game.newBoard();

        board[4][5].piece = new King(Color.BLACK);
        board[3][3].piece = new Pawn(Color.WHITE);
        board[0][0].piece = new King(Color.WHITE);
        game.whiteCells.add(board[3][3]);
        game.blackCells.add(board[4][5]);
        game.whiteCells.add(board[0][0]);
        game.blackKingCell = board[4][5];
        game.whiteKingCell = board[0][0];
        game.setTurn(Color.WHITE);
        game.movePiece(3, 3, 3, 4);
        assertEquals(Color.BLACK, game.checked);

    }
}
