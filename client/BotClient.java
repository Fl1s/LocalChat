package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        Client client = new BotClient();
        client.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            String hello = "Жалкие смертные, меня зовут Бот. Я понимаю команды, но вам их не скажу, т.к я не подчиняюсь кожаным кошелькам.";
            BotClient.this.sendTextMessage(hello);

            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String userNameDelimiter = ": ";
            String[] split = message.split(userNameDelimiter);
            if (split.length != 2) return;

            String messageWithoutUserName = split[1];
            String format = null;
            switch (messageWithoutUserName) {
                case "Дата":
                    format = "d.MM.YYYY";
                    break;
                case "День":
                    format = "d";
                    break;
                case "Месяц":
                    format = "MMMM";
                    break;
                case "Год":
                    format = "YYYY";
                    break;
                case "Время":
                    format = "H:mm:ss";
                    break;
                case "Час":
                    format = "H";
                    break;
                case "Минуты":
                    format = "m";
                    break;
                case "Секунды":
                    format = "s";
                    break;
            }
            if (format != null) {
                String answer = new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
                BotClient.this.sendTextMessage("Инфа для " + split[0] + ": " + answer);
            }
        }
    }
}