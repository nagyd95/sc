package logic;



public class GameLogic {
    private int[][] fields;
    private int[][] gamerNumber;
    private int size;
    private int score_1;
    private int score_2;
    private int winer;
    public boolean elsojatekos;

    public void newGame(final int size) {
        this.size = size;
        this.score_1 = 0;
        this.score_2 = 0;
        this.winer = 0;
        this.elsojatekos = true;
        fields = new int[size][size];
        gamerNumber = new int[size][size];
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                fields[row][column] = 0;
                gamerNumber[row][column] = 0;
            }
        }
    }


    public int getFieldValue(final int row, final int column) {
        return fields[row][column];
    }
    public int getGamerNumber(final int row, final int column) {
        return gamerNumber[row][column];
    }

    public void changeFieldValues(final int row, final int column) {

        changeSingleFieldValue(row, column);
        changeSingleFieldValue(row - 1, column);
        changeSingleFieldValue(row + 1, column);
        changeSingleFieldValue(row, column - 1);
        changeSingleFieldValue(row, column + 1);
        if (elsojatekos) {elsojatekos = false;}
        else {elsojatekos=true;}
    }

    private void changeSingleFieldValue(final int row, final int column) {
        try {
            if (fields[row][column]<=3) {
                fields[row][column]++;
                if (fields[row][column]==4 && elsojatekos){
                    score_1++;
                    gamerNumber[row][column] = 1;}
                if (fields[row][column]==4 && !elsojatekos) {
                    score_2++;
                    gamerNumber[row][column] = 2;
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    public boolean isGameEnd() {
        for (int row = 0; row < size; ++row) {
            for (int column = 0; column < size; ++column) {
                if (fields[row][column] != 4) {
                    return false;
                }
            }
        }
        return true;
    }



    public int getSize() {
        return size;
    }
    public int getWiner() {if (score_1>score_2) winer=1;
    else winer=2;
        return winer;}
    public int getScore_1() {
        return score_1;
    }
    public int getScore_2() {
        return score_2;
    }
}
