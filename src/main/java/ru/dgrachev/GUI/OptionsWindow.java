package ru.dgrachev.GUI;

//import com.sun.java.swing.ui.OkCancelButtonPanel;
import ru.dgrachev.game.Difficult;
import ru.dgrachev.game.GameParameters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class OptionsWindow extends JDialog implements ActionListener{
//    private final ActionListener optionsListener;
//    private JOptionPane optionPane=new JOptionPane();
    private GUI gui;
    private ButtonGroup rButtonGroup;
    private JRadioButton easy,medium,hard,custom;
    private Border buttonsBorder;
    private JTextField x, y, bombsCount, playerName;
    private JButton oK,cancel;
    private JPanel radioButtonPanel,customPanel,okCancelButtonPanel;
//    private OkCancelButtonPanel okCancelButtonPanel;

    public OptionsWindow(GUI gui) {
        super(gui,"OPTIONS",true);
        this.gui=gui;
        setPreferredSize(new Dimension(500,400));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        radioButtonPanel=new JPanel();
        rButtonGroup =new ButtonGroup();
        addRadioButton(easy=new JRadioButton(), Difficult.EASY.toString());
        addRadioButton(medium=new JRadioButton(),Difficult.MEDIUM.toString());
        addRadioButton(hard=new JRadioButton(),Difficult.HARD.toString());
        addRadioButton(custom=new JRadioButton(),Difficult.CUSTOM.toString());
        createBorderOnPanel(radioButtonPanel,BorderFactory.createEtchedBorder(),"CHOICE THE DIFFICULT");
        add(radioButtonPanel,BorderLayout.CENTER);

        customPanel=new JPanel();
        createCustomFields(x=new JTextField("CUSTOM CELLS COUNT ON X"),""+GameParameters.currentBoardSize.x,3);
        createCustomFields(y=new JTextField("CUSTOM CELLS COUNT ON Y"), ""+GameParameters.currentBoardSize.y, 3);
        createCustomFields(bombsCount=new JTextField("CUSTOM BOMBS COUNT"),""+GameParameters.currentBombsCount, 5);
        createCustomFields(playerName=new JTextField("PLAYER NAME"), GameParameters.playerName, 15);
        add(customPanel,BorderLayout.SOUTH);

        okCancelButtonPanel=new JPanel();
        createButtons(oK=new JButton("OK"));
        createButtons(cancel=new JButton("CANCEL"));
        add(okCancelButtonPanel,BorderLayout.AFTER_LAST_LINE);
//        addActionListener(this);
        pack();
        setVisible(true);
    }

    private void createBorderOnPanel(JPanel panel, Border border, String title) {
        if(title!=null)
            panel.setBorder(BorderFactory.createTitledBorder(border,title));
        else
            panel.setBorder(border);
    }

    private void createButtons(JButton button) {
        button.setActionCommand(button.getText());
        button.addActionListener(this);
        okCancelButtonPanel.add(button);
    }



    private void createCustomFields(JTextField textField, String fieldValue, int columnsCount) {
//        textField=new JTextField(fieldValue,columnsCount);
        textField.setText(fieldValue);
        textField.setColumns(columnsCount);
//        JPanel tmp=new JPanel();
        customPanel.add(new JLabel(textField.getText()));
        customPanel.add(textField);

    }

    private void addRadioButton(JRadioButton rButton, String rButtonName) {
        boolean selected=rButtonName.equalsIgnoreCase(GameParameters.currentDifficult.toString());
        rButton.setText(rButtonName);
        rButton.setSelected(selected);
        rButton.setActionCommand(rButtonName);
        rButtonGroup.add(rButton);
        radioButtonPanel.add(rButton);
    }

    public String getSelection(){
        return rButtonGroup.getSelection().getActionCommand();
    }

//    public void addActionListener(ActionListener listener) {
//        oK.addActionListener(listener);
//        cancel.addActionListener(listener);
////        okCancelButtonPanel =new OkCancelButtonPanel(listener);
////        x.addActionListener(listener);
////        y.addActionListener(listener);
////        bombsCount.addActionListener(listener);
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
        String buttonName=((JButton)e.getSource()).getText();
            if (("OK").equalsIgnoreCase(buttonName)) {
                int tmpX = Integer.valueOf(this.x.getText().trim());
                int tmpY = Integer.valueOf(this.y.getText().trim());
                int tmpBCount = Integer.valueOf(this.bombsCount.getText().trim());
                String pName=this.playerName.getText().trim();

                if ("EASY".equalsIgnoreCase(
                        this.getSelection())) {
                    GameParameters.currentDifficult = Difficult.EASY;
                    GameParameters.currentBombsCount = GameParameters.EASY_BOMB_COUNT;
                    GameParameters.currentBoardSize.x = GameParameters.EASY_BOARD_SIZE.x;
                    GameParameters.currentBoardSize.y = GameParameters.EASY_BOARD_SIZE.y;

                }

                if ("MEDIUM".equalsIgnoreCase(
                        this.getSelection())) {
                    GameParameters.currentDifficult = Difficult.MEDIUM;
                    GameParameters.currentBombsCount = GameParameters.MEDIUM_BOMB_COUNT;
                    GameParameters.currentBoardSize.x = GameParameters.MEDIUM_BOARD_SIZE.x;
                    GameParameters.currentBoardSize.y = GameParameters.MEDIUM_BOARD_SIZE.y;

                }

                if ("HARD".equalsIgnoreCase(
                        this.getSelection())) {
                    GameParameters.currentDifficult = Difficult.HARD;
                    GameParameters.currentBombsCount = GameParameters.HARD_BOMB_COUNT;
                    GameParameters.currentBoardSize.x = GameParameters.HARD_BOARD_SIZE.x;
                    GameParameters.currentBoardSize.y = GameParameters.HARD_BOARD_SIZE.y;

                }

                if ("CUSTOM".equalsIgnoreCase(
                        this.getSelection())) {
                    if (tmpX > 4 &&
                            tmpY > 4 &&
                            tmpBCount > 0 && tmpBCount < (tmpX * tmpY)) {
                        GameParameters.currentDifficult = Difficult.CUSTOM;
                        GameParameters.currentBombsCount = tmpBCount;
                        GameParameters.currentBoardSize.x = tmpX;
                        GameParameters.currentBoardSize.y = tmpY;
                    } else if (tmpX == 0 || tmpY == 0 || tmpBCount == 0) {
                        String message="You have been chose CUSTOM currentDifficult,\n" +
                                "that's why you should enter CELLS COUNTS\n" +
                                "bigger then 4x4 and CUSTOM BOMBS COUNT= \n" +
                                "bigger then zero and less then multiple\n" +
                                "of (CELLS COUNT on X and CELLS COUNT ON Y)";
                        JOptionPane.showMessageDialog(this,
                                message,
                                "Wrong Parameters", JOptionPane.WARNING_MESSAGE);
                    }
                }
                GameParameters.playerName=pName;
                this.dispose();
                gui.begin();
            }
            if (("CANCEL").equalsIgnoreCase(buttonName)) {
                this.dispose();
            }
            gui.repaint();
        }





    }
}
