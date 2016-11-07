package ru.dgrachev;

import ru.dgrachev.GUI.GUI;
import ru.dgrachev.GUI.GamePanel;
import ru.dgrachev.game.Board;

import javax.swing.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class Main {

    static GUI gui;
    static Board board;
    static GamePanel gp;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                board=new Board();
                gp=new GamePanel(board);

                gui=new GUI(gp);

                gui.init();
                gui.start();
            }
        });

    }



}
