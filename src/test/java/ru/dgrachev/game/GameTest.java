package ru.dgrachev.game;

import org.junit.Test;
import ru.dgrachev.GUI.GUI;
import ru.dgrachev.GUI.GamePanel;
import ru.dgrachev.GUI.IGUI;
import ru.dgrachev.game.Exceptions.LooseException;
import ru.dgrachev.game.Exceptions.WinException;

import java.awt.*;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static ru.dgrachev.game.GameParameters.BOMB_TYPE;
import static ru.dgrachev.game.GameParameters.currentBoardSize;

/**
 * Created by OTBA}|{HbIu` on 20.10.16.
 */
public class GameTest {
    Board board;
    GamePanel gp;
    IGUI gui;
    Game game;
    Point p=new Point(1,1);

    @Test
    public void checkLooseTest() throws Exception {
        GameParameters.currentBombsCount= (int) (currentBoardSize.x*currentBoardSize.y-2);
        board=new Board();
        gp=new GamePanel(board);
        gui=new GUI(gp);
        game=new Game(board,gui);
        game.openCell(p);
        try {
//            game.openCell(new Point(1,2));
            game.checkLoose(new CellState(BOMB_TYPE));
            fail();
        }catch (LooseException e){
        }

    }

    @Test
    public void checkWinTest() throws Exception {
        GameParameters.currentBombsCount=1;
        board=new Board();
        gp=new GamePanel(board);
        gui=new GUI(gp);
        game=new Game(board,gui);
        int maxAllowClosedCells=0;
        try {
            checkWinHelper();
            ICellState c;
            for (Map.Entry<Point,ICellState> entry:board){
                p=entry.getKey();
                c=entry.getValue();
                if (!c.isOpen()){
                    maxAllowClosedCells++;
                }
            }
            //3 закрытых ячейки когда цифры находятся в углу
            // только 1 открытая когда мы умудрились тыкнуть в цифру
        assertTrue(maxAllowClosedCells<=3
                || maxAllowClosedCells==currentBoardSize.x*currentBoardSize.y-1);
        }catch (WinException e){

        }
    }

    @Test
    public void updateGameTime() throws Exception {

    }

    private void checkWinHelper() throws WinException {

        new Generator(BOMB_TYPE,this.board).generateMines(p);

        ICellState cellState=board.getCellState(p);
        try {
            game.checkLoose(cellState);
        } catch (LooseException e) {
            return;
        }
        game.openCellsOnBoard(p);

        game.checkWin();

    }

}