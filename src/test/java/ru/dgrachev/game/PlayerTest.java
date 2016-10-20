package ru.dgrachev.game;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by OTBA}|{HbIu` on 20.10.16.
 */
public class PlayerTest {
    @Test
    public void compareTo() throws Exception {
        Player p15=new Player("00:00:15");
        Player p16=new Player("00:00:16");
        Player otherp16=new Player("00:00:16");

        assertTrue(p15.compareTo(p16)==-1);
        assertTrue(p16.compareTo(p15)==1);
        assertTrue(p15.compareTo(p15)==0);
        assertTrue(p16.compareTo(otherp16)==0);
    }

}