package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread{
        Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }
        public Socket getSocket() {
            return socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message answer = null;

            while(true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                answer = connection.receive();

                if(answer.getType()==MessageType.USER_NAME) {
                    if (!answer.getData().isEmpty()) {
                        if (!connectionMap.containsKey(answer.getData())) {
                            connectionMap.put(answer.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            break;
                        }
                    }
                }
            }

            return answer.getData();
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if(userName.equals(pair.getKey())) continue;
                Message message = new Message(MessageType.USER_ADDED, pair.getKey());
                connection.send(message);
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message = null;
            Message toSend = null;
            while(true){
                message = connection.receive();
                if(message.getType() == MessageType.TEXT){
                    toSend = new Message(message.getType(), userName + ": " + message.getData());
                    sendBroadcastMessage(toSend);
                } else {
                    System.out.println("Ожидается сообщение типа TEXT");
                }
            }
        }

        public void run(){
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            System.out.println( "Установлено соединение с: " + socketAddress);
            String userName = null;

            try {
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (ClassNotFoundException e) {
                System.out.println("При обмене данными с удаленным адресом произошла ошибка1: " + e);
            } catch (IOException e) {
                System.out.println("При обмене данными с удаленным адресом произошла ошибка2: " + e);
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(userName != null) connectionMap.remove(userName);
            sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            System.out.println( "Cоединение с удаленным адресом прервано: " + socketAddress);

        }
    }

    public static void sendBroadcastMessage(Message message){
        for (Map.Entry<String,Connection> pair : connectionMap.entrySet()) {
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Server Error while sending broadcast message" + e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Введите порт:");
        int port = ConsoleHelper.readInt();
        InetAddress ia = null;

        try (ServerSocket ss = new ServerSocket(port)){
            System.out.println("Сервер запущен. Порт: " + port);

            while(true){
                Socket soc = ss.accept();
                Handler handler = new Handler(soc);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
