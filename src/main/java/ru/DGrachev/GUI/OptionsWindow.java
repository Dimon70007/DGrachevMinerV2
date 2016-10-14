package ru.DGrachev.GUI;

import ru.DGrachev.Main;
import ru.DGrachev.game.GameParameters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public class OptionsWindow extends JDialog implements ItemListener, ActionListener {

    private CheckboxGroup checkboxGroup;
    private Checkbox easy,medium,hard,custom;
    private JLabel checkbGroupName,inputX,inputY,inputBombsCount;
    private JTextField x,y,bombsCount;
    private JButton oK,cancel;

    public OptionsWindow(JFrame frame, String name) {
        super(frame,name,true);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(400,300);
        createCheckbGroup();
        createCustomFields();
        createButtons();
        addItemListeners();
        addActionListeners();
        setVisible(true);

    }

    private void createButtons() {
        oK=new JButton("OK");
        cancel=new JButton("CANCEL");
        oK.setLayout(new FlowLayout(FlowLayout.TRAILING));
        cancel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        oK.setVisible(true);
        cancel.setVisible(true);
    }

    private void addItemListeners() {

        easy.addItemListener(this);
        medium.addItemListener(this);
        hard.addItemListener(this);
        custom.addItemListener(this);
    }

    private void createCustomFields() {
        inputX=new JLabel("CUSTOM CELLS COUNT ON WIDTH",JLabel.LEFT);
        x=new JTextField(2);
        x.setLayout(new FlowLayout(FlowLayout.RIGHT));
        inputY=new JLabel("CUSTOM CELLS COUNT ON HEIGHT",JLabel.LEFT);
        y=new JTextField(2);
        y.setLayout(new FlowLayout(FlowLayout.RIGHT));
        inputBombsCount=new JLabel("CUSTOM BOMBS COUNT",JLabel.LEFT);
        bombsCount=new JTextField(4);
        bombsCount.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(inputX);
        add(x);
        add(inputY);
        add(y);
        add(inputBombsCount);
        add(bombsCount);
    }

    private void createCheckbGroup() {
        checkboxGroup=new CheckboxGroup();
        checkbGroupName=new JLabel("SET DIFFICULT",JLabel.LEADING);
        easy=new Checkbox("EASY", checkboxGroup,true);
        medium=new Checkbox("MEDIUM", checkboxGroup,false);
        hard=new Checkbox("HARD", checkboxGroup,false);
        custom=new Checkbox("CUSTOM", checkboxGroup,false);

        add(checkbGroupName);
        add(easy);
        add(medium);
        add(hard);
        add(custom);
    }

    public void addActionListeners() {
        x.addActionListener(this);
        y.addActionListener(this);
        bombsCount.addActionListener(this);
        oK.addActionListener(this);
        cancel.addActionListener(this);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strE=e.getSource().toString().toUpperCase();
        if(("OK").equals(strE)){
            int tmpX=Integer.valueOf(x.getText());
            int tmpY=Integer.valueOf(y.getText());
            int tmpBCount=Integer.valueOf(bombsCount.getText());

            if(checkboxGroup.getSelectedCheckbox()==custom &&
                    tmpX>4 &&
                    tmpY>4 &&
                    tmpBCount>0 && tmpBCount<(tmpX*tmpY)){
                GameParameters.customBombsCount=tmpBCount;
                GameParameters.customBoardSize.x=tmpX;
                GameParameters.customBoardSize.y=tmpY;
                Main.main(null);
                dispose();
            }
            dispose();
        }
        if (("CANCEL").equals(strE)){
            dispose();
        }
        repaint();
    }
}
