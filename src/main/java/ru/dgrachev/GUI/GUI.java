package ru.dgrachev.GUI;

import ru.dgrachev.game.Game;
import ru.dgrachev.game.ICell;
import ru.dgrachev.userinput.KeyGamePanelInput;
import ru.dgrachev.userinput.MouseGamePanelInput;
import ru.dgrachev.userinput.UserGUIInput;

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

    JLabel currentGameTime;



    public GUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void createGamePanel() {
        add(gamePanel,BorderLayout.CENTER);
        gamePanel.setEnabled(true);
    }

    protected void createGameState() {
        gameState=new JPanel();
        currentGameTime=new JLabel("TIME: "+"00:00:00"+"hh:mm:ss");
        gameState.add(currentGameTime);

        add(gameState,BorderLayout.SOUTH);
    }


    protected void createMenuBar() {
        gameMenuBar=new JMenuBar();
        JMenu gameMenu=new JMenu("MENU");
        gameMenu.add("NEW GAME");
        gameMenu.add("OPTIONS");
        gameMenu.add("STATISTIKS");
//        gameMenu.addSeparator();
        gameMenu.add("EXIT");
        gameMenuBar.add(gameMenu);

        JMenu helpMenu=new JMenu("HELP");
        helpMenu.add("HELP");
//        helpMenu.addSeparator();
        helpMenu.add("ABOUT");
        gameMenuBar.add(helpMenu);

        setJMenuBar(gameMenuBar);

    }

    @Override
    public void congratulations() {
        JLabel jl=new JLabel("Congratulations");
        JOptionPane.showMessageDialog(this,jl,jl.getText(),JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void gameOver() {
        JLabel jl=new JLabel("Game over.");
        JOptionPane.showMessageDialog(this,jl,jl.getText(),JOptionPane.INFORMATION_MESSAGE);
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

        if(gameState!=null){
            currentGameTime.setText("TIME: "+time+" hh:mm:ss");
            gameState.repaint();

        }
    }

    @Override
    public void drawBoard(Map<Point, ICell> cells) {
        gamePanel.drawBoard(cells);
    }

    @Override
    public Dimension getPanelSize() {
        return gamePanel.getPanelSize();
    }

    public void init(){
        createMenuBar();
        createGamePanel();
        createGameState();
        pack();
        setResizable(false);
        setVisible(true);

    }


    public void begin() {
        Game game=new Game(this);
        MouseGamePanelInput mInput=new MouseGamePanelInput(gamePanel,game);
        KeyGamePanelInput kInput=new KeyGamePanelInput(gamePanel,game);
        UserGUIInput uGInput=new UserGUIInput(this,game);



    }

}
