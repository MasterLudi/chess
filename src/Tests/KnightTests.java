package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;
import Exceptions.InvalidMoveException;


public class KnightTests {
    @Test
    public void validMove() throws Exception {
        Game game = new Game();
        Piece knight = game.board[1][0].piece;

        game.movePiece(1, 0, 2, 2);
        assertEquals(knight, game.board[2][2].piece);
    }

    @Test
    public void eatEnemy() throws Exception {
        Game game = new Game();
        Piece knight = game.board[6][7].piece;

        game.movePiece(1, 0, 2, 2);
        game.movePiece(6, 7, 5, 5);
        game.movePiece(2, 2, 4, 3);
        game.movePiece(5, 5, 4, 3);
        assertEquals(knight, game.board[4][3].piece);

    }
}
