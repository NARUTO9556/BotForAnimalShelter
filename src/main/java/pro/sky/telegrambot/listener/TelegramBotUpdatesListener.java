package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.CallbackQuery;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.telegrambot.constants.Constants.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            if (update.message() != null) {
                String incomeMsgText = update.message().text();
                if (incomeMsgText == null) {
                    return;
                }
                long chatId = update.message().chat().id();
                if (incomeMsgText.equals("/start")) {
                    SendMessage message = new SendMessage(chatId, WELCOME_MSG_TEXT);
                    message.replyMarkup(createButtonsStage0());
                    sendMessage(message);
                }
            }
            else {
                SendMessage message = processButtonClick(update);
                if (message != null) {
                    sendMessage(message);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
    private void sendMessage(SendMessage message) {
        SendResponse response = telegramBot.execute(message);
        if (!response.isOk()) {
            logger.warn("Message was not sent: {}, error code: {}", message, response.errorCode());
        }
    }
    private InlineKeyboardMarkup createButtonsStage0() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonStage1 = new InlineKeyboardButton(BUTTON_STAGE1_TEXT).callbackData(BUTTON_STAGE1_CALLBACK_TEXT);
        InlineKeyboardButton buttonStage2 = new InlineKeyboardButton(BUTTON_STAGE2_TEXT).callbackData(BUTTON_STAGE2_CALLBACK_TEXT);
        InlineKeyboardButton buttonStage3 = new InlineKeyboardButton(BUTTON_STAGE3_TEXT).callbackData(BUTTON_STAGE3_CALLBACK_TEXT);
        InlineKeyboardButton buttonCallVolunteer = new InlineKeyboardButton(BUTTON_CALL_VOLUNTEER_TEXT).callbackData(BUTTON_CALL_VOLUNTEER_CALLBACK_TEXT);
        inlineKeyboardMarkup.addRow(buttonStage1);
        inlineKeyboardMarkup.addRow(buttonStage2);
        inlineKeyboardMarkup.addRow(buttonStage3);
        inlineKeyboardMarkup.addRow(buttonCallVolunteer);
        return inlineKeyboardMarkup;
    }
    private SendMessage processButtonClick(Update update) {
        SendMessage message = null;
        CallbackQuery callbackQuery = update.callbackQuery();
        if (callbackQuery != null) {
            long chatId = callbackQuery.message().chat().id();
            if (callbackQuery.data().equals(BUTTON_STAGE1_CALLBACK_TEXT)) {
                // General info about the shelter (stage 1)
                message = new SendMessage(chatId, BUTTON_STAGE1_CALLBACK_TEXT);
            } else if (callbackQuery.data().equals(BUTTON_STAGE2_CALLBACK_TEXT)) {
                // How to adopt a dog (stage 2)
                message = new SendMessage(chatId, BUTTON_STAGE2_CALLBACK_TEXT);
            } else if (callbackQuery.data().equals(BUTTON_STAGE3_CALLBACK_TEXT)) {
                // Send a follow-up report (stage 3)
                message = new SendMessage(chatId, BUTTON_STAGE3_CALLBACK_TEXT);
            } else if (callbackQuery.data().equals(BUTTON_CALL_VOLUNTEER_CALLBACK_TEXT)) {
                // Call a volunteer
                message = new SendMessage(chatId, BUTTON_CALL_VOLUNTEER_CALLBACK_TEXT);
            }
        }
        return message;
    }
}
