package ru.dgrachev.game;

import java.awt.*;
import java.util.Random;

import static ru.dgrachev.game.GameParameters.currentBombsCount;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class Generator implements IGenerate {

    @Override
    public  void generateBoard(Board board) {
            Point size=board.getSize();
        for(int x=0;x<size.x;x++){
            for (int y=0;y<size.y;y++){
                   board.setCell(new Point(x,y),Cell.EMPTY);
            }
        }
    }

    @Override
    public  void generateMines(Board board, Point userPoint,ICell bombType) {
        Random r=new Random();
        int maxX=board.getSize().x;
        int maxY=board.getSize().y;
        int bombCount= currentBombsCount;
        for(int i=0;i<bombCount;i++){
            Point newPoint=new Point(Math.abs(r.nextInt()%maxX),Math.abs(r.nextInt()%maxY));
            //сначала проверяем что пользователь сюда не ткнул
            if(newPoint.x==userPoint.x && newPoint.y==userPoint.y) {
                i--;
                continue;
            }
                //if bomb allready exist - i-- and continue
            if(board.getCell(newPoint)==bombType) {
                i--;
                continue;
            }
            //set the bomb
            board.setCell(newPoint,bombType);
            generateNumbers(board,newPoint,bombType);

        }
    }

    private static void generateNumbers(Board board, Point newPoint, ICell bombType) {
        Point p;
        for(int x=newPoint.x-1;x<=newPoint.x+1;x++){
            for (int y=newPoint.y-1;y<=newPoint.y+1;y++) {
                p=new Point(x,y);
                generateCountBombsAroundPoint(board,p,bombType);
            }

        }
    }

    private static void generateCountBombsAroundPoint(Board board, Point newPoint, ICell bombType) {
        ICell c = board.getCell(newPoint);
        //скорее всего мы вышли за пределы поля или попали на бомбу
        // , поэтому пропускаем этот поинт
        if ( c == bombType || c==null){
            return;
        }
        if(c== Cell.EMPTY){//set ONE
            c=board.setCell(newPoint,Cell.ONE);
            return;
        }
        if(c.getNumber()>0){
            c=c.nextCell();//если ячейка уже содержит цифру - мы ее увеличиваем на 1
            board.setCell(newPoint,c);
        }
    }

}
