package Controller;

import Game.*;
import GUI.*;
import javax.swing.*;


public class GameController {

    BoardGUI g;

    public GameController(BoardGUI gui) {g = gui;}

    public void undo() {
        if (g.game.undoMove())
            g.bp.updateIcons(Move.UNDO, g.bp.btnsArray[g.game.prevCell.x][g.game.prevCell.y], g.bp.btnsArray[g.game.currCell.x][g.game.currCell.y]);
        else
            JOptionPane.showMessageDialog(null, "Cannot undo more than 1 move!");
    }

    public void redo() {
        if (g.game.redoMove())
            g.bp.updateIcons(Move.VALID, g.bp.btnsArray[g.game.prevCell.x][g.game.prevCell.y], g.bp.btnsArray[g.game.currCell.x][g.game.currCell.y]);
        else
            JOptionPane.showMessageDialog(null, "Cannot redo more than 1 move!");
    }

    public void forfeit() {
        String question = "Are you sure you want to forfeit?";

        int reply = JOptionPane.showConfirmDialog(g, question, "Forfeit", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            if (g.game.turn == Color.WHITE) {
                g.p2Score++;
            } else {
                g.p1Score++;
            }
        }
        resetGame();
        g.gp.setScoreLabels(g.p1Name, g.p2Name, g.p1Score, g.p2Score);
    }

    public void changeMode(boolean custom) {
        if (custom == g.customChess)
            return;
        String question = "Changing mode restarts the game. Do you want to change mode?";
        int reply = JOptionPane.showConfirmDialog(g, question, "Change Mode", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            g.setCustomChess(custom);
            resetGame();
        }
    }

    public void restart() {
        int reply = JOptionPane.showConfirmDialog(g, "Are you sure you want to restart?", "Restart", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            resetGame();
        }
    }

    public void resetGame() {
        g.bp = new BoardPanel(g, g.customChess);
        g.game = new Game(g.customChess);
        g.sp.setLeftComponent(g.bp);
        g.game.setTurn(Color.WHITE);
    }
}
