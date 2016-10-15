package ru.DGrachev.game;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 14.10.16.
 */
public class CellTest {
    @Test
    public void getImage() throws Exception {
        JFrame frame=new JFrame(){
            @Override
            public void paintComponents(Graphics g) {
                super.paintComponents(g);
                Image img=Cell.CLOSED.getImage();
                g.drawImage(img,100,100,null);
            }
        };

//        System.out.println(Cell.CLOSED.getUrl());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,300));
        frame.pack();
        frame.setVisible(true);
        while(true){
            frame.repaint();
            Thread.sleep(1000);
        }


    }

}