package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() { return new BotSocketThread(); }

    @Override
    protected boolean shouldSendTextFromConsole() { return false; }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random()*100);
    }

    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            System.out.println(message);
            String answer, name, text;

            if(message.contains(": ")) {
                name = message.substring(0, message.indexOf(':'));
                text = message.substring(message.indexOf(':') + 1).trim();
            }else return;

            if      (text.equalsIgnoreCase("дата"   )) { answer = "d.MM.YYYY";
            }else if(text.equalsIgnoreCase("день"   )) { answer = "d";
            }else if(text.equalsIgnoreCase("месяц"  )) { answer = "MMMM";
            }else if(text.equalsIgnoreCase("год"    )) { answer = "YYYY";
            }else if(text.equalsIgnoreCase("время"  )) { answer = "H:mm:ss";
            }else if(text.equalsIgnoreCase("час"    )) { answer = "H";
            }else if(text.equalsIgnoreCase("минуты" )) { answer = "m";
            }else if(text.equalsIgnoreCase("секунды")) { answer = "s";
            }else return;

            sendTextMessage("Информация для " + name + ": " + (new SimpleDateFormat(answer).format(Calendar.getInstance().getTime())));
        }
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
