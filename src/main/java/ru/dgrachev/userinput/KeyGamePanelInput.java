package ru.dgrachev.userinput;

import ru.dgrachev.GUI.IGamePanel;
import ru.dgrachev.game.IGame;

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
