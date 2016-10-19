package ru.dgrachev.GUI;

import ru.dgrachev.game.ICell;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Map;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IGamePanel {

    void drawBoard(Map<Point,ICell> cells);

    Dimension getPanelSize();

    void addMouseListener(MouseListener listener);

    void addKeyListener(KeyListener listener);





}
