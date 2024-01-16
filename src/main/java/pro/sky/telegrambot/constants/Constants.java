package pro.sky.telegrambot.constants;

import com.vdurmont.emoji.EmojiParser;

public class Constants {
    public final static String EMOJI_DOG = EmojiParser.parseToUnicode(":dog:");
    public final static String EMOJI_WAVE = EmojiParser.parseToUnicode(":smiley:");
    public final static String WELCOME_MSG_TEXT = "Привет " + EMOJI_WAVE + " Это бот приюта для собак. Чем могу Вам помочь? " + EMOJI_DOG;

    public final static String BUTTON_STAGE1_TEXT = "Узнать информацию о приюте";
    public final static String BUTTON_STAGE2_TEXT = "Как взять собаку из приюта";
    public final static String BUTTON_STAGE3_TEXT = "Прислать отчет о питомце";
    public final static String BUTTON_CALL_VOLUNTEER_TEXT = "Позвать волотера";
    public final static String BUTTON_STAGE1_CALLBACK_TEXT = "button Stage1 clicked";
    public final static String BUTTON_STAGE2_CALLBACK_TEXT = "button Stage2 clicked";
    public final static String BUTTON_STAGE3_CALLBACK_TEXT = "button Stage3 clicked";
    public final static String BUTTON_CALL_VOLUNTEER_CALLBACK_TEXT = "button CallVolunteer clicked";
}
