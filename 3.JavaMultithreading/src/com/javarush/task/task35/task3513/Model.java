package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Model {
    private final static int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;
//    private Tile[] testTiles = {new Tile(4), new Tile(4), new Tile(2), new Tile(2)};
    private Tile[] testTiles = {new Tile(2), new Tile(0), new Tile(0), new Tile(0)};

    public Model() {
        score = 0;
        maxTile = 2;
        resetGameTiles();

        Arrays.stream(testTiles).forEach(s -> System.out.print(s + " "));
        System.out.println();

        System.out.println(mergeTiles(testTiles));

        Arrays.stream(testTiles).forEach(s -> System.out.print(s + " "));
        System.out.println();

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

    private void addTile(){
        List<Tile> emptyTiles = getEmptyTiles();
        if(emptyTiles == null || emptyTiles.size()==0) return;

        emptyTiles.get((int) (Math.random()*emptyTiles.size())).value = (Math.random() < 0.9 ? 2 : 4);
    }

    private List<Tile> getEmptyTiles(){
        List<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 0; j < gameTiles[i].length; j++) {
                Tile current = gameTiles[i][j];
                if(current.isEmpty()) emptyTiles.add(current);
            }
        }
        return emptyTiles;
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean retval = false;
        Tile tmp = null;
        if (Stream.of(tiles)
                .map(t -> t.value)
                .reduce(0, Integer::sum) == 0) return retval;

        int empty = -1;
        int full  = -1;

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if(tiles[j].isEmpty()) empty = j;
                else full = j;
                if(empty >= 0 && full >= 0 && full > empty){
                    tmp = tiles[empty];
                    tiles[empty] = tiles[full];
                    tiles[full] = tmp;
                    retval = true;
                }
            }
        }
        return retval;
    }


    private boolean mergeTiles(Tile[] tiles){
        boolean retval = false;
        if (Stream.of(tiles)
                .map(t -> t.value)
                .reduce(0, Integer::sum) == 0) return retval;

        Tile t;
        Tile t1;

        if(compressTiles(tiles)) retval = true;
        for (int i = 0; i < tiles.length-1; i++) {
            t  = tiles[i];
            t1 = tiles[i+1];
            if(t.value == t1.value && !t.isEmpty() && !t1.isEmpty()){
                t.value += t1.value;
                t1.value = 0;
                score += t.value;
                if(t.value > maxTile) maxTile = t.value;
                retval = true;
                if(compressTiles(tiles)) retval = true;
            }
        }
        return retval;
    }

    void left(){
        boolean isCompress = false;
        boolean isMerge    = false;
        for (int i = 0; i < gameTiles.length; i++) {
            if(compressTiles(gameTiles[i])) isCompress = true;
            if(mergeTiles   (gameTiles[i])) isMerge    = true;
        }
        if(isCompress && isMerge)addTile();
    }
}
