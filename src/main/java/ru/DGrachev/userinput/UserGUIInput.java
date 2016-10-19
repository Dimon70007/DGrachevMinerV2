package ru.DGrachev.userinput;

import ru.DGrachev.GUI.GUI;
import ru.DGrachev.GUI.OptionsWindow;
import ru.DGrachev.GUI.RecordsWindow;
import ru.DGrachev.game.Difficult;
import ru.DGrachev.game.GameParameters;
import ru.DGrachev.game.IGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                optionsWindow = new OptionsWindow(gui, this);

            }
            if (e.getActionCommand().equalsIgnoreCase("NEW GAME")) {
                gui.init();

            }
            if (e.getActionCommand().equalsIgnoreCase("EXIT")) {
                gui.dispose();
                System.exit(0);
            }
            if (e.getActionCommand().equalsIgnoreCase("RECORDS")) {
                new RecordsWindow(gui);
            }
        }

        if(e.getSource() instanceof JButton) {
            String buttonName=((JButton)e.getSource()).getText();
            if(optionsWindow!=null) {
                if (("OK").equalsIgnoreCase(buttonName)) {
                    int tmpX = Integer.valueOf(optionsWindow.x.getText().trim());
                    int tmpY = Integer.valueOf(optionsWindow.y.getText().trim());
                    int tmpBCount = Integer.valueOf(optionsWindow.bombsCount.getText().trim());

                    if ("EASY".equalsIgnoreCase(
                            optionsWindow.getSelection())) {
                        GameParameters.currentDifficult = Difficult.EASY;
                        GameParameters.currentBombsCount = GameParameters.EASY_BOMB_COUNT;
                        GameParameters.currentBoardSize.x = GameParameters.EASY_BOARD_SIZE.x;
                        GameParameters.currentBoardSize.y = GameParameters.EASY_BOARD_SIZE.y;

                    }

                    if ("MEDIUM".equalsIgnoreCase(
                            optionsWindow.getSelection())) {
                        GameParameters.currentDifficult = Difficult.MEDIUM;
                        GameParameters.currentBombsCount = GameParameters.MEDIUM_BOMB_COUNT;
                        GameParameters.currentBoardSize.x = GameParameters.MEDIUM_BOARD_SIZE.x;
                        GameParameters.currentBoardSize.y = GameParameters.MEDIUM_BOARD_SIZE.y;

                    }

                    if ("HARD".equalsIgnoreCase(
                            optionsWindow.getSelection())) {
                        GameParameters.currentDifficult = Difficult.HARD;
                        GameParameters.currentBombsCount = GameParameters.HARD_BOMB_COUNT;
                        GameParameters.currentBoardSize.x = GameParameters.HARD_BOARD_SIZE.x;
                        GameParameters.currentBoardSize.y = GameParameters.HARD_BOARD_SIZE.y;

                    }

                    if ("CUSTOM".equalsIgnoreCase(
                            optionsWindow.getSelection())) {
                        if (tmpX > 4 &&
                                tmpY > 4 &&
                                tmpBCount > 0 && tmpBCount < (tmpX * tmpY)) {
                            GameParameters.currentDifficult = Difficult.CUSTOM;
                            GameParameters.currentBombsCount = tmpBCount;
                            GameParameters.currentBoardSize.x = tmpX;
                            GameParameters.currentBoardSize.y = tmpY;
                        } else if (tmpX == 0 || tmpY == 0 || tmpBCount == 0) {
                            String message="You have been chose CUSTOM currentDifficult,\n" +
                                            "that's why you should enter CELLS COUNTS\n" +
                                            "bigger then 4x4 and CUSTOM BOMBS COUNT= \n" +
                                            "bigger then zero and less then multiple\n" +
                                            "of (CELLS COUNT on X and CELLS COUNT ON Y)";
                            JOptionPane.showMessageDialog(optionsWindow,
                                    message,
                                    "Wrong Parameters", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }
                optionsWindow.dispose();
                gui.init();

            }

            if (("CANCEL").equalsIgnoreCase(buttonName)) {
                optionsWindow.dispose();
            }
            optionsWindow=null;
            gui.repaint();

        }
    }
}
