package GUI;

import Game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

/**
 * Creates GridLayout with piece images.
 */
public class BoardPanel extends JPanel {
    private BoardGUI g;

    public HashSet<Button> bBtns;
    public HashSet<Button> wBtns;
    public Button[][] btnsArray;

    private Icon removedIcon;

    public BoardPanel(BoardGUI gui, boolean custom) {
        g = gui;
        bBtns = new HashSet<Button>();
        wBtns = new HashSet<Button>();

        btnsArray = new Button[8][];
        for (int i=0; i<8; i++)
            btnsArray[i] = new Button[8];

        try {
            setLayout(new GridBagLayout());

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {

                    GridBagConstraints gc = new GridBagConstraints();
                    Image icon = null;
                    btnsArray[col][7-row] = new Button(col, 7-row);
                    Button button = btnsArray[col][7-row];

                    gc.gridx = col;
                    gc.gridy = row;

                    // custom pieces
                    if (row == 1 && col == 0) {
                        if (custom)
                            icon = ImageIO.read(getClass().getResource("img/bWazir.png"));
                        else
                            icon = ImageIO.read(getClass().getResource("img/bPawn.png"));
                        bBtns.add(button);
                    }
                    if (row == 1 && col == 7) {
                        if (custom)
                            icon = ImageIO.read(getClass().getResource("img/bFerz.png"));
                        else
                            icon = ImageIO.read(getClass().getResource("img/bPawn.png"));
                        bBtns.add(button);
                    }
                    if (row == 6 && col == 0) {
                        if (custom)
                            icon = ImageIO.read(getClass().getResource("img/wWazir.png"));
                        else
                            icon = ImageIO.read(getClass().getResource("img/wPawn.png"));
                        wBtns.add(button);
                    }
                    if (row == 6 && col == 7) {
                        if (custom)
                            icon = ImageIO.read(getClass().getResource("img/wFerz.png"));
                        else
                            icon = ImageIO.read(getClass().getResource("img/wPawn.png"));
                        wBtns.add(button);
                    }

                    // black pawns
                    if (row == 1 && col != 0 && col != 7) {
                        icon = ImageIO.read(getClass().getResource("img/bPawn.png"));
                        bBtns.add(button);
                    }
                    // white pawns
                    if (row == 6 && col != 0 && col != 7) {
                        icon = ImageIO.read(getClass().getResource("img/wPawn.png"));
                        wBtns.add(button);
                    }
                    // black rooks
                    if (row == 0 && (col == 0 || col == 7)) {
                        icon = ImageIO.read(getClass().getResource("img/bRook.png"));
                        bBtns.add(button);
                    }
                    // white rooks
                    if (row == 7 && (col == 0 || col == 7)) {
                        icon = ImageIO.read(getClass().getResource("img/wRook.png"));
                        wBtns.add(button);
                    }
                    // black knights
                    if (row == 0 && (col == 1 || col == 6)) {
                        icon = ImageIO.read(getClass().getResource("img/bKnight.png"));
                        bBtns.add(button);
                    }
                    // white knights
                    if (row == 7 && (col == 1 || col == 6)) {
                        icon = ImageIO.read(getClass().getResource("img/wKnight.png"));
                        wBtns.add(button);
                    }
                    // black bishops
                    if (row == 0 && (col == 2 || col == 5)) {
                        icon = ImageIO.read(getClass().getResource("img/bBishop.png"));
                        bBtns.add(button);
                    }
                    // white bishops
                    if (row == 7 && (col == 2 || col == 5)) {
                        icon = ImageIO.read(getClass().getResource("img/wBishop.png"));
                        wBtns.add(button);
                    }
                    // black King
                    if (row == 0 && col == 4) {
                        icon = ImageIO.read(getClass().getResource("img/bKing.png"));
                        bBtns.add(button);
                    }
                    // white king
                    if (row == 7 && col == 4) {
                        icon = ImageIO.read(getClass().getResource("img/wKing.png"));
                        wBtns.add(button);
                    }
                    // black queen
                    if (row == 0 && col == 3) {
                        icon = ImageIO.read(getClass().getResource("img/bQueen.png"));
                        bBtns.add(button);
                    }
                    // white queen
                    if (row == 7 && col == 3) {
                        icon = ImageIO.read(getClass().getResource("img/wQueen.png"));
                        wBtns.add(button);
                    }

                    if (icon != null)
                        button.setIcon(new ImageIcon(icon));

                    // set background colors
                    if ((row + col)%2 == 0)
                        button.setBackground(Color.white);
                    else
                        button.setBackground(Color.lightGray);
                    button.setPreferredSize(new Dimension(60, 60));
                    button.setBorderPainted(false);
                    button.setVisible(true);
                    button.setOpaque(true);
                    button.addActionListener(new ButtonActionListener());
                    add(button, gc);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when button is clicked.
     */
    class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // change the color of the button with icon when clicked
            Button button = (Button) ae.getSource();
            if (button == g.selectedButton) {
                changeButtonBackground(button);
                return;
            }
            if (button.getIcon() != null && g.selectedButton == null) {
                changeButtonBackground(button);
            }
            // when user makes a move
            else if (g.selectedButton != null){
                Move mv = g.game.movePiece(g.selectedButton.x, g.selectedButton.y, button.x, button.y);
                updateIcons(mv, g.selectedButton, button);
                changeButtonBackground(g.selectedButton);
                g.selectedButton = null;
                g.selectedColor = null;
            }
        }

    }


    /**
     * Update icons after move
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     */
    public void updateIcons (Move mv, Button oldBtn, Button newBtn) {

        if (mv == Move.INVALID) {
            JOptionPane.showMessageDialog(this, "Invalid Move!");
        }
        if (mv == Move.CAPTURE) {

            removedIcon = newBtn.getIcon();
            newBtn.setIcon(oldBtn.getIcon());
            oldBtn.setIcon(null);
        }
        if (mv == Move.VALID) {
           newBtn.setIcon(oldBtn.getIcon());
            oldBtn.setIcon(null);
            removedIcon = null;
        }
        if (mv == Move.CHECK) {
            JOptionPane.showMessageDialog(this, "You are checked!");
        }
        if (mv == Move.WRONGTURN) {
            JOptionPane.showMessageDialog(this, "It's not your turn!");
        }
        if (mv == Move.UNDO) {
            oldBtn.setIcon(newBtn.getIcon());
            newBtn.setIcon(removedIcon);
            removedIcon = null;
        }
    }

    /**
     * Change the background of the button when icon to be moved is clicked
     * @param button
     */
    private void changeButtonBackground(Button button) {
        if (button.getBackground() == Color.pink) {
            button.setBackground(g.selectedColor);
            g.selectedButton = null;
            g.selectedColor = null;
        } else {
            if (g.selectedButton != null) {
                g.selectedButton.setBackground(g.selectedColor);
            }
            g.selectedButton = button;
            g.selectedColor = button.getBackground();
            button.setBackground(Color.pink);
        }
    }


}
