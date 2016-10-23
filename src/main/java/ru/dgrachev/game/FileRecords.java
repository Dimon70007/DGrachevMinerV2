package ru.dgrachev.game;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.dgrachev.game.GameParameters.MAX_RECORDS;

/**
 * Created by otvazhniy on 18.10.16.
 */
public class FileRecords {

    public static final String STATISTICS_PATH= "res/records.txt";

    public static NavigableSet<Player> read() {
        File file=new File(STATISTICS_PATH);

        NavigableSet<Player> players= new TreeSet<>();
        parsePlayers(players,file);
        return players;
    }

    public static void write(Player player) {
        try{
            Set<Player> players=read();
            players.add(player);

            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(STATISTICS_PATH));
            String line="";
            int maxRecords=MAX_RECORDS;
            for (Player p:players) {
                if (maxRecords==0)
                    break;
                bufferedWriter.write(p.toString());
                bufferedWriter.newLine();
                maxRecords--;
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println("Input error");
        }

    }

    public static void parsePlayers(NavigableSet<Player> players, File file) {
        try {

            List<String> lines= Files.readAllLines(Paths.get(file.getPath()), StandardCharsets.UTF_8);
                Pattern pt = Pattern.compile("-.+?(,|$)");//парсим строку берем все что между - и , или между - и концом строки
                Matcher mt;
            for (String line:lines){
                mt=pt.matcher(line);
                String parseString="";
                while (mt.find()){
                    parseString+=mt.group();
                }
                players.add(parsePlayer(parseString));
            }
        } catch (IOException e) {
            try {
                FileWriter fr=new FileWriter(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
//            e.printStackTrace();
        }
    }

    public static Player parsePlayer(String s) {

        String params=s.replaceAll("-","");//убираем минус
        String[] modParams=params.split(",");//нарезаем по запятой

        String name=modParams[0].trim();
        String time=modParams[1].trim();
        Difficult difficult=Difficult.valueOf(modParams[2].trim());
        Point boardSize=new Point(Integer.valueOf((modParams[3]).trim()),Integer.valueOf((modParams[4]).trim()));
        int bombCount=Integer.valueOf(modParams[5].trim());
        String localDateTime=modParams[6].trim();
        return new Player(name,time,difficult,boardSize,bombCount,localDateTime);
    }

}
