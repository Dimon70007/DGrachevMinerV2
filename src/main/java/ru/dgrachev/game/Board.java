package ru.dgrachev.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class Board implements IBoard{
    private final Map<Point,ICellState> boardCellsState;


    public Board() {
        this.boardCellsState = new HashMap<>();
    }

    @Override
    public Point getSize() {
        return GameParameters.currentBoardSize;
    }

    @Override
    public ICellState getCellState(final Point point) {
        return boardCellsState.get(point);
    }


    //this method has been set package private for generating mines
    ICellState setCellState(final Point point, final ICellState cellState) {
        return boardCellsState.put(point,cellState);
    }

    @Override
    public Iterator<Map.Entry<Point, ICellState>> iterator() {
        return boardCellsState.entrySet().iterator();
    }
}
