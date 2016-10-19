package ru.dgrachev.GUI;

import java.awt.event.ActionListener;

/**
 * Created by OTBA}|{HbIu` on 12.10.16.
 */
public interface IGUI extends IGamePanel{

    void congratulations();

    void gameOver();

    void addActionListener(ActionListener listener);

    void updateTime(String time);



}
