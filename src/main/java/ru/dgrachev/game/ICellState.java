package ru.dgrachev.game;

/**
 * Created by OTBA}|{HbIu` on 22.10.16.
 */
public interface ICellState {

    boolean isOpen();

    ICell getFlag();

    ICell setFlag();

    void setOpened();

    ICell getCell();

}
