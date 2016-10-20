package ru.dgrachev.game;

import ru.dgrachev.GUI.IGUI;
import ru.dgrachev.game.Exceptions.LooseException;
import ru.dgrachev.game.Exceptions.WinException;

import java.awt.*;
import java.text.SimpleDateFormat;
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
    private Board board;
    private long beginTime;
    private long currentGameTime;
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm:ss");

    private boolean firstUserPoint=true;

    public Game(Board board, IGUI gui, IGenerate generator) {
        this.board = board;
        this.generator=generator;
        this.gui=gui;
        generator.generateBoard(board);
        gui.drawBoard(resultBoardWithChangedBombs(null,false));
        beginTime=System.currentTimeMillis();
        gui.updateTime("00:00:00");


    }

    @Override
    public void checkLoose(ICell cell) throws LooseException {
        if(cell==Cell.BOMB)
            throw new LooseException();
    }

    @Override
    public void checkWin() throws WinException {
        ICell c;
        for(Map.Entry<Point,ICell> entry:board){
            c=entry.getValue();
            if(c== Cell.EMPTY || c.getNumber() >0 ){
                if(!c.isOpen())
                    return;
            }
        }
        throw new WinException();
    }

    @Override
    public void openCell(Point point) {
        if(firstUserPoint){
            generator.generateMines(board,point,BOMB_TYPE);
            firstUserPoint=false;
        }
        updateGameTime();
        ICell cell=board.getCell(point);
        cell.setOpened();
        try {
            checkLoose(cell);

            if(cell==Cell.EMPTY)
                openCellsOnBoard(point,cell);

            checkWin();
        } catch (LooseException e) {
            gui.drawBoard(resultBoardWithChangedBombs(Cell.EXPLOSION,true));
            gui.gameOver();
        } catch (WinException e) {

            gui.drawBoard(resultBoardWithChangedBombs(Cell.BOMB,true));
            gui.congratulations();
            saveCurrentGameTime();
        }

    }



    @Override
    public void setFlag(Point point) {
        board.getCell(point).setFlag();
    }

    @Override
    public void updateGameTime() {
        long tmpTime=System.currentTimeMillis();
        currentGameTime=tmpTime-beginTime;
        LocalTime locGameTime = LocalTime.ofSecondOfDay((currentGameTime)/1000);
        gui.updateTime(locGameTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    private void saveCurrentGameTime(){
        //посчитал что время дешевле всего хранить в милисекундах а не в объекте
        //тем более что вызов этого метода происходит 1 раз за игру
        LocalTime locGameTime = LocalTime.ofSecondOfDay((currentGameTime)/1000);
        String recordTime=locGameTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime localDateTime=LocalDateTime.now();
        Player p=new Player(recordTime,localDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss")));
        FileRecords.write(p);
    }

    private void openCellsOnBoard(Point point, ICell targetCell) {

        ICell c;
        Point p;
        Map<Point,ICell> openedCells=new HashMap<>();
        int noExit;
        while (true) {
            noExit=0;
            for (Map.Entry<Point, ICell> entry : board) {
                p = entry.getKey();
                c = entry.getValue();
                if (c == Cell.EMPTY && c.isOpen())
                    noExit+=goAroundCell(p, true);
            }
            if(noExit==0)
                break;//тут мы получим на поле после 2 хода игрока часть открытых ячеек пустые а часть с цифрами
        }

        for (Map.Entry<Point, ICell> entry : board) {
            p = entry.getKey();
            c = entry.getValue();
            if (c == Cell.EMPTY && c.isOpen())
                noExit+=goAroundCell(p, false);
        }

    }

    private int goAroundCell(Point point, boolean isGoToEmpty) {
        //обходим board начиная с переданной ячейки вокруг нее с радиусом 1 и если она EMPTY ставим setOpened()
        Point p;
        ICell c;
        int countOfChange=0;
        for (int x=point.x-1;x<=point.x+1;x++){
            for(int y=point.y-1;y<=point.y+1;y++){
                p=new Point(x,y);
                c=board.getCell(p);
                if(c==null || c.isOpen())
                    continue;//скорее всего мы вышли за границу доски или ячейка уже открыта
                if(isGoToEmpty) {
                    //если флаг false значит мы идем +100500 раз по пустым закрытым ячейкам и открываем их
                    if (c == Cell.EMPTY && !c.isOpen()) {
                        c.setOpened();//open closed empty cell
                        countOfChange++;
                    }
                }else {
                    c.setOpened();//тут мы открываем все закрытые ячейки в радиусе 1 от текущей
                }
            }
        }
        return countOfChange;

    }

    private Map<Point, ICell> resultBoardWithChangedBombs(Cell targetCell, boolean doesNeedToChangeBombsOrReturnExistingBoard) {
        ICell c;
        Point p;
        Map<Point, ICell> tmp=new HashMap<>();
        for(Map.Entry<Point,ICell> entry:board){
            c=entry.getValue();
            p=entry.getKey();
            //если мы указали что нужно заменить бомбу на что-то другое (на взрыв например)
            //то в этом if() бомба заменяется на targetCell
            if(doesNeedToChangeBombsOrReturnExistingBoard) {
                if (c == Cell.BOMB)
                    tmp.put(p,targetCell);
            }else if(!c.isOpen()) {
                tmp.put(p, Cell.CLOSED);
            }else if(c.getFlag()!=Cell.CLOSED) {
                tmp.put(p, c.getFlag());
            }else {
                tmp.put(p, c);
            }
        }
        return tmp;
    }

}
