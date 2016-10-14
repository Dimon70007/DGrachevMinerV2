package ru.DGrachev.userinput;

import ru.DGrachev.GUI.IGamePanel;
import ru.DGrachev.game.IGame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public abstract class AbstractUserMouseInput extends AbstractUserInput implements MouseListener{

    public AbstractUserMouseInput(IGamePanel gamePanel, IGame game) {
        super(gamePanel, game);
        gamePanel.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
