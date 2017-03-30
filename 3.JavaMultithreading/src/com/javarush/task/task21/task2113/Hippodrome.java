package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    static Hippodrome game;

    private List<Horse> horses;

    public Hippodrome(List<Horse> horses) { this.horses = horses; }

    public List<Horse> getHorses() { return horses; }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(100);
        }
    }

    public void move(){
        for( Horse current : horses ) current.move();
    }
    public void print(){
        for( Horse current : horses ) current.print();
        System.out.printf("%n%n%n%n%n%n%n%n%n%n");
    }

    public Horse getWinner(){
        double max = 0;
        Horse winner = null;
        for (Horse h : horses){
            double dist = h.getDistance();
            if (dist > max){
                max = dist;
                winner = h;
            }
        }
        return winner;
    }

    public void printWinner(){
        System.out.printf("Winner is %s!", getWinner().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        game = new Hippodrome(new ArrayList<>());
        List<Horse> h = game.getHorses();
        h.add(new Horse("Top-Top", 3, 0));
        h.add(new Horse("Tip-Top", 3, 0));
        h.add(new Horse("Top-Tip", 3, 0));
        game.run();
        game.printWinner();
    }
}
