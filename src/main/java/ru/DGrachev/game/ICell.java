package ru.DGrachev.game;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface ICell {

    String getValue();

    Image getImage();

    int getNumber();

//используется для расстановки цифр вокруг бомбы
    ICell nextCell();

    boolean isOpen();

    ICell getFlag();

    ICell setFlag();

    void setOpened();

}
