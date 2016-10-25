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

    private final static String RECORDS ="records.txt";
    final static Path FULL_STATISTIC_PATH;
    private static BufferedWriter writer;
    private final static File CUR_DIR;

//вроде как должна быть инициализация статик констант
    static {
        CUR_DIR =new File(".");
        FULL_STATISTIC_PATH =Paths.get(CUR_DIR.getPath(), RECORDS);
    //Paths.get(String ... args);=>CUR_DIR="arg1/arg2/arg3/..."
    //или можно еще CUR_DIR.resolve(RECORDS);=> CUR_DIR="CUR_DIR"+"/"+"records.txt"
    //при чем разделитель берется в зависимости от ОС автоматом

    }

    public static NavigableSet<Player> read() {

        NavigableSet<Player> players= new TreeSet<>();
        parsePlayers(players);
        return players;
    }

    static void write(Player player) {

        try{
            Set<Player> players=read();
            players.add(player);
            writer=Files.newBufferedWriter(FULL_STATISTIC_PATH,StandardCharsets.UTF_8);
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

    private static void parsePlayers(final NavigableSet<Player> players) {
        try {
            List<String> lines= Files.readAllLines(FULL_STATISTIC_PATH, StandardCharsets.UTF_8);
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
                writer=Files.newBufferedWriter(FULL_STATISTIC_PATH, StandardCharsets.UTF_8);
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

    static Player parsePlayer(String s) {

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
//        Path CUR_DIR = null;
//        try {
//            CUR_DIR = Paths.get(startupUrl.toURI());
//        } catch (FileSystemNotFoundException e) {
//            try {
//                CUR_DIR = Paths.get(new URL(startupUrl.getPath()).getPath());
//            } catch (Exception ipe) {
//                CUR_DIR = Paths.get(startupUrl.getPath());
//            }
//        } catch (IllegalArgumentException | SecurityException | URISyntaxException e) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
//        }
//        if (CUR_DIR!=null)
//            CUR_DIR = CUR_DIR.getParent();
//        return CUR_DIR;
//    }

}
