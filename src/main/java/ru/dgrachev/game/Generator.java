package ru.dgrachev.game;

import java.awt.*;
import java.util.Random;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class Generator implements IGenerate {
    private final ICell bombType;

    private final Board board;

    public Generator(ICell bombType, Board board) {
        this.bombType = bombType;
        this.board = board;
    }

    @Override
    public void generateBoard() {
            Point size=board.getSize();
        for(int x=0;x<size.x;x++){
            for (int y=0;y<size.y;y++){
                   board.setCellState(new Point(x,y),new CellState(Cell.EMPTY));
            }
        }
    }

    @Override
    public void generateMines(Point userPoint) {
        Random r=new Random();
        int maxX=board.getSize().x;
        int maxY=board.getSize().y;
        int bombCount= GameParameters.currentBombsCount;
        for(int i=0;i<bombCount;i++){
            Point newPoint=new Point(Math.abs(r.nextInt()%maxX),Math.abs(r.nextInt()%maxY));
            //сначала проверяем что пользователь сюда не ткнул
            if(newPoint.x==userPoint.x && newPoint.y==userPoint.y) {
                i--;
                continue;
            }
                //if bomb allready exist - i-- and continue
            ICell ce=board.getCellState(newPoint).getCell();
            if (ce==null)
                System.out.println(ce);
            if(ce== bombType) {
                i--;
                continue;
            }
            //set the bomb
            board.setCellState(newPoint, new CellState(bombType));
            generateNumbers(board,newPoint);

        }
    }

    private void generateNumbers(Board board, Point newPoint) {
        Point p;
        for(int x=newPoint.x-1;x<=newPoint.x+1;x++){
            for (int y=newPoint.y-1;y<=newPoint.y+1;y++) {
                p=new Point(x,y);
                generateCountBombsAroundPoint(board,p);
            }

        }
    }

    private void generateCountBombsAroundPoint(Board board, Point newPoint) {
        ICellState cs = board.getCellState(newPoint);
        //скорее всего мы вышли за пределы поля или попали на бомбу
        // , поэтому пропускаем этот поинт
        if ( cs==null || cs.getCell() == bombType){
            return;
        }
        if(cs.getCell()== Cell.EMPTY){//set ONE
            board.setCellState(newPoint,new CellState(Cell.ONE));
            return;
        }
        if(cs.getCell().getNumber()>0){
            //если ячейка уже содержит цифру - мы ее увеличиваем на 1
            ICell cell=cs.getCell().nextCell();
            board.setCellState(newPoint,new CellState(cell));
        }
    }

}
