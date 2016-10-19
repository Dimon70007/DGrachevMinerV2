package ru.DGrachev.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by OTBA}|{HbIu` on 19.10.16.
 */
public class FileRecordsTest {
    @Test
    public void readRecordsTest() throws Exception {

    }

    @Test
    public void writeRecordsTest() throws Exception {

    }

    @Test
    public void parsePlayerTest() throws Exception {
        String time="18:54:54";
        Player p=new Player(time);
        String parseStr=p.toString();
        Player parsePlayer=FileRecords.parsePlayer(parseStr);
        assertEquals(p.getName(),parsePlayer.getName());
        assertEquals(p.getBoardSize(),parsePlayer.getBoardSize());
        assertEquals(p.getBombsCount(),parsePlayer.getBombsCount());
        assertEquals(p.getDifficult(),parsePlayer.getDifficult());
        assertEquals(p.getRecordTime(),parsePlayer.getRecordTime());


    }


    @Test
    public void parsePlayersTest() throws Exception {
//        String patternTime="00:00:";
//        NavigableSet<Player> players=new TreeSet<>();
//        for (int i=10;i<50;i+=3){
//            players.add(new Player(patternTime+i));
//        }
    }
}