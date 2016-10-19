package ru.dgrachev;

import ru.dgrachev.game.Cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public class Main {
    public static void main(String[] args) {
//        long beginTime=System.currentTimeMillis();
//        try {
//            Thread.sleep(12345);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long tmpTime=System.currentTimeMillis();
//        long gameTime=tmpTime-beginTime;
//        LocalTime curTime=LocalTime.ofSecondOfDay(gameTime/1000);
//        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
////        String str=sdf.format();
//        System.out.println(curTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//                GamePanel gamePanel=new GamePanel();
//                GUI gui=new GUI(gamePanel);
//                Generator generator=new Generator();
//                Board board=new Board();
//                Game game=new Game(board,gamePanel,gui,generator);
//                KeyGamePanelInput keyGamePanelInput=new KeyGamePanelInput(gamePanel,game);
//                MouseGamePanelInput mouseGamePanelInput=new MouseGamePanelInput(gamePanel,game);
//                UserGUIInput userGUIInput=new UserGUIInput(gui,game);
//
//            }
//        });

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new JFrame();
                JPanel component=new JPanel() {
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
