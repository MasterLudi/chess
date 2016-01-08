package Game;
import GUI.*;


/**
 * Chess class. Main program runs from this class.
 */
public class Chess {

    public void gameLoop() {
        BoardGUI bg = new BoardGUI();
        Game g = bg.game;

        while(g.checkMate == null) {
        }

    }

    public static void main(String[] argv) {
        Chess chess = new Chess();
        chess.gameLoop();
    }

}
