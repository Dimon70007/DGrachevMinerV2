package ru.dgrachev.game;

/**
 * Created by OTBA}|{HbIu` on 22.10.16.
 */
public class CellState implements ICellState {
    private boolean isOpen;
    private int flag;
    private final ICell cell;

    public CellState(final ICell cell) {
        this.cell = cell;
        this.isOpen = false;
        this.flag = 0;
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
                flag=1;
                return Cell.CLOSED;
            case 1:
                flag=2;
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
    public ICell getCell() {
        return cell;
    }
}
