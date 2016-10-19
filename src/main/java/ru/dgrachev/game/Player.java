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

    public Player(String player, String recordTime, Difficult difficult, Point boardSize, int bombsCount) {
        this.player = player;
        this.recordTime = recordTime;
        this.boardSize = boardSize;
        this.bombsCount = bombsCount;
        this.difficult = difficult;
    }

    public Player(String recordTime) {
        this.player = GameParameters.playerName;
        this.boardSize = GameParameters.currentBoardSize;
        this.bombsCount = GameParameters.currentBombsCount;
        this.difficult = GameParameters.currentDifficult;
        this.recordTime=recordTime;
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
                "currentDifficult- " +
                difficult +
                ", " +
                "Board width- " +
                boardSize.x +
                ", " +
                "Board height-"+
                boardSize.y +
                ", " +
                "count of bombs- " +
                bombsCount;
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
