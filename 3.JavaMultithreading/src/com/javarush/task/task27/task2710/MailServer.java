package com.javarush.task.task27.task2710;

public class MailServer implements Runnable {
    private Mail mail;

    public MailServer(Mail mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        long beforeTime = System.currentTimeMillis();
        while(mail.getText() == null){
            synchronized (mail){
                try {
                    mail.wait();
                } catch (InterruptedException e) {
                    System.out.println("MailServer exception " + e);
                }
            }
        }
        String name = Thread.currentThread().getName();
        long afterTime = System.currentTimeMillis();
        System.out.format("%s MailServer has got: [%s] in %d ms after start", name, mail.getText(), (afterTime - beforeTime));
    }
}