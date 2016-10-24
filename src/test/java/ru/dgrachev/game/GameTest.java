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
import static ru.dgrachev.game.GeneratorTest.board;
import static ru.dgrachev.game.GeneratorTest.generator;

/**
 * Created by OTBA}|{HbIu` on 20.10.16.
 */
public class GameTest {
    GamePanel gp=new GamePanel();
    IGUI gui=new GUI(gp);
    Point p=new Point(1,1);
    Game game;
    @Test
    public void checkLooseTest() throws Exception {
        GameParameters.currentBombsCount= (int) (currentBoardSize.x*currentBoardSize.y-2);
        game=new Game(gui);
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
        generator.generateMines(board,p);

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