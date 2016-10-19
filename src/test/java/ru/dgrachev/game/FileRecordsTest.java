package ru.dgrachev.game;

import org.junit.Test;

import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Created by OTBA}|{HbIu` on 19.10.16.
 */
public class FileRecordsTest {
    private Player parsePlayer;
    @Test
    public void readRecordsTest() throws Exception {

    }

    @Test
    public void writeRecordsTest() throws Exception {
        String patternTime="00:00:";
        NavigableSet<Player> players=new TreeSet<>();
        for (int i=10;i<50;i+=3){
            FileRecords.writeRecords(new Player(patternTime+i));
        }
        String s=FileRecords.readRecords();
        System.out.println(s);
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
//        System.out.println(parseString);
        parsePlayer= FileRecords.parsePlayer(parseString);
//        System.out.println(parsePlayer.toString());
        assertEquals(p.getPlayer(),parsePlayer.getPlayer());
        assertEquals(p.getBoardSize(),parsePlayer.getBoardSize());
        assertEquals(p.getBombsCount(),parsePlayer.getBombsCount());
        assertEquals(p.getDifficult(),parsePlayer.getDifficult());
        assertEquals(p.getRecordTime(),parsePlayer.getRecordTime());
    }

}