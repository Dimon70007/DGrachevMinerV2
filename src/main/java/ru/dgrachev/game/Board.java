package ru.dgrachev.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class Board implements IBoard{
    Map<Point,ICell> boardCells;


    public Board() {
        this.boardCells = new HashMap<>();
    }

    @Override
    public Point getSize() {
        return GameParameters.currentBoardSize;
    }

    @Override
    public ICell getCell(Point point) {
        return boardCells.get(point);
    }


    ICell setCell(Point point, ICell cell) {
        return boardCells.put(point,cell);
    }

    @Override
    public Iterator<Map.Entry<Point, ICell>> iterator() {
        return boardCells.entrySet().iterator();
    }
}
