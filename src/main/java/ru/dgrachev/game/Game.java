package ru.dgrachev.game;

import ru.dgrachev.GUI.IGUI;
import ru.dgrachev.game.Exceptions.LooseException;
import ru.dgrachev.game.Exceptions.WinException;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static ru.dgrachev.game.GameParameters.BOMB_TYPE;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class Game implements IGame {
    private final IGenerate generator;
    private final IGUI gui;
    private final Board board;
    private long beginTime;
    private long currentGameTime;
//    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");
    private final static int DONT_NEED_OPEN_BOMB=0;
    private final static int OPEN_BOMB_AS_EXPLOSION=1;
    private final static int OPEN_BOMB_AS_BOMB=2;

    private boolean firstUserPoint=true;
    private boolean gameOver=false;
    private Thread threadThatUpdateGameTime;

    public Game(Board board, IGUI gui, IGenerate generator) {
        this.board = board;
        this.generator=generator;
        this.gui=gui;
        generator.generateBoard(board);
        gui.drawBoard(resultBoardWithChangedBombs(DONT_NEED_OPEN_BOMB));
        gui.updateTime("00:00:00");
    }

    public Game(Board board,IGUI gui) {
        this(board,gui,new Generator(BOMB_TYPE));
    }

    public Game(IGUI gui) {
        this(new Board(),gui);
    }

    @Override
    public void openCell(Point point) {
        if (gameOver)
            return;

        if(firstUserPoint)
            startGame(point);

        ICellState cellState=board.getCellState(point);
        if (cellState.getFlag()==Cell.FLAG)
            return;

        try {
            checkLoose(cellState);
        } catch (LooseException e) {
            gameOver=true;
            gui.drawBoard(resultBoardWithChangedBombs(OPEN_BOMB_AS_EXPLOSION));
            gui.gameOver();
            joinThreadThatUpdateGameTime();
            return;
        }
        //change board's state
        openCellsOnBoard(point);

            try {
                checkWin();
            }catch (WinException e) {
                gameOver=true;
                saveCurrentGameTime();
                gui.drawBoard(resultBoardWithChangedBombs(OPEN_BOMB_AS_BOMB));
                gui.congratulations();
                joinThreadThatUpdateGameTime();
                return;
        }
        //simple draw board with it's current state
        gui.drawBoard(resultBoardWithChangedBombs(DONT_NEED_OPEN_BOMB));
    }




    @Override
    public void checkLoose(ICellState cellState) throws LooseException {
        if(cellState.getCell()==Cell.BOMB)
            throw new LooseException();
    }

    @Override
    public void checkWin() throws WinException {
        ICellState cs;
        for(Map.Entry<Point,ICellState> entry:board){
            cs=entry.getValue();
            if( cs.getCell() !=BOMB_TYPE){
                if(!cs.isOpen())
                    return;
            }
        }
        throw new WinException();
    }

    @Override
    public void setFlag(Point point) {
        if (gameOver)
            return;
        board.getCellState(point).setFlag();
        gui.drawBoard(resultBoardWithChangedBombs(DONT_NEED_OPEN_BOMB));
    }

    @Override
    public void updateGameTime() {
        long tmpTime=System.currentTimeMillis();
        currentGameTime=tmpTime-beginTime;
        LocalTime locGameTime = LocalTime.ofSecondOfDay((currentGameTime)/1000);
        gui.updateTime(locGameTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    //this method has been set package private for testing
    void openCellsOnBoard(Point point) {
        ICellState cs=board.getCellState(point);
        cs.setOpened();
        if (cs.getCell()!=Cell.EMPTY)
            return;//если ячейка не пустая -открываем только ее

        Point p;
        int exit;
        while (true) {
            exit=0;
            for (HashMap.Entry<Point, ICellState> entry : board) {
                p = entry.getKey();
                cs=entry.getValue();
                if (cs.getCell()==Cell.EMPTY && cs.isOpen())
                    exit+=goAroundCell(p, true);
            }
            if(exit==0)
                break;//тут мы получим на поле после 2 хода игрока
            // часть открытых ячеек - пустые а часть - с цифрами
        }

        for (Map.Entry<Point, ICellState> entry : board) {
            p = entry.getKey();
            cs = entry.getValue();
            //open closed cells around empty opened cell
            if (cs.getCell() == Cell.EMPTY && cs.isOpen())
                goAroundCell(p, false);
        }

    }

    private Map<Point, ICell> resultBoardWithChangedBombs(int flag) {
        ICellState cs;
        Point p;
        Map<Point, ICell> result=new HashMap<>();
        for(Map.Entry<Point,ICellState> entry:board){
            cs=entry.getValue();
            p=entry.getKey();
            if(!cs.isOpen()) {
                if (cs.getFlag() != Cell.CLOSED) {
                    result.put(p, cs.getFlag());
                } else {
                    result.put(p, Cell.CLOSED);
                }
            }else {
                result.put(p, cs.getCell());
            }
            if (flag==OPEN_BOMB_AS_BOMB) {
                if (cs.getCell()==BOMB_TYPE)
                    result.put(p, BOMB_TYPE);
                continue;
            }
            if (flag==OPEN_BOMB_AS_EXPLOSION) {
                if (cs.getCell()==BOMB_TYPE)
                    result.put(p, Cell.EXPLOSION);
            }
        }
        return result;
    }

    private void startGame(Point point) {
        generator.generateMines(board,point);
        firstUserPoint=false;
        threadThatUpdateGameTime =new Thread(new Runnable(){
            public void run(){
                while (!gameOver){
                    updateGameTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadThatUpdateGameTime.start();
        beginTime=System.currentTimeMillis();
    }
    private void saveCurrentGameTime(){
        //посчитал что время дешевле всего хранить в милисекундах а не в объекте
        //тем более что вызов этого метода происходит 1 раз за игру
        new Thread(new Runnable() {
            @Override
            public void run() {
                LocalTime locGameTime = LocalTime.ofSecondOfDay((currentGameTime)/1000);
                String recordTime=locGameTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalDateTime localDateTime=LocalDateTime.now();
                Player p=new Player(recordTime,localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
                FileRecords.write(p);
            }
        }).start();
    }
    private void joinThreadThatUpdateGameTime() {
        try {
            threadThatUpdateGameTime.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private int goAroundCell(Point point, boolean isGoToEmpty) {
        //обходим board начиная с переданной ячейки вокруг нее с радиусом 1 и если она EMPTY ставим setOpened()
        Point p;
        ICellState cs;
        int result=0;
//        Map<Point,ICellState> result=new HashMap<>();
        for (int x=point.x-1;x<=point.x+1;x++){
            for(int y=point.y-1;y<=point.y+1;y++){
                p=new Point(x,y);
                cs=board.getCellState(p);
                if(cs==null || cs.isOpen())
                    continue;//скорее всего мы вышли за границу доски или ячейка уже открыта
                if(isGoToEmpty) {
                    //если isGoToEmpty значит мы идем по пустым закрытым ячейкам в радиусе 1 и открываем их
                    if (cs.getCell() == Cell.EMPTY) {
                        cs.setOpened();//open closed empty cell
//                        result.put(p,cs);
                        result++;
                    }
                }else {
                    cs.setOpened();//тут мы открываем все закрытые ячейки в радиусе 1 от текущей
                }
            }
        }
        return result;
    }

}
