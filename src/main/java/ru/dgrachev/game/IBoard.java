package ru.dgrachev.game;

import java.awt.*;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IBoard extends Iterable<Map.Entry<Point,ICellState>>{


    Point getSize();

    ICellState getCellState(Point point);

    default void drawBoard(Graphics g){
        for (Map.Entry<Point,ICellState> entry:this){
            entry.getValue().drawCell(entry.getKey(),g);
        }
    }
    //закоментировал чтобы можно было сделать его package-private
   // void setCellState(Point point,ICell cell);


}
