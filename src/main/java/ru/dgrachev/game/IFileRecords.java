package ru.dgrachev.game;

import java.util.Set;

/**
 * Created by OTBA}|{HbIu` on 20.10.16.
 */
public interface IFileRecords {
    Set<Player> read();

    void write(Player player);
}
