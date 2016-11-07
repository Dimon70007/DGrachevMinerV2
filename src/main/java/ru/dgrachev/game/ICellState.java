package ru.dgrachev.game;

import java.awt.*;
import java.io.IOException;

import static ru.dgrachev.game.GameParameters.CELL_SIZE;

/**
 * Created by OTBA}|{HbIu` on 22.10.16.
 */
public interface ICellState {

    boolean isOpen();

    ICell getFlag();

    ICell setFlag();

    void setOpened();

    ICell getCell();

    ICell setCell(ICell cell);

//    void drawCell(Point coordinate);
    default void drawCell(final Point point,final Graphics g) {
        try {
            ICell cell=getCell();
            if (!isOpen()){
                cell=getFlag();
            }
            Image img=cell.getImage();
            g.drawImage(img,point.x*CELL_SIZE,point.y*CELL_SIZE,CELL_SIZE,CELL_SIZE,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
