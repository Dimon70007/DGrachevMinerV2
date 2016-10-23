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
    public void checkLoose() throws Exception {
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
    public void checkWin() throws Exception {
        GameParameters.currentBombsCount=1;
        game=new Game(board,gui);
        generator.generateMines(board,p);

        if (board.getCellState(p).getCell()==BOMB_TYPE)
            fail();
        game.openCellsOnBoard(p);
        Map<Point, ICell> cells=game.resultBoardWithChangedBombs(2);
        int maxAllowClosedCells=0;
        try {
            game.checkWin();
            ICell c;
            for (Map.Entry<Point,ICell> entry:cells.entrySet()){
                p=entry.getKey();
                c=entry.getValue();
                if (c==Cell.CLOSED){
                    maxAllowClosedCells++;
                }
            }
        assertTrue(maxAllowClosedCells<=3);
        }catch (WinException e){

        }
    }

    @Test
    public void updateGameTime() throws Exception {

    }

}