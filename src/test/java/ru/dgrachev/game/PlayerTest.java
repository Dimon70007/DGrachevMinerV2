package ru.dgrachev.game;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by OTBA}|{HbIu` on 20.10.16.
 */
public class PlayerTest {
    static String localDateTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));
    @Test
    public void compareToTest() throws Exception {
        Player p15=new Player("00:00:15",localDateTime);
        Player p16=new Player("00:00:16",localDateTime);
        Player otherp16=new Player("00:00:16",localDateTime);

        assertTrue(p15.compareTo(p16)==-1);
        assertTrue(p16.compareTo(p15)==1);
        assertTrue(p15.compareTo(p15)==0);
        assertTrue(p16.compareTo(otherp16)==0);
    }

    @Test
    public void getLocalDateTimeTest(){
        Player p=new Player("00:00:59", localDateTime);
        assertEquals(p.getLocalDateTime(),localDateTime);
    }
}