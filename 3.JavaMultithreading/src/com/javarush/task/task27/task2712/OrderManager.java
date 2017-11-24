//package com.javarush.task.task27.task2712;
//
//import com.javarush.task.task27.task2712.kitchen.Cook;
//import com.javarush.task.task27.task2712.kitchen.Order;
//import com.javarush.task.task27.task2712.statistic.StatisticManager;
//
//import java.util.Observable;
//import java.util.Observer;
//import java.util.concurrent.LinkedBlockingQueue;
//
//public class OrderManager implements Observer {
//    private Thread thread;
//
//    public OrderManager() {
//        StatisticManager manager = StatisticManager.getInstance();
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    if(!orderQueue.isEmpty()){
//                        for(Cook cook : manager.getCooks()){
//                            if(!cook.isBusy()) {
//                                cook.startCookingOrder(orderQueue.poll());
//                                break;
//                            }
//                        }
//                    }
//                    try {
////                        System.out.println("runner");
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread = new Thread(r);
//        thread.setDaemon(true);
//        thread.start();
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        if(arg != null) {
//            orderQueue.add((Order) arg);
////            setChanged();
////            notifyObservers(order);
//        }
//    }
//}
