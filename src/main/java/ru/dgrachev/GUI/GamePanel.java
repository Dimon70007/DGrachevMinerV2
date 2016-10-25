package ru.dgrachev.GUI;

import ru.dgrachev.game.ICell;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
//        setPreferredSize(new Dimension(width,height));
        setBackground(Color.white);
//        setVisible(true);
    }

    @Override
    public void drawBoard(Map<Point,ICell> cells){
        this.cells=cells;
        if (SwingUtilities.isEventDispatchThread()){
            repaint();
        } else {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    repaint();
                }
            });
        }
    }


    @Override
    public Dimension getPreferredSize() {
        //установили правильный размер
        return getPanelSize();
    }

    @Override
    public Dimension getPanelSize() {
        return new Dimension(width,height);
    }

    //overriding paint() method of java.awt.Component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image;
        Point p;
        if (cells!=null) {
            for (Map.Entry<Point, ICell> entry : this.cells.entrySet()) {
                p = entry.getKey();
                try {
                    image = entry.getValue().getImage();
                    //drawing the matrix of cells with size==CELLSIZE
                    g.drawImage(image, p.x * CELL_SIZE, p.y * CELL_SIZE, CELL_SIZE, CELL_SIZE, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
