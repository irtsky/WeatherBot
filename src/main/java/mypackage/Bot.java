package mypackage;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Bot extends TelegramLongPollingBot {

    //TODO: BOT_NAME is apparently unnecessary
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
            if (update.getMessage().getText().equals("/now")) {
                Message inMessage = update.getMessage();
                String chatId = inMessage.getChatId().toString();
                SendMessage sendMessage = new SendMessage(chatId, WeatherApiClient.getCurrentWeather());
                execute(sendMessage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
