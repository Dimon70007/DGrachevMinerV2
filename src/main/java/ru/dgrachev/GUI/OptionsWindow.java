package ru.dgrachev.GUI;

//import com.sun.java.swing.ui.OkCancelButtonPanel;
import ru.dgrachev.Main;
import ru.dgrachev.game.Difficult;
import ru.dgrachev.game.Exceptions.WrongGameParameterException;
import ru.dgrachev.game.GameParameters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static ru.dgrachev.game.GameParameters.CELL_SIZE;

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
                String pName="";

                if (Difficult.EASY.toString().equalsIgnoreCase(
                        this.getSelection())) {
                    GameParameters.currentDifficult = Difficult.EASY;
                    GameParameters.currentBoardSize = GameParameters.EASY_BOARD_SIZE;
                    GameParameters.currentBombsCount = GameParameters.EASY_BOMB_COUNT;

                }

                if (Difficult.MEDIUM.toString().equalsIgnoreCase(
                        this.getSelection())) {
                    GameParameters.currentDifficult = Difficult.MEDIUM;
                    GameParameters.currentBoardSize = GameParameters.MEDIUM_BOARD_SIZE;
                    GameParameters.currentBombsCount = GameParameters.MEDIUM_BOMB_COUNT;

                }

                if (Difficult.HARD.toString().equalsIgnoreCase(
                        this.getSelection())) {
                    GameParameters.currentDifficult = Difficult.HARD;
                    GameParameters.currentBoardSize = GameParameters.HARD_BOARD_SIZE;
                    GameParameters.currentBombsCount = GameParameters.HARD_BOMB_COUNT;

                }

                if (Difficult.CUSTOM.toString().equalsIgnoreCase(
                        this.getSelection())) {
                    Dimension screenSize=gui.getToolkit().getScreenSize();
                    try {
                        GameParameters.currentBoardSize.x=handleInput(tmpX,screenSize.width/CELL_SIZE,"CUSTOM CELLS ON X ");
                        GameParameters.currentBoardSize.y=handleInput(tmpY,screenSize.height/CELL_SIZE,"CUSTOM CELLS ON Y ");
                        GameParameters.currentBombsCount=handleInput(tmpBCount,tmpX*tmpY,"CUSTOM BOMBS COUNT ");
                        isCustomCorrect=true;
                        GameParameters.currentDifficult = Difficult.CUSTOM;
                    }catch (WrongGameParameterException ex){
                        isCustomCorrect=false;
                    }
                }

                if (this.playerName.getText().trim().length()==0){
                    isNameCorrect=false;
                    showWarningMessage("Please enter player's name");
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
    private int handleInput(int input, int parameter, String s) throws WrongGameParameterException {
        if (input>=2 && input<parameter) {
            return input;
        }else {
            showWarningMessage(String.format(s + "MUST BE IN 2 - %d", parameter-1));
            throw new WrongGameParameterException();
        }
    }

    private void showWarningMessage(String message){
        JOptionPane.showMessageDialog(this,
                message,
                "Wrong Parameters", JOptionPane.WARNING_MESSAGE);
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
        playerName.setEnabled(true);
        createBorderOnPanel(customPanel,BorderFactory.createEtchedBorder(),"SET CUSTOM PARAMETERS");
        customPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        textField.setEnabled(custom.isSelected());
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
