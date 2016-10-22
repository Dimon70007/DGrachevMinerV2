package ru.dgrachev.userinput;

import ru.dgrachev.GUI.GUI;
import ru.dgrachev.GUI.OptionsWindow;
import ru.dgrachev.Main;
import ru.dgrachev.game.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NavigableSet;

/**
 * Created by OTBA}|{HbIu` on 13.10.16.
 */
public class UserGUIInput implements ActionListener {
    private final GUI gui;
    private final IGame game;
    private OptionsWindow optionsWindow;

    public UserGUIInput(GUI gui, IGame game) {
        this.gui=gui;
        this.game=game;
        gui.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            if (e.getActionCommand().equalsIgnoreCase("OPTIONS")) {
                 optionsWindow = new OptionsWindow(gui);
            }
            if (e.getActionCommand().equalsIgnoreCase("NEW GAME")) {
                gui.dispose();
                Main.main(null);

            }
            if (e.getActionCommand().equalsIgnoreCase("EXIT")) {
                gui.dispose();
                System.exit(0);
            }
            if (e.getActionCommand().equalsIgnoreCase("STATISTIKS")) {
                NavigableSet<Player> records=FileRecords.read();
                String message="";
                for (Player p:records){
                    message+=p.toString()+"\n";
                }
                JOptionPane.showMessageDialog(gui,message);
            }
            if (e.getActionCommand().equalsIgnoreCase("ABOUT")) {

                JOptionPane.showMessageDialog(gui,"Created by Grachev Dmitryi\nat 22.10.2016.\nBe happy!");
            }
            if (e.getActionCommand().equalsIgnoreCase("HELP")) {

                JOptionPane.showMessageDialog(gui,"Just click on game's window...");
            }
        }
    }
}
