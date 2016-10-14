package ru.DGrachev.game;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IDifficult {

    void setDifficult(Difficult difficult);

    void setDifficult(Difficult difficult,Point boardSize,int bombCount);

    Point getBoardSize();

    int getBombsCount();
}
