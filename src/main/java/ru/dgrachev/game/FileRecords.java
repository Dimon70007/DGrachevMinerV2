package ru.dgrachev.game;

import ru.dgrachev.Main;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.dgrachev.game.GameParameters.MAX_RECORDS;

/**
 * Created by otvazhniy on 18.10.16.
 */
public class FileRecords {

    public final static String RECORDS ="records.txt";
    public final static Path FULL_STATICTICS_PATH;
    private static BufferedWriter writer;
    private final static File curDir;

//вроде как должна быть инициализация статик констант
    static {
        curDir =new File(".");
        FULL_STATICTICS_PATH =Paths.get(curDir.getPath(), RECORDS);
    //Paths.get(String ... args);=>curDir="arg1/arg2/arg3/..."
    //или можно еще curDir.resolve(RECORDS);=> curDir="curDir"+"/"+"records.txt"
    //при чем разделитель берется в зависимости от ОС автоматом

    }

    public static NavigableSet<Player> read() {

        NavigableSet<Player> players= new TreeSet<>();
        parsePlayers(players);
        return players;
    }

    public static void write(Player player) {

        try{
            Set<Player> players=read();
            players.add(player);
            writer=Files.newBufferedWriter(FULL_STATICTICS_PATH,StandardCharsets.UTF_8);
            int maxRecords=MAX_RECORDS;
            for (Player p:players) {
                if (maxRecords==0)
                    break;
                writer.write(p.toString());
                writer.newLine();
                maxRecords--;
            }
        }catch(IOException e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    public static void parsePlayers(NavigableSet<Player> players) {
        try {

            List<String> lines= Files.readAllLines(FULL_STATICTICS_PATH, StandardCharsets.UTF_8);
            //парсим строку берем все что между - и , или между - и концом строки
            Pattern pt = Pattern.compile("-.+?(,|$)");
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
                writer=Files.newBufferedWriter(FULL_STATICTICS_PATH, StandardCharsets.UTF_8);
            } catch (IOException e1) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }finally {
                try {
                    writer.close();
                } catch (IOException e1) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }

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

//    /**
//     * @return Путь к каталогу, в котором расположен jar-файл с классом
//     *         ApplicationStartUpPath.
//     */
//    public static Path getApplicationStartUp() {
//        URL startupUrl = FileRecords.class.getProtectionDomain().getCodeSource()
//                .getLocation();
//        Path curDir = null;
//        try {
//            curDir = Paths.get(startupUrl.toURI());
//        } catch (FileSystemNotFoundException e) {
//            try {
//                curDir = Paths.get(new URL(startupUrl.getPath()).getPath());
//            } catch (Exception ipe) {
//                curDir = Paths.get(startupUrl.getPath());
//            }
//        } catch (IllegalArgumentException | SecurityException | URISyntaxException e) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
//        }
//        if (curDir!=null)
//            curDir = curDir.getParent();
//        return curDir;
//    }

}
