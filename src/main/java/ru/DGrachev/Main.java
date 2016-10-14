package ru.DGrachev;

import ru.DGrachev.GUI.GUI;
import ru.DGrachev.GUI.GamePanel;
import ru.DGrachev.game.Board;
import ru.DGrachev.game.Game;
import ru.DGrachev.game.Generator;
import ru.DGrachev.userinput.KeyGamePanelInput;
import ru.DGrachev.userinput.MouseGamePanelInput;
import ru.DGrachev.userinput.UserGUIInput;

import javax.swing.*;

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

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                GamePanel gamePanel=new GamePanel();
                GUI gui=new GUI(gamePanel);
                Generator generator=new Generator();
                Board board=new Board();
                Game game=new Game(board,gamePanel,gui,generator);
                KeyGamePanelInput keyGamePanelInput=new KeyGamePanelInput(gamePanel,game);
                MouseGamePanelInput mouseGamePanelInput=new MouseGamePanelInput(gamePanel,game);
                UserGUIInput userGUIInput=new UserGUIInput(gui,game);

            }
        });
    }
}
