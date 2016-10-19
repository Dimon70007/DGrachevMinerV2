package ru.dgrachev.game;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.fail;
import static ru.dgrachev.game.GameParameters.BOMB_TYPE;
import static ru.dgrachev.game.GameParameters.currentBombsCount;

/**
 * Created by OTBA}|{HbIu` on 13.10.16.
 */
public class GeneratorTest {
    private Board board=new Board();
    private final Generator generator= new Generator();

    @Before
    public void setUp() throws Exception {
        generator.generateBoard(board);
    }

//    @Test
//    public void whenGeneratedBoardThenAllCellIsEMPTY() throws Exception {
//
//        searchOnBoard(Cell.EMPTY, 0);
//    }

    @Test
    public void whenMinesGeneratedThenMinesCountEqualsBombCount() throws Exception {
        generator.generateMines(board,new Point(5,5),BOMB_TYPE);
        searchOnBoard(Cell.BOMB, currentBombsCount);
    }


    private void searchOnBoard(Cell cell, int cBombCount){
        ICell c;
        int count=0;
        for (Map.Entry<Point,ICell> entry:board){
            c = entry.getValue();
            if(currentBombsCount ==0) {
                assertEquals(cell, c);
            }else if(c==BOMB_TYPE) {
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