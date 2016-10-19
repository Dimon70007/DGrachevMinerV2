package ru.dgrachev.GUI;

import ru.dgrachev.game.ICell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Map;

import static ru.dgrachev.game.GameParameters.CELL_SIZE;
import static ru.dgrachev.game.GameParameters.currentBoardSize;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class GamePanel extends JPanel implements IGamePanel {
    private Map<Point,ICell> cells;
    //store window parameters in pix
    private final int width;
    private final int height;



    public GamePanel() {
        Point cellsLength = currentBoardSize;
        this.width= cellsLength.x*CELL_SIZE;
        this.height= cellsLength.y*CELL_SIZE;
        setPreferredSize(new Dimension(width,height));
        setVisible(true);
    }

    @Override
    public void drawBoard(Map<Point,ICell> cells){
        this.cells=cells;
        repaint();//using repaint() method of java.awt.Component
    }


    @Override
    public Dimension getPreferredSize() {
        //установили правильный размер
        return new Dimension(width,height);
    }

    @Override
    public Dimension getPanelSize() {
        return new Dimension(width,height);
    }

    //override addMouseListener method of java.awt.Component чтобы 1 вызовом сразу добалять
    // в слушателя мыши все возможные варианты слушателей... не пригодилось
    @Override
    public void addMouseListener(MouseListener listener){
        super.addMouseListener(listener);

    }


    //override addKeyListener method of java.awt.Component чтобы 1 вызовом сразу добалять
    // в слушателя клавиатуры все возможные варианты слушателей... не пригодилось
    @Override
    public void addKeyListener(KeyListener listener){
        super.addKeyListener(listener);
    }


    //overriding paint() method of java.awt.Component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image;
        Point p;
        for(Map.Entry<Point,ICell> entry:this.cells.entrySet()){
            p=entry.getKey();
            image=entry.getValue().getImage();
            g.drawImage(image,p.x*CELL_SIZE,p.y*CELL_SIZE,null);
        }
//        Graphics2D g2=(Graphics2D)g;
//        Rectangle2D rect=new Rectangle2D.Double(400,400,200,150);
//
    }

}
