package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client extends Thread{
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress(){
        System.out.println("Введите адрес сервера: ");
        return ConsoleHelper.readString();
    }
    protected int getServerPort(){
        System.out.println("Введите адрес порта: ");
        return ConsoleHelper.readInt();
    }
    protected String getUserName(){
        System.out.println("Введите свое имя: ");
        return ConsoleHelper.readString();
    }
    protected boolean shouldSendTextFromConsole(){
        return true;
    }
    protected SocketThread getSocketThread(){
        return new SocketThread();
    }
    protected void sendTextMessage(String text){
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            System.out.println("Во время отправки сообщения произошла ошибка: " + e);
            clientConnected = false;
        }
    }

    @Override
    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        try {
            synchronized (this){
                this.wait();
            };
        } catch (InterruptedException e) {
            System.out.println("Возникла ошибка ожидания соединения: " + e);
        }
        if(clientConnected)
            System.out.println("Соединение установлено. Для выхода наберите команду 'exit'.");
        else
            System.out.println("Произошла ошибка во время работы клиента.");

        while (clientConnected){
            String inString;
            if ((inString = ConsoleHelper.readString()).equalsIgnoreCase("EXIT")){
                clientConnected = false;
                break;
            }
            if(shouldSendTextFromConsole()) sendTextMessage(inString);
        }
    }

    public class SocketThread extends Thread{
        @Override
        public void run() {
            String server = getServerAddress();
            int    port   = getServerPort();
            try {
                Socket socket = new Socket(server, port);
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage    (String message)  { System.out.println(message); }
        protected void informAboutAddingNewUser  (String userName) { System.out.println("К нам присоединился: " + userName); }
        protected void informAboutDeletingNewUser(String userName) { System.out.println(userName + " покинул чат"); }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true){
                Message inMes = connection.receive();
                if(inMes.getType() == MessageType.NAME_REQUEST ){
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                }else if(inMes.getType() == MessageType.NAME_ACCEPTED){
                    notifyConnectionStatusChanged(true);
                    break;
                }else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true){
                Message inMes = connection.receive();
                if(inMes.getType() == MessageType.TEXT ){
                    processIncomingMessage(inMes.getData());
                }else if(inMes.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(inMes.getData());
                }else if(inMes.getType() == MessageType.USER_REMOVED){
                    informAboutDeletingNewUser(inMes.getData());
                }else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }
    }


    public static void main(String[] args) {
        new Client().run();
    }
}
