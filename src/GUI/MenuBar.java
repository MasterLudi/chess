package GUI;

import Controller.GameController;
import Game.*;

/**
 * Created by chloe on 2/27/15.
 */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {

    private BoardGUI g;
    private GameController gc;

    public MenuBar(BoardGUI gui){
        g = gui;
        gc = g.gc;
        JMenuBar menubar=new JMenuBar();
        MenuBarActionListener m=new MenuBarActionListener();

        JMenu gameMenu=new JMenu("Game");
        JMenuItem classicMenuItem=new JMenuItem("Classic Chess");
        JMenuItem customMenuItem=new JMenuItem("Custom Chess");
        classicMenuItem.addActionListener(m);
        customMenuItem.addActionListener(m);
        gameMenu.add(classicMenuItem);
        gameMenu.add(customMenuItem);

        JMenu restartMenu=new JMenu("Restart");
        JMenuItem restartMenuItem=new JMenuItem("Restart");
        JMenuItem forfeitMenuItem=new JMenuItem("Forfeit");
        restartMenuItem.addActionListener(m);
        forfeitMenuItem.addActionListener(m);
        restartMenu.add(restartMenuItem);
        restartMenu.add(forfeitMenuItem);

        JMenu undoMenu=new JMenu("Undo");
        JMenuItem undoMenuItem=new JMenuItem("Undo");
        JMenuItem redoMenuItem=new JMenuItem("Redo");
        undoMenuItem.addActionListener(m);
        redoMenuItem.addActionListener(m);
        undoMenu.add(undoMenuItem);
        undoMenu.add(redoMenuItem);

        menubar.add(gameMenu);
        menubar.add(restartMenu);
        menubar.add(undoMenu);

        g.setJMenuBar(menubar);

        }

    private class MenuBarActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            String text = item.getText();
            if (text.equals("Undo")) {
                gc.undo();
            }
            if (text.equals("Redo")) {
                gc.redo();
            }
            if (text.equals("Restart")) {
                gc.restart();
            }
            if (text.equals("Forfeit")) {
                gc.forfeit();
            }
            if (text.equals("Classic Chess")) {
                gc.changeMode(false);
            }
            if (text.equals("Custom Chess")) {
                gc.changeMode(true);
            }
        }
    }


}
