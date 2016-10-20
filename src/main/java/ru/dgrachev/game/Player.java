package ru.dgrachev.game;

import java.awt.*;
import java.time.LocalTime;

/**
 * Created by otvazhniy on 18.10.16.
 */
public class Player implements Comparable<Player>{
    private final String player;
    private String recordTime;
    private final Point boardSize;
    private final int bombsCount;
    private final Difficult difficult;
    private final String localDateTime;

    public Player(String player, String recordTime, Difficult difficult, Point boardSize, int bombsCount,String localDateTime) {
        this.player = player;
        this.recordTime = recordTime;
        this.boardSize = boardSize;
        this.bombsCount = bombsCount;
        this.difficult = difficult;
        this.localDateTime = localDateTime;
    }

    public Player(String recordTime, String localDateTime) {
        this(GameParameters.playerName,
                recordTime,
                GameParameters.currentDifficult,
                GameParameters.currentBoardSize,
                GameParameters.currentBombsCount,
                localDateTime);
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public String getPlayer() {
        return player;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public Point getBoardSize() {
        return boardSize;
    }

    public int getBombsCount() {
        return bombsCount;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public final String toString(){
        //        if (currentDifficult==Difficult.CUSTOM) {
        //        }

        return "player- " +
                player +
                ", " +
                "time- " +
                recordTime +
                ", " +
                "difficult- " +
                difficult +
                ", " +
                "board width- " +
                boardSize.x +
                ", " +
                "board height-"+
                boardSize.y +
                ", " +
                "count of bombs- " +
                bombsCount +
                ", "+
                "DATE- "+
                getLocalDateTime();
    }

    @Override
    public int compareTo(Player p) {

        int currentTime= LocalTime.parse(this.getRecordTime()).toSecondOfDay();

        int comparedtTime= LocalTime.parse(p.getRecordTime()).toSecondOfDay();

        if(currentTime<comparedtTime)
            return -1;
        if(currentTime>comparedtTime)
            return 1;
        return 0;
    }
}
