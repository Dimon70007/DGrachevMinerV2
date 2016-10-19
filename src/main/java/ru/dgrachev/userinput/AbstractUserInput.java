package ru.dgrachev.userinput;

import ru.dgrachev.GUI.IGamePanel;
import ru.dgrachev.game.IGame;

import java.awt.*;

import static ru.dgrachev.game.GameParameters.CELL_SIZE;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public abstract class AbstractUserInput {

    protected IGamePanel gamePanel;
    protected IGame game;
    protected Dimension gamePanelSize;



    public AbstractUserInput(IGamePanel gamePanel, IGame game) {
        this.gamePanel = gamePanel;
        this.game = game;
        this.gamePanelSize = gamePanel.getPanelSize();

    }

    //обрабатываем координату и возвращаем адрес ячейки если мы на нее попали
    //например пришла координата (5,10) - возвращаем (0,0), так как CELL_SIZE=32
    //и мы не вышли за пределы ячейки
    protected Point isOnSquare(Point p){
        for(int x = 0; x< gamePanelSize.width; x+=CELL_SIZE) {
            for (int y = 0; y < gamePanelSize.height; y += CELL_SIZE) {
                if(p.x>x && p.x<(x+CELL_SIZE) && p.y>y && p.y<(y+CELL_SIZE))
                    return new Point(x/CELL_SIZE,y/CELL_SIZE);
            }
        }
        return null;
    }

}
