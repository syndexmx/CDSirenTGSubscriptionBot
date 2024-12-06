package syndexmx.github.com.tgsiren.controllers.tgbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import syndexmx.github.com.tgsiren.controllers.tgbot.menu.BotMenu;
import syndexmx.github.com.tgsiren.services.backgroundwebmonitor.WebMonitor;
import syndexmx.github.com.tgsiren.services.susbcribers.SubscriberService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TgBotController extends TelegramLongPollingBot {
    final String BOT_NAME;
    final String BOT_TOKEN;

    final private WebMonitor webMonitor;
    final private SubscriberService subscriberService;


    public TgBotController(@Value("${tg-bot.name}") String botName,
                           @Value("${tg-bot.token}") String botToken,
                           @Autowired WebMonitor webMonitor,
                           @Autowired SubscriberService subscriberService) throws TelegramApiException {
        BOT_NAME = botName;
        BOT_TOKEN = botToken;
        this.webMonitor = webMonitor;
        Thread backgroundThread = new Thread() {
            public void run() {
                webMonitor.startMonitor();
            }
        };
        this.subscriberService = subscriberService;
        backgroundThread.start();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userFullCommand = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            subscriberService.serve(chatId, userFullCommand);
        }
    }

    public void sendMessage (String m, long chatId) {
        // How to from  https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(m);
        message.setReplyMarkup(BotMenu.prepareKeyboard(new ArrayList<>()));
        message.setParseMode("markdown");

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdatesReceived(String m, long chatId) {
            // How to from  https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(chatId);
            message.setText(m);
            message.setParseMode("markdown");
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
