package ru.dgrachev;

import ru.dgrachev.GUI.GUI;
import ru.dgrachev.GUI.GamePanel;

import javax.swing.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class Main {
    static GUI gui;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GamePanel gp=new GamePanel();
                gui=new GUI(gp);
                gui.init();
                gui.start();
            }
        });

    }



}
