package ru.dgrachev.game;

import java.awt.*;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IBoard extends Iterable<Map.Entry<Point,ICell>>{


    Point getSize();

    ICell getCell(Point point);

    //закоментировал чтобы можно было сделать его package-private
   // void setCell(Point point,ICell cell);


}
