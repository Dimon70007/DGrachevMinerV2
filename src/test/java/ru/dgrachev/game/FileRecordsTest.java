package ru.dgrachev.game;

import org.junit.Test;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by OTBA}|{HbIu` on 19.10.16.
 */
public class FileRecordsTest {
    private Player parsePlayer;

    @Test
    public void writeAndReadRecordsTest() throws Exception {
        String patternTime="00:00:";
        NavigableSet<Player> players=new TreeSet<>();
        Player p;
        for (int i=10;i<50;i+=3){
            p=new Player(patternTime+i);
            players.add(p);
            FileRecords.write(p);
            Thread.sleep(10);
        }
        Set<Player> readedPlayers=FileRecords.read();
        players.removeAll(readedPlayers);
        assertTrue(players.size()==0);
    }

    @Test
    public void parsePlayerTest() throws Exception {
        String time="18:54:54";
        Player p=new Player(time);
        String parseStr=p.toString();

        Pattern pt = Pattern.compile("-.+?(,|$)");//ищем любую последовательность между - и , или  $
        Matcher mt = pt.matcher(parseStr);
        String parseString="";
        while (mt.find()){
            parseString+=mt.group();
        }
        parsePlayer= FileRecords.parsePlayer(parseString);
        assertEquals(p.getPlayer(),parsePlayer.getPlayer());
        assertEquals(p.getBoardSize(),parsePlayer.getBoardSize());
        assertEquals(p.getBombsCount(),parsePlayer.getBombsCount());
        assertEquals(p.getDifficult(),parsePlayer.getDifficult());
        assertEquals(p.getRecordTime(),parsePlayer.getRecordTime());
    }

}