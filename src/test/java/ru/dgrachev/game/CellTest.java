package ru.dgrachev.game;

import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Created by OTBA}|{HbIu` on 14.10.16.
 */
public class CellTest {
    @Test
    public void getImageTest() throws Exception {
        Image img;
        for (Cell cell:Cell.values()){
            try {
                img=cell.getImage();
                assertTrue(img!=null);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @Test
    public void whenCEmptyNextIsCOne() throws Exception{
        assertTrue(Cell.EMPTY.nextCell()==Cell.ONE);
    }
}