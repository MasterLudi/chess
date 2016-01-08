package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;
import Exceptions.InvalidMoveException;

public class PawnTests extends Tests{

    @Test
    public void moveOne() throws Exception {
        Game game = new Game();
        Piece pawn = game.board[0][1].piece;

        game.movePiece(0, 1, 0, 2);
        assertEquals(pawn, game.board[0][2].piece);
    }

    @Test
    public void moveTwo() throws Exception {
        Game game = new Game();
        Piece pawn = game.board[0][1].piece;

        game.movePiece(0, 1, 0, 3);
        assertEquals(pawn, game.board[0][3].piece);
    }

    @Test
    public void eatEnemy() throws Exception {
        Game game = setBoard();

        addKing(game,0,0,Color.WHITE);
        addKing(game,7,7,Color.BLACK);
        Piece pawn = addPawn(game,0,1,Color.WHITE);
        Piece queen = addQueen(game,1,2,Color.BLACK);

        game.movePiece(0, 1, 1, 2);
        assertEquals(pawn, game.board[1][2].piece);
    }
}