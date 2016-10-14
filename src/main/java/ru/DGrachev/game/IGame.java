package ru.DGrachev.game;

import ru.DGrachev.game.Exceptions.LooseException;
import ru.DGrachev.game.Exceptions.WinException;

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
     return GameParameters.customBoardSize;
    }

    void updateGameTime();

}
