package ru.dgrachev.game;

import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by otvazhniy on 18.10.16.
 */
public class FileRecords {

    protected static final String STATISTICS_PATH= "res/records.txt";
    protected static final int MAX_RECORDS=10;


    public static String readRecords() {
        String result="";
        try {
            List<String> lines= Files.readAllLines(Paths.get(STATISTICS_PATH), StandardCharsets.UTF_8);
            for (String line:lines){
                result+=line;
            }
            return result;
        }catch(IOException e){
            System.out.println("Input file error");
        }
        return result;
    }

    public static void writeRecords(Player player) {
        try{
            File file=new File(STATISTICS_PATH);
            NavigableSet<Player> players=new TreeSet<Player>();

            parsePlayers(players);
            players.add(player);

//            int index = hasNewRecordTime(players, player);
//
//            if (players.size()<=index+1) {
//                players.add(player);
//            }else {
//                players.add(index, player);
//            }

            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(STATISTICS_PATH));
            String line="";
            int maxRecords=MAX_RECORDS;
            for (Player p:players) {
                bufferedWriter.write(player.toString());
                bufferedWriter.newLine();
                if (maxRecords<=0)
                    break;
                maxRecords--;
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println("Input error");
        }

    }

    public static void parsePlayers(NavigableSet<Player> players) {
        try {

            List<String> lines= Files.readAllLines(Paths.get(STATISTICS_PATH), StandardCharsets.UTF_8);
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
            e.printStackTrace();
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

        return new Player(name,time,difficult,boardSize,bombCount);
    }


//    private static int hasNewRecordTime(ArrayList<Player> players, Player player) {
//        int playerTime= LocalTime.parse(player.getRecordTime()).toSecondOfDay();
//        Player p;
//        int index=0;
//        for (int i=0;i<players.size();i++){
//            p=players.get(i);
//            int recordTime= LocalTime.parse(p.getRecordTime()).toSecondOfDay();
//            if (player.getDifficult()==p.getDifficult()
//                    && playerTime<recordTime)
//                return i;
//            index++;
//        }
//        return index;
//    }

//
//    public static int searchInFile(String currentDifficult,File file) {
//        try{
//
//            FileReader fileReader=new FileReader(file);
//            BufferedReader buffer=new BufferedReader(fileReader);
//            String line="";
//            Pattern pt=Pattern.compile("("+currentDifficult+")");
//            Matcher mt;
//            for(int i=0;(line=buffer.readLine())!=null;i++){
//                mt=pt.matcher(line);
//                if(mt.find()){
//                    return i;
//                }
//            }
//            buffer.close();
//        }catch(IOException e){
//            System.out.println("Input file error");
//        }
//
//        return -1;
//    }
}
