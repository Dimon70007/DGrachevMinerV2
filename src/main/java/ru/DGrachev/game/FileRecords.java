package ru.DGrachev.game;

import java.awt.*;
import java.io.*;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by otvazhniy on 18.10.16.
 */
public class FileRecords {

    protected static final String STATISTICS_PATH="/res/records.txt";
    protected static final int MAX_RECORDS=100;


    public static String readRecords() {
        String result="";
        try {
            FileReader file=new FileReader(STATISTICS_PATH);
            BufferedReader buffer=new BufferedReader(file);
            String line="";
            while((line=buffer.readLine())!=null){
                result+=line;
            }
            buffer.close();
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
            players.add(player);
            parsePlayers(file,players);

//            int index = hasNewRecordTime(players, player);
//
//            if (players.size()<=index+1) {
//                players.add(player);
//            }else {
//                players.add(index, player);
//            }

            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));

            String line="";
            int maxRecords=MAX_RECORDS;
            for (Player p:players) {
                bufferedWriter.write(player.toString());
                bufferedWriter.newLine();
                if (maxRecords<=0)
                    break;
                maxRecords--;
            }
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println("Input error");
        }

    }

    public static void parsePlayers(File file,NavigableSet<Player> players) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        if(line!=null) {
            Pattern pt = Pattern.compile(":+,");
            Matcher mt;
            do {
                mt = pt.matcher(line);
                if (mt.find()) {
                    players.add(parsePlayer(mt.group()));
                }
                line = bufferedReader.readLine();
            } while (line != null);

            bufferedReader.close();

        }
    }

    public static Player parsePlayer(String group) {

        String params=group.replaceAll(":","");
        String[] modParams=params.split(",");

        String name=modParams[0].trim();
        String time=modParams[1].trim();
        Difficult difficult=Difficult.valueOf(modParams[2].trim());
        String[] bSizeArr=modParams[3].trim().split("x");
        Point boardSize=new Point(Integer.valueOf(bSizeArr[0]),Integer.valueOf(bSizeArr[1]));
        int bombCount=Integer.valueOf(modParams[4].trim());

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
