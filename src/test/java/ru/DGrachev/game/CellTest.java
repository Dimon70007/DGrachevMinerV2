package ru.DGrachev.game;

import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by OTBA}|{HbIu` on 14.10.16.
 */
public class CellTest {
    @Test
    public void getImage() throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new JFrame();
                JComponent component=new JComponent() {
                    @Override
                    public void paintComponents(Graphics g) {
                        super.paintComponents(g);
                        Image img= null;
                        try {
                            img = ImageIO.read(Cell.class.getResourceAsStream("CLOSED.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        g.drawImage(img,100,100,null);
                        g.fillOval(300,200,100,50);
                    }
                };

                component.setPreferredSize(new Dimension(400,300));
                frame.add(component);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(400,300));
                frame.pack();
                frame.setVisible(true);

                frame.repaint();
//                System.out.println(Cell.CLOSED.getUrl());

            }
        });




    }

}