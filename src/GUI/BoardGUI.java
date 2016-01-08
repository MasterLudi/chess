package GUI;
import Controller.GameController;
import Game.*;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;

/**
 * Created by chloe on 2/19/15.
 */
public class BoardGUI extends JFrame {

    public Button selectedButton;
    public java.awt.Color selectedColor;
    public Game game;
    public GameController gc;
    public boolean customChess = false;
    public BoardPanel bp;
    public GamePanel gp;
    public MenuBar mb;
    public JSplitPane sp;

    public int p1Score = 0;
    public int p2Score = 0;

    public String p1Name;
    public String p2Name;

    /**
     * Initializes the chess board gui.
     */
    public BoardGUI() {

        game = new Game(customChess);
        gc = new GameController(this);
        mb = new MenuBar(this);

        sp = new JSplitPane();
        bp = new BoardPanel(this, customChess);
        gp = new GamePanel(this);
        sp.setLeftComponent(bp);
        sp.setRightComponent(gp);

        this.add(sp);
        this.pack();
        this.setVisible(true);

        getInsertNamePopup();
        gp.setScoreLabels(p1Name, p2Name, 0, 0);
        gameLoop();

    }

    public void gameLoop() {

        while (game.checkMate == null) {}
        JOptionPane.showMessageDialog(this, "Checkmate!");
        gc.resetGame();
        gameLoop();
    }

    public void setCustomChess(boolean c) {
        customChess = c;
    }

    private void getInsertNamePopup() {
        p1Name = JOptionPane.showInputDialog("Enter White's name: ");
        p2Name = JOptionPane.showInputDialog("Enter Black's name: ");
    }




}
