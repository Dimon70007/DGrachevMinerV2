package ru.dgrachev.game;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 14.10.16.
 */
public class CellTest {
    @Test
    public void getImage() throws Exception {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
    public static void createGUI(){

        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img;
                img = Cell.CLOSED.getImage();
                g.drawImage(img, 0, 0,32,32, null);
                g.fillOval(200, 200, 100, 50);
                repaint();
            }
        };
        panel.setLayout(new FlowLayout());

        frame.add(panel);
        frame.setPreferredSize(new Dimension(400,300));
//        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
//                System.out.println(Cell.CLOSED.getUrl());
    }
}