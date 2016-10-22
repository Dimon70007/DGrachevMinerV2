package ru.dgrachev.game;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class GameParameters {

    public static final Point EASY_BOARD_SIZE=new Point(9,9);
    public static final Point MEDIUM_BOARD_SIZE=new Point(16,16);
    public static final Point HARD_BOARD_SIZE=new Point(30,20);

    public static final int EASY_BOMB_COUNT=(int) (EASY_BOARD_SIZE.x*EASY_BOARD_SIZE.y*0.15);
    public static final int MEDIUM_BOMB_COUNT=(int) (MEDIUM_BOARD_SIZE.x*MEDIUM_BOARD_SIZE.y*0.2);
    public static final int HARD_BOMB_COUNT=(int) (HARD_BOARD_SIZE.x*HARD_BOARD_SIZE.y*0.25);

    public static Point currentBoardSize =EASY_BOARD_SIZE;
    public static int currentBombsCount =EASY_BOMB_COUNT;
    public static String playerName="Unknown";
    public static Difficult currentDifficult =Difficult.EASY;

    public static final Cell BOMB_TYPE=Cell.BOMB;
    public static final int CELL_SIZE=24;

    public static final String GAME_NAME="Miner V2.0";

}
