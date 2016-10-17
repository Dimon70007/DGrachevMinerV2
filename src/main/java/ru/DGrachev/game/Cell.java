package ru.DGrachev.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by OTBA}|{HbIu` on 10.10.16.
 */
public enum Cell implements ICell {
    EMPTY("0"),ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"), BOMB("BOMB"), FLAG("FLAG"),QUESTION("QUESTION"),CLOSED("CLOSED"),EXPLOSION("EXPLOSION");


    private final static ICell[] CELLS=Cell.class.getEnumConstants();

    private String value;
    private URL url;
    private boolean isOpen=false;
    private int flag;
    private Image image;

    Cell(String s) {
        this.value=s;

        try {
            this.url=Cell.class.getResource("CLOSED.png");
            this.image = ImageIO.read(url);

        } catch (IOException e) {
//            e.printStackTrace();
        }
        this.flag=0;

    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public int getNumber() {
        int outOfNumericCell=9;
        for(int i=0;i<outOfNumericCell;i++){
            if(CELLS[i].getValue().equals(value))//тупо берем стрингу у текущей ячейки и по ней вытаскиваем объект
                return i;//метод конечно опасный, но в данном случае стринга всегда равна значению объекта)))
        }
        return -1;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public ICell nextCell() {
        int nextNumber=getNumber()+1;
        if(nextNumber==0)
            return null;
        return CELLS[nextNumber];
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public ICell getFlag() {
        switch (flag){
            case 0:
                return Cell.CLOSED;
            case 1:
                return Cell.FLAG;
            case 2:
                return Cell.QUESTION;
        }
        return null;
    }

    @Override
    public ICell setFlag() {
        switch (flag){
            case 0:
                flag++;
                return Cell.CLOSED;
            case 1:
                flag++;
                return Cell.FLAG;
            case 2:
                flag=0;
                return Cell.QUESTION;
        }
        return null;
    }

    @Override
    public void setOpened() {
        isOpen=true;
    }

    @Override
    public Image  getImage() {
            return image;
    }


}
