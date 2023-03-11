package org.random;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    public Bot(String name, String token) {
        this.BOT_NAME = name;
        this.BOT_TOKEN = token;
    }
    @Override
    public String getBotUsername() {
        return this.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return this.BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if (update.getMessage().getText().equals("/start")) {

                Message inMess = update.getMessage();
                String chatId = inMess.getChatId().toString();
                SendMessage outMess = new SendMessage();

                outMess.setChatId(chatId);
                outMess.setText("hi");
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
