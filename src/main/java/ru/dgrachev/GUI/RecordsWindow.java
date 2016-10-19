package ru.dgrachev.GUI;

import javax.swing.*;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by otvazhniy on 18.10.16.
 */
public class RecordsWindow {
    FileInputStream fin = null;
    FileChannel fChan = null;
    ByteBuffer mBuf;
    int count;
    public RecordsWindow(GUI gui) {

        JOptionPane.showMessageDialog(gui,"","records",JOptionPane.INFORMATION_MESSAGE);
    }
}
