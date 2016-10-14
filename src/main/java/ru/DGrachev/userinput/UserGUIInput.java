package ru.DGrachev.userinput;

import ru.DGrachev.GUI.IGUI;
import ru.DGrachev.GUI.OptionsWindow;
import ru.DGrachev.game.IGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by OTBA}|{HbIu` on 13.10.16.
 */
public class UserGUIInput implements ActionListener {
    private final IGUI gui;
    private final IGame game;

    public UserGUIInput(IGUI gui, IGame game) {
        this.gui=gui;
        this.game=game;
        gui.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("OPTIONS")){
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    OptionsWindow optionsWindow=new OptionsWindow((JFrame) gui,"OPTIONS");
                }
            });
        }
    }
}
