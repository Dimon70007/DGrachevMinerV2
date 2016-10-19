package ru.dgrachev.GUI;

//import com.sun.java.swing.ui.OkCancelButtonPanel;
import ru.dgrachev.game.Difficult;
import ru.dgrachev.game.GameParameters;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class OptionsWindow extends JDialog {
    private final ActionListener optionsListener;
//    private JOptionPane optionPane=new JOptionPane();
//    private GUI gui;
    private ButtonGroup rButtonGroup;
    private JRadioButton easy,medium,hard,custom;
    private Border buttonsBorder;
    public JTextField x;
    public JTextField y;
    public JTextField bombsCount;
    private JButton oK,cancel;
    private JPanel radioButtonPanel,customPanel,okCancelButtonPanel;
//    private OkCancelButtonPanel okCancelButtonPanel;

    public OptionsWindow(GUI gui,ActionListener listener) {
        super(gui,"OPTIONS",true);
        this.optionsListener=listener;
        setPreferredSize(new Dimension(500,400));

        radioButtonPanel=new JPanel();
        rButtonGroup =new ButtonGroup();
        addRadioButton(easy, Difficult.EASY.toString());
        addRadioButton(medium,Difficult.MEDIUM.toString());
        addRadioButton(hard,Difficult.HARD.toString());
        addRadioButton(custom,Difficult.CUSTOM.toString());
        createBorderOnPanel(radioButtonPanel,BorderFactory.createEtchedBorder(),"CHOICE THE DIFFICULT");
        add(radioButtonPanel,BorderLayout.CENTER);

        customPanel=new JPanel();
        createCustomFields(x,GameParameters.currentBoardSize.x,"CUSTOM CELLS COUNT ON X",3);
        createCustomFields(y, GameParameters.currentBoardSize.y, "CUSTOM CELLS COUNT ON Y",3);
        createCustomFields(bombsCount, GameParameters.currentBombsCount, "CUSTOM BOMBS COUNT",5);
        add(customPanel,BorderLayout.SOUTH);

        addActionListeners(listener);
        createButtons(oK,"OK");
        createButtons(cancel,"CANCEL");
        add(okCancelButtonPanel,BorderLayout.AFTER_LAST_LINE);

        pack();
        setVisible(true);
    }

    private void createBorderOnPanel(JPanel panel, Border border, String title) {
        if(title!=null)
            panel.setBorder(BorderFactory.createTitledBorder(border,title));
        else
            panel.setBorder(border);
    }

    private void createButtons(JButton button, String buttonName) {
        button=new JButton(buttonName);
        okCancelButtonPanel.add(button);
    }



    private void createCustomFields(JTextField textField, int fieldValue, String labelName, int columnsCount) {
        textField=new JTextField((""+fieldValue),columnsCount);
        customPanel.add(new JLabel(labelName),BorderLayout.EAST);
        customPanel.add(textField,BorderLayout.WEST);
    }

    private void addRadioButton(JRadioButton rButton, String rButtonName) {
        rButton=new JRadioButton(rButtonName,
                rButtonName.equalsIgnoreCase(GameParameters.currentDifficult.toString()));
        rButton.setActionCommand(rButtonName);
        /*{
            String actionCommand;
            @Override
            public void setActionCommand(String actionCommand) {
                super.setActionCommand(actionCommand);
                actionCommand=rButtonName;
            }
        };*/
        rButtonGroup.add(rButton);
        radioButtonPanel.add(rButton);
    }
    public String getSelection(){
        return rButtonGroup.getSelection().getActionCommand();
    }

    public void addActionListeners(ActionListener listener) {
        oK.addActionListener(listener);
        cancel.addActionListener(listener);
//        okCancelButtonPanel =new OkCancelButtonPanel(listener);
//        x.addActionListener(listener);
//        y.addActionListener(listener);
//        bombsCount.addActionListener(listener);
    }


}
