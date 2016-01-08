package GUI;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class GamePanel extends JPanel {
    private BoardGUI g;
    private JLabel p1Lbl;
    private JLabel p2Lbl;

    public GamePanel(BoardGUI gui) {
        g = gui;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));
        p1Lbl = new JLabel("Player1 : 0 (W)", JLabel.CENTER);
        p2Lbl = new JLabel("Player2 : 0 (B)", JLabel.CENTER);
        add(new JLabel("*************************"));
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(p1Lbl);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(p2Lbl);
        add(Box.createRigidArea(new Dimension(0, 5)));
        add(new JLabel("*************************"));
        add(Box.createRigidArea(new Dimension(0, 30)));
    }

    public void setScoreLabels(String p1, String p2, int p1Score, int p2Score) {
        p1Lbl.setText(p1 + " : " + p1Score + " (W)");
        p2Lbl.setText(p2 + " : " + p2Score + " (B)");
    }
}
