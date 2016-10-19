package ru.dgrachev.userinput;

import ru.dgrachev.GUI.IGamePanel;
import ru.dgrachev.game.IGame;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class MouseGamePanelInput extends AbstractUserMouseInput {
//    IGamePanel gamePanel;
//    IGame game ;
//    Dimension gamePanelSize;


    public MouseGamePanelInput(IGamePanel gamePanel, IGame game) {
        super(gamePanel, game);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

        Point p=this.isOnSquare(new Point(e.getX(),e.getY()));
        if(p!=null) {
            if (e.getButton() == 0) {
                game.openCell(p);
            }
            if (e.getButton() == 1) {
                game.setFlag(p);

            }
        }
    }
}
