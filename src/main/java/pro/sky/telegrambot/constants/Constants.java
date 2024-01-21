package pro.sky.telegrambot.constants;

import com.vdurmont.emoji.EmojiParser;

public class Constants {
    // Emojis
    public final static String EMOJI_DOG = EmojiParser.parseToUnicode(":dog:");
    public final static String EMOJI_CAT = EmojiParser.parseToUnicode(":cat:");
    public final static String EMOJI_WAVE = EmojiParser.parseToUnicode(":smiley:");

    // Information messages
    public final static String WELCOME_MSG_TEXT = "Привет " + EMOJI_WAVE + " Это бот приюта для собак. Чем могу Вам помочь? " + EMOJI_DOG + EMOJI_CAT;

    // Buttons text
    public final static String BUTTON_STAGE1_TEXT = "Узнать информацию о приюте";
    public final static String BUTTON_STAGE2_TEXT = "Как взять собаку из приюта";
    public final static String BUTTON_STAGE3_TEXT = "Прислать отчет о питомце";
    public final static String BUTTON_CALL_VOLUNTEER_TEXT = "Позвать волотера";

    // Buttons callback text
    /**
     * Текст, связанный с кнопкой STAGE1. Используйте этот текст при обработке нажатий на кнопки. <br>
     * Example: <br>
     * {@code if (callbackQuery.data().equals(BUTTON_STAGE1_CALLBACK_TEXT)) { Ваш код для обработки Stage1 }}
     */
    public final static String BUTTON_STAGE1_CALLBACK_TEXT = "button Stage1 clicked";
    public final static String BUTTON_STAGE2_CALLBACK_TEXT = "button Stage2 clicked";
    public final static String BUTTON_STAGE3_CALLBACK_TEXT = "button Stage3 clicked";
    public final static String BUTTON_CALL_VOLUNTEER_CALLBACK_TEXT = "button CallVolunteer clicked";
}
