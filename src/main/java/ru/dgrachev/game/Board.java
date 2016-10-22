package ru.dgrachev.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class Board implements IBoard{
    Map<Point,ICellState> boardCellsState;


    public Board() {
        this.boardCellsState = new HashMap<>();
    }

    @Override
    public Point getSize() {
        return GameParameters.currentBoardSize;
    }

    @Override
    public ICellState getCellState(Point point) {
        return boardCellsState.get(point);
    }


    ICellState setCellState(Point point, ICellState cellState) {
        return boardCellsState.put(point,cellState);
    }

    @Override
    public Iterator<Map.Entry<Point, ICellState>> iterator() {
        return boardCellsState.entrySet().iterator();
    }
}
