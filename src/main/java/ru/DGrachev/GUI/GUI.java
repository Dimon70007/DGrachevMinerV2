package ru.DGrachev.GUI;

import ru.DGrachev.game.ICell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class GUI extends JFrame implements IGUI{

    JMenuBar gameMenuBar;

    GamePanel gamePanel;

    JPanel gameState;

    String currentGameTime="00:00:00";


    public GUI(GamePanel gamePanel) {
        createMenuBar();
        this.gamePanel = gamePanel;
        gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        gamePanel.setVisible(true);
        //// TODO: 14.10.16
//        gamePanel.setUI();

        createGameState();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER));
//        setPreferredSize(new Dimension(800,600));
//        pack();
        setVisible(true);
    }

    protected void createGameState() {
        gameState=new JPanel(new FlowLayout(FlowLayout.TRAILING));
        gameState.add(new JLabel("YOUR TIME: "));
        gameState.add(new JLabel(currentGameTime));
        gameState.add(new JLabel("hh:mm:ss"));
        gameState.setVisible(true);

    }


    protected void createMenuBar() {
        gameMenuBar=new JMenuBar();
        JMenu gameMenu=new JMenu("MENU");
        gameMenu.add("NEW GAME");
        gameMenu.add("OPTIONS");
        gameMenu.add("RECORDS");
        gameMenu.add("-");
        gameMenu.add("EXIT");
        gameMenuBar.add(gameMenu);

        JMenu helpMenu=new JMenu("HELP");
        helpMenu.add("HELP");
        helpMenu.add("ABOUT");
        gameMenuBar.add(helpMenu);

        setJMenuBar(gameMenuBar);

    }


    @Override
    public void congratulations() {

    }

    @Override
    public void gameOver() {

    }

    public void addActionListener(ActionListener listener) {

        for (int i = 0; i < gameMenuBar.getMenuCount(); i++) {
            for (int j =0; j < gameMenuBar.getMenu(i).getItemCount(); j++) {
                gameMenuBar.getMenu(i).getItem(j).addActionListener(listener);
            }
        }
    }

    @Override
    public void updateTime(String time) {
        currentGameTime=time;
        gameState.repaint();
    }

    @Override
    public void drawBoard(Map<Point, ICell> cells) {
        gamePanel.drawBoard(cells);
    }

    @Override
    public Dimension getPanelSize() {
        return gamePanel.getPanelSize();
    }
}
