package Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import Game.*;

public class WazirTests extends Tests {
    @Test
    public void validMove() throws Exception {
        Game game = setBoard();
        addKing(game,0,0,Color.WHITE);
        addKing(game,7,7,Color.BLACK);
        Piece wazir = addWazir(game,3,4,Color.WHITE);
        setTurn(game,Color.WHITE);
        game.movePiece(3,4,3,5);
        assertEquals(wazir, game.board[3][5].piece);
    }

    @Test
    public void eatEnemy() throws Exception {
        Game game = setBoard();
        addKing(game,0,0,Color.WHITE);
        addKing(game,7,7,Color.BLACK);
        Piece wazir = addWazir(game,3,4,Color.WHITE);
        Piece rook = addRook(game,3,5,Color.BLACK);
        setTurn(game,Color.WHITE);
        game.movePiece(3,4,3,5);
        assertEquals(wazir, game.board[3][5].piece);
    }

    @Test
    public void invalidMove() throws Exception {

    }
}
