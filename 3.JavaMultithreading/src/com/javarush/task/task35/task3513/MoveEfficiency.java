package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency>{
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }


    @Override
    public int compareTo(MoveEfficiency o) {
//        if(o == null) return 0;
        int retVal = 0;
        retVal = Integer.compare(this.numberOfEmptyTiles, o.numberOfEmptyTiles);
        if(retVal == 0)
            retVal = Integer.compare(this.score, o.score);
        return retVal;
    }
}
