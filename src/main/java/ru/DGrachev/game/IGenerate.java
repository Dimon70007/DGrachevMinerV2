package ru.DGrachev.game;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public interface IGenerate {

    void generateBoard(Board board);

     void generateMines(Board board, Point userPoint, ICell bombType);

// жуткая штука - интерфейсы - не разрешают создавать статик методы...
    //оно и понятно ))) ведь статик методы - методы уровня класса, а не инстанса

    //а еще они не имеют конструктора
    //а еще они на 20 процентов медленнее абстрактных классов
    // но я их все равно люблю)))

}
