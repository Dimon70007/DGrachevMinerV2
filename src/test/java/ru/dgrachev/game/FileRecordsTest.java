package ru.dgrachev.game;

import org.junit.Test;

import java.io.FileWriter;
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
    public void writeAndReadRecordsTest() throws Exception {
        String patternTime="00:00:";
        NavigableSet<Player> players=new TreeSet<>();
        Player p;
        //очищаем файл статистики от предыдущих результатов
        //чтобы проверить что чтение и запись проходят на 100 процентов правильно
        FileWriter fr=new FileWriter(FileRecords.STATISTICS_PATH);
        fr.flush();
        fr.close();
        //записываем
        for (int i=10;i<50;i+=3){
            p=new Player(patternTime+i,PlayerTest.localDateTime);
            players.add(p);
            FileRecords.write(p);
            Thread.sleep(10);
        }
        //считываем
        NavigableSet<Player> readPlayers= FileRecords.read();
        Player readP;
        //проверяем
        while (players.size()!=0){
            //check lockaldatetime
            p=players.pollFirst();
            readP=readPlayers.pollFirst();
            assertEquals(p.toString(),readP.toString());

        }
    }

    @Test
    public void parsePlayerTest() throws Exception {
        String time="18:54:54";
        Player p=new Player(time,PlayerTest.localDateTime);
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