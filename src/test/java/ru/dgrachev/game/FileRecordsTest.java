package ru.dgrachev.game;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static ru.dgrachev.game.FileRecords.FULL_STATISTIC_PATH;

/**
 * Created by OTBA}|{HbIu` on 19.10.16.
 */
public class FileRecordsTest {
    private File file= new File(FULL_STATISTIC_PATH.toString());
    private Player parsePlayer;
    private FileWriter writer;
    @Test
    public void writeAndReadRecordsTest() throws Exception {
        String patternTime="00:00:";
        NavigableSet<Player> players=new TreeSet<>();
        Player p;
        //очищаем файл статистики от предыдущих результатов
        //чтобы проверить что чтение и запись проходят на 100 процентов правильно


        try{
             writer =new FileWriter(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
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
        writer.flush();
//        System.out.println(file.getAbsolutePath());
        //удаляем тестовый файл чтоб мусор не разводить
        file.delete();
        writer.close();
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