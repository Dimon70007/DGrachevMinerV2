package ru.dgrachev.game;

import java.awt.*;
import java.io.IOException;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface ICell {

    String getValue();

    Image getImage() throws IOException;

    int getNumber();

//используется для расстановки цифр вокруг бомбы
    ICell nextCell();

}
