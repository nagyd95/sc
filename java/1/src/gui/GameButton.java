package gui;

import javax.swing.JButton;


public class GameButton extends JButton{
    private final int row;
    private final int column;

    public GameButton(final int row, final int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }
}

