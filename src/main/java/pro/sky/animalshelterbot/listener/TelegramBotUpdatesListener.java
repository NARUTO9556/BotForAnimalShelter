package pro.sky.animalshelterbot.listener;

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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static pro.sky.animalshelterbot.constants.Constants.*;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    private final TelegramBot telegramBot;

    public TelegramBotUpdatesListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            // Обработка приветственного сообщения (этап 0)
            if (update.message() != null) {
                String incomeMsgText = update.message().text();
                // For stickers incomeMsgText is null
                if (incomeMsgText == null) {
                    return;
                }
                long chatId = update.message().chat().id();
                if (incomeMsgText.equals("/start")) {
                    SendMessage message = new SendMessage(chatId, WELCOME_MSG_TEXT);
                    // Adding buttons
                    message.replyMarkup(createButtonsStage0());
                    sendMessage(message);
                }
            }
            // Process buttons clicks
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

    /**
     * Метод создает кнопки для приветственного сообщения (ответ на команду /start) на этапе 0
     * @return InlineKeyboardMarkup
     */
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

    /**
     * Метод обработки процесса нажатия кнопки пользователем.
     * @param update пользовательский ввод (может быть текст, нажатие кнопки, эмодзи, наклейка и т.д.)
     *               но обрабатывайте только нажатия кнопок, которые определены в  {@code callbackData()}.
     * @return сообщение, которое будет отправлено обратно пользователю.
     * @see InlineKeyboardButton#callbackData()
     */
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