package ru.dgrachev.game;

import ru.dgrachev.game.Exceptions.LooseException;
import ru.dgrachev.game.Exceptions.WinException;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IGame {

    IBoard board = null;

    void checkLoose(ICell cell) throws LooseException;

    void checkWin() throws WinException;

    void openCell(Point point);

    void setFlag(Point point);

    default Point getBoardSize(){
     return GameParameters.currentBoardSize;
    }

    void updateGameTime();



}
