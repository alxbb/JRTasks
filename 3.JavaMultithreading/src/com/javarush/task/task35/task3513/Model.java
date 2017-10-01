package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
    private Tile[] testTiles;
    private boolean isSaveNeeded  = true;
    Stack<Tile[][]> previousStates = new Stack<>();
    Stack<Integer>  previousScores = new Stack<>();


    public Model() {
        score = 0;
        maxTile = 2;
        resetGameTiles();
//        initTestData();

    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }
    public boolean canMove(){
        boolean retVal = false;
        if(getEmptyTiles().size() > 0){ return true;}

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length-1 ; j++) {
                if(gameTiles[i][j].value == gameTiles[i][j+1].value) return true;
                if(gameTiles[j][i].value == gameTiles[j+1][i].value) return true;
            }
        }

        return retVal;
    }
    private void saveState(Tile[][] toSave){
        isSaveNeeded = false;
        previousStates.push(getTilesCopy(toSave));
        previousScores.push(score);
    }

    private Tile[][] getTilesCopy(Tile[][] toCopy) {
        Tile[][] copy = new Tile[Model.FIELD_WIDTH][Model.FIELD_WIDTH];
        for (int i = 0; i < toCopy.length; i++) {
            for (int j = 0; j < toCopy[i].length; j++) {
                copy[i][j] = new Tile(toCopy[i][j].value);
            }
        }
        return copy;
    }

    public void rollback(){
        if(!previousScores.empty() && !previousStates.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }
    private void printGameTiles() {
        System.out.println("--------");
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                System.out.print(gameTiles[i][j].value + " ");
            }
            System.out.println();
        }
    }

    private void initTestData() {
//        Tile[] test = {new Tile(0), new Tile(2), new Tile(4), new Tile(4)};
//        testTiles = test;
//        Arrays.stream(testTiles).forEach(s -> System.out.print(s + " "));
//        System.out.println();
//        System.out.println(getNextFilled(testTiles, 0));
//        System.out.println(getNextFilled(testTiles, 1));
//        System.out.println(getNextFilled(testTiles, 2));
//        System.out.println(getNextFilled(testTiles, 3));
//        System.out.println(getNextEmpty(testTiles, 0));
//        System.out.println(getNextEmpty(testTiles, 1));
//        System.out.println(getNextEmpty(testTiles, 2));
//        System.out.println(getNextEmpty(testTiles, 3));
////        System.out.println(compressTiles(testTiles));
//        System.out.println(mergeTiles(testTiles));
//
//        Arrays.stream(testTiles).forEach(s -> System.out.print(s + " "));
//        System.out.println();

//        Tile[][] testT = {
//                {new Tile(1), new Tile(2), new Tile(3), new Tile(4)},
//                {new Tile(5), new Tile(6), new Tile(7), new Tile(8)},
//                {new Tile(9), new Tile(10), new Tile(11), new Tile(12)},
//                {new Tile(13), new Tile(14), new Tile(15), new Tile(16)},
//                {new Tile(0), new Tile(4), new Tile(4), new Tile(4)},
//                {new Tile(0), new Tile(0), new Tile(0), new Tile(0)},
//                {new Tile(0), new Tile(4), new Tile(2), new Tile(2)}
//                };
//                gameTiles = testT;
//        printGameTiles();
//        left();
//        rotateMatrix();
//        rotateMatrix();
//        System.out.println(canMove());
//        printGameTiles();
    }

    public void resetGameTiles() {
        if (this.gameTiles == null)
            this.gameTiles = new Tile[Model.FIELD_WIDTH][Model.FIELD_WIDTH];

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles == null || emptyTiles.size() == 0) return;

        emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                Tile current = gameTiles[i][j];
                if (current.isEmpty()) emptyTiles.add(current);
            }
        }
        return emptyTiles;
    }

    private int getNextFilled(Tile[] tiles, int from) {
        int retVal = -1;
        for (int i = from; i < tiles.length; i++) {
            if (!tiles[i].isEmpty()) {
                retVal = i;
                break;
            }
        }
        return retVal;
    }

    private int getNextEmpty(Tile[] tiles, int from) {
        int retVal = -1;
        for (int i = from; i < tiles.length; i++) {
            if (tiles[i].isEmpty()) {
                retVal = i;
                break;
            }
        }
        return retVal;
    }

    private void swapTiles(Tile[] tiles, int t1, int t2) {
        Tile tmp = tiles[t1];
        tiles[t1] = tiles[t2];
        tiles[t2] = tmp;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean retVal = false;
        if (getNextFilled(tiles, 0) == -1 || getNextEmpty(tiles, 0) == -1) return retVal;
        int i = 0;
        while (true) {
            int empty = getNextEmpty(tiles, i);
            int fill = getNextFilled(tiles, i);
            if (fill == -1) break;
            if (empty < fill) {
                swapTiles(tiles, empty, fill);
                retVal = true;
            } else {
                i++;
            }
        }
        return retVal;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean retVal = false;
        if (getNextFilled(tiles, 0) == -1) return retVal;
        Tile t1;
        Tile t2;

        if (compressTiles(tiles)) retVal = true;
        for (int i = 0; i < tiles.length - 1; i++) {
            t1 = tiles[i];
            t2 = tiles[i + 1];
            if (t1.value == t2.value && !t1.isEmpty() && !t2.isEmpty()) {
                t1.value += t2.value;
                t2.value = 0;
                score += t1.value;
                if (t1.value > maxTile) maxTile = t1.value;
                retVal = true;
                if (compressTiles(tiles)) retVal = true;
            }
        }
        return retVal;
    }

    void left() {
        if(isSaveNeeded) saveState(gameTiles);
        boolean isCompress = false;
        boolean isMerge = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if (compressTiles(gameTiles[i])) isCompress = true;
            if (mergeTiles(gameTiles[i])) isMerge = true;
        }

        if (isCompress || isMerge) addTile();
        isSaveNeeded = true;
    }
    void right() {
        saveState(gameTiles);
        rotateMatrix();
        rotateMatrix();
        left();
        rotateMatrix();
        rotateMatrix();
    }
    void up() {
        saveState(gameTiles);
        rotateMatrix();
        left();
        rotateMatrix();
        rotateMatrix();
        rotateMatrix();
    }
    void down() {
        saveState(gameTiles);
        rotateMatrix();
        rotateMatrix();
        rotateMatrix();
        left();
        rotateMatrix();
    }
    private void rotateMatrix(){
        int m = Model.FIELD_WIDTH;
        Tile tmp;

        for (int k=0; k<m/2; k++) {// border -> center
            for (int j=k; j<m-1-k; j++) {// left -> right
                // меняем местами 4 угла
                tmp                     = gameTiles[k][j];
                gameTiles[k][j]         = gameTiles[j][m-1-k];
                gameTiles[j][m-1-k]     = gameTiles[m-1-k][m-1-j];
                gameTiles[m-1-k][m-1-j] = gameTiles[m-1-j][k];
                gameTiles[m-1-j][k]     = tmp;
            }
        }
    }
    public void randomMove(){
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n){
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }
    public boolean hasBoardChanged(){
        return (getTilesWeight(gameTiles) - getTilesWeight(previousStates.peek())) != 0;
    }
    public MoveEfficiency getMoveEfficiency(Move move){
        MoveEfficiency after;
        move.move();
        if(!hasBoardChanged()) {
            after = new MoveEfficiency(-1, 0, move);
        } else {
            after = new MoveEfficiency(getEmptyTiles().size(), score, move);
        }
        rollback();
        return after;
    }
    private int getTilesWeight(Tile[][] tiles){
        int sum = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                sum += tiles[i][j].value;
            }
        }
        return sum;
    }
    public void autoMove(){
        PriorityQueue queue = new PriorityQueue(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));

        MoveEfficiency moveEfficiency = (MoveEfficiency) queue.poll();
        moveEfficiency.getMove().move();
    }
}