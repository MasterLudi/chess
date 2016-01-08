package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;

import java.util.HashSet;


public class FerzTests extends Tests {
    @Test
    public void validMove() throws Exception {
        Game game = setBoard();
        addKing(game,0,0,Color.WHITE);
        addKing(game,7,7,Color.BLACK);
        Piece ferz = addFerz(game,3,4,Color.WHITE);
        setTurn(game,Color.WHITE);
        game.movePiece(3,4,4,5);
        assertEquals(ferz, game.board[4][5].piece);

    }

    @Test
    public void eatEnemy() throws Exception {
        Game game = setBoard();
        addKing(game,0,0,Color.WHITE);
        addKing(game,7,7,Color.BLACK);
        Piece ferz = addFerz(game,3,4,Color.WHITE);
        Piece rook = addRook(game,4,5,Color.BLACK);
        setTurn(game,Color.WHITE);
        game.movePiece(3,4,4,5);
        assertEquals(ferz, game.board[4][5].piece);

    }

    @Test
    public void invalidMove() throws Exception {
        Game game = setBoard();

    }

}
