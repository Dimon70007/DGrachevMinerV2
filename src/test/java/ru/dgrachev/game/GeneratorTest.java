package ru.dgrachev.game;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static ru.dgrachev.game.GameParameters.BOMB_TYPE;
import static ru.dgrachev.game.GameParameters.currentBombsCount;

/**
 * Created by OTBA}|{HbIu` on 13.10.16.
 */
public class GeneratorTest {
    final static Board board=new Board();
    final static Generator generator= new Generator(BOMB_TYPE, board);

    @Before
    public void setUp() throws Exception {
        generator.generateBoard();
    }

    @Test
    public void whenGeneratedBoardThenAllCellIsEMPTY() throws Exception {

        searchOnBoard(Cell.EMPTY, 0);
    }

    @Test
    public void whenMinesGeneratedThenMinesCountEqualsBombCount() throws Exception {
        generator.generateMines(new Point(5,5));
        searchOnBoard(Cell.BOMB, currentBombsCount);
    }


    private void searchOnBoard(Cell cell, int cBombCount){
        ICell c;
        int count=0;
        for (Map.Entry<Point,ICellState> entry:board){
            c = entry.getValue().getCell();
            if(cell==Cell.EMPTY){
                assertTrue(cell==c);
            }
            if(c==BOMB_TYPE) {
                count++;
            }else if(c==null) {
                fail();
            }
        }
        if (cBombCount>0) {
            assertEquals(cBombCount,count);
        }
    }

}