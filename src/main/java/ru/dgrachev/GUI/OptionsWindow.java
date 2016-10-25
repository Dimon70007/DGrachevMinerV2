package ru.dgrachev.GUI;

//import com.sun.java.swing.ui.OkCancelButtonPanel;
import ru.dgrachev.Main;
import ru.dgrachev.game.Difficult;
import ru.dgrachev.game.GameParameters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class OptionsWindow extends JDialog implements ActionListener,ItemListener{
//    private final ActionListener optionsListener;
//    private JOptionPane optionPane=new JOptionPane();
    private final static Dimension optSize=new Dimension(330,300);
    private final static int SHIFT_OPT_WINDOW=30;
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
        init();
        createRadioButtonPanel();
        createCustomPanel();
        createOkCancelPanel();
        start();
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
                boolean isCustomCorrect=true;
                boolean isNameCorrect=true;

                int tmpX = Integer.valueOf(this.x.getText().trim());
                int tmpY = Integer.valueOf(this.y.getText().trim());
                int tmpBCount = Integer.valueOf(this.bombsCount.getText().trim());
                String pName=GameParameters.playerName;

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

                        isCustomCorrect=true;
                    } else if (tmpX <= 0 || tmpY <= 0 || tmpBCount <= 0) {
                        isCustomCorrect=false;
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
                if (this.playerName.getText().trim().length()==0){
                    isNameCorrect=false;
                    JOptionPane.showMessageDialog(this,"Please enter player's name","Empty player name",JOptionPane.WARNING_MESSAGE);
                }else {
                    pName=this.playerName.getText().trim();
                    isNameCorrect=true;
                }
                if (isCustomCorrect && isNameCorrect){
                    GameParameters.playerName=pName;
                    this.dispose();
                    Main.main(null);
                    gui.dispose();
                }
            }
            if (("CANCEL").equalsIgnoreCase(buttonName)) {
                this.dispose();
                gui.repaint();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() instanceof JRadioButton) {
            if (custom.isSelected()) {
                x.setEnabled(true);
                y.setEnabled(true);
                bombsCount.setEnabled(true);
            } else {
                x.setEnabled(false);
                y.setEnabled(false);
                bombsCount.setEnabled(false);
            }
        }
    }


    private String getSelection(){
        return rButtonGroup.getSelection().getActionCommand();
    }

    private void start() {
        //        addActionListener(this);
        pack();
        setVisible(true);
    }

    private void createOkCancelPanel() {
        okCancelButtonPanel=new JPanel(new FlowLayout());
        createButtons(oK=new JButton("OK"));
        createButtons(cancel=new JButton("CANCEL"));
        add(okCancelButtonPanel,BorderLayout.AFTER_LAST_LINE);
    }

    private void createCustomPanel() {
        customPanel=new JPanel(new FlowLayout());
        createCustomFields(x=new JTextField("CUSTOM CELLS COUNT ON X"),""+GameParameters.currentBoardSize.x,3);
        createCustomFields(y=new JTextField("CUSTOM CELLS COUNT ON Y"), ""+GameParameters.currentBoardSize.y, 3);
        createCustomFields(bombsCount=new JTextField("CUSTOM BOMBS COUNT"),""+GameParameters.currentBombsCount, 5);
        createCustomFields(playerName=new JTextField("PLAYER NAME"), GameParameters.playerName, 15);
        createBorderOnPanel(customPanel,BorderFactory.createEtchedBorder(),"SET CUSTOM PARAMETERS");
        customPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        x.setEnabled(false);
        y.setEnabled(false);
        bombsCount.setEnabled(false);
        add(customPanel,BorderLayout.CENTER);
    }

    private void createRadioButtonPanel() {
        radioButtonPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
        rButtonGroup =new ButtonGroup();
        addRadioButton(easy=new JRadioButton(), Difficult.EASY.toString());
        addRadioButton(medium=new JRadioButton(),Difficult.MEDIUM.toString());
        addRadioButton(hard=new JRadioButton(),Difficult.HARD.toString());
        addRadioButton(custom=new JRadioButton(),Difficult.CUSTOM.toString());
        createBorderOnPanel(radioButtonPanel,BorderFactory.createEtchedBorder(),"CHOICE THE DIFFICULT");
        add(radioButtonPanel,BorderLayout.NORTH);

    }

    private void init() {
        setBounds(gui.getBounds().x+SHIFT_OPT_WINDOW,
                gui.getBounds().y+SHIFT_OPT_WINDOW,
                optSize.width,
                optSize.height);
        setPreferredSize(optSize);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
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

        JLabel label=new JLabel(textField.getText());
        textField.setText(fieldValue);
        textField.setColumns(columnsCount);
        customPanel.add(label);
        customPanel.add(textField);

    }

    private void addRadioButton(JRadioButton rButton, String rButtonName) {
        boolean selected=rButtonName.equalsIgnoreCase(GameParameters.currentDifficult.toString());
        rButton.setText(rButtonName);
        rButton.setSelected(selected);
        rButton.setActionCommand(rButtonName);
        rButton.addItemListener(this);
        rButtonGroup.add(rButton);
        radioButtonPanel.add(rButton);
    }

}
