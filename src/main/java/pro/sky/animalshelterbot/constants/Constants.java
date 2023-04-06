package pro.sky.animalshelterbot.constants;

import com.vdurmont.emoji.EmojiParser;
import com.pengrad.telegrambot.model.CallbackQuery;
public class Constants {
    // Emojis
    public final static String EMOJI_DOG = EmojiParser.parseToUnicode(":dog:");
    public final static String EMOJI_WAVE = EmojiParser.parseToUnicode(":smiley:");

    // Information messages
    public final static String WELCOME_MSG_TEXT = "Привет " + EMOJI_WAVE + " Это бот приюта для собак. Чем я могу Вам помочь? " + EMOJI_DOG;

    // Buttons text
    public final static String BUTTON_STAGE1_TEXT = "Узнать информацию о приюте (этап 1)";
    public final static String BUTTON_STAGE2_TEXT = "Как взять собаку из приюта (этап 2)";
    public final static String BUTTON_STAGE3_TEXT = "Прислать отчет о питомце (этап 3)";
    public final static String BUTTON_CALL_VOLUNTEER_TEXT = "Позвать волонтера";

    // Buttons callback text
    /**
     * Текст, связанный с кнопкой STAGE1. Используйте этот текст при обработке нажатий на кнопки. <br>
     * Example: <br>
     * {@code if (callbackQuery.data().equals(BUTTON_STAGE1_CALLBACK_TEXT)) { Ваш код для обработки Stage1 }}
     * @see CallbackQuery
     */
    public final static String BUTTON_STAGE1_CALLBACK_TEXT = "button Stage1 clicked";
    public final static String BUTTON_STAGE2_CALLBACK_TEXT = "button Stage2 clicked";
    public final static String BUTTON_STAGE3_CALLBACK_TEXT = "button Stage3 clicked";
    public final static String BUTTON_CALL_VOLUNTEER_CALLBACK_TEXT = "button CallVolunteer clicked";
}
