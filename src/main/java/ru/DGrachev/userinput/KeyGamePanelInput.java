package ru.DGrachev.userinput;

import ru.DGrachev.GUI.IGamePanel;
import ru.DGrachev.game.IGame;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class KeyGamePanelInput extends AbstractUserKeyInput{

    public KeyGamePanelInput(IGamePanel gamePanel, IGame game) {
        super(gamePanel, game);
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

}
