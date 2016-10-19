package ru.dgrachev.userinput;

import ru.dgrachev.GUI.IGamePanel;
import ru.dgrachev.game.IGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public abstract class AbstractUserKeyInput extends AbstractUserInput implements KeyListener{

    public AbstractUserKeyInput(IGamePanel gamePanel, IGame game) {
        super(gamePanel, game);
        gamePanel.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
