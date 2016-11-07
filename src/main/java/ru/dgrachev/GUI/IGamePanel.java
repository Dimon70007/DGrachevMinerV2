package ru.dgrachev.GUI;

import ru.dgrachev.game.Board;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IGamePanel {

    void drawBoard();

    void drawBoard(Board board);

    Board getBoard();

    Dimension getPanelSize();

    void addMouseListener(MouseListener listener);

    void addKeyListener(KeyListener listener);





}
