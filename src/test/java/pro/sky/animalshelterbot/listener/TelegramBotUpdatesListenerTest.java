package pro.sky.animalshelterbot.listener;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animalshelterbot.repository.ShelterRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro.sky.animalshelterbot.ConstantsTest.SHELTER_TEST_1;
import static pro.sky.animalshelterbot.constants.Constants.BUTTON_STAGE1_1_CALLBACK_TEXT;
import static pro.sky.animalshelterbot.constants.Constants.WELCOME_MSG_TEXT_STAGE_0;

@ExtendWith(MockitoExtension.class)
class TelegramBotUpdatesListenerTest {

    private static final String BUTTON_STAGE1_1_CALLBACK_TEXT = ;
    @Mock
    private TelegramBot telegramBot;

    @Mock
    ShelterRepository shelterRepository;

    @InjectMocks
    private TelegramBotUpdatesListener telegramBotUpdatesListener;

    @Test
    public void handleStartTest() throws URISyntaxException, IOException {
        String json = Files.readString(
                Paths.get(TelegramBotUpdatesListener.class.getResource("text_update.json").toURI()));
        Update update = getUpdate(json, "/start");
        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(123L);
        assertThat(actual.getParameters().get("text")).isEqualTo(WELCOME_MSG_TEXT_STAGE_0);
        assertThat(actual.getParameters().get("reply_markup")).isNotNull();
    }

    @Test
    public void handleButtonInfoTest() throws URISyntaxException, IOException {
        when(shelterRepository.findById(anyLong())).thenReturn(SHELTER_TEST_1);

        String json = Files.readString(
                Path.of(TelegramBotUpdatesListener.class.getResource("data_update.json").toURI()));
        Update update = getUpdate(json, BUTTON_STAGE1_1_CALLBACK_TEXT);

        String jsonIsOkStatus
                = Files.readString(Path.of(TelegramBotUpdatesListener.class.getResource("responseIsOk.json").toURI()));

        SendResponse sendResponse = BotUtils.fromJson(jsonIsOkStatus, SendResponse.class);
        when(telegramBot.execute(any(SendMessage.class))).thenReturn(sendResponse);

        telegramBotUpdatesListener.process(Collections.singletonList(update));

        ArgumentCaptor<SendMessage> argumentCaptor = ArgumentCaptor.forClass(SendMessage.class);
        verify(telegramBot).execute(argumentCaptor.capture());
        SendMessage actual = argumentCaptor.getValue();

        assertThat(actual.getParameters().get("chat_id")).isEqualTo(222L);
        assertThat(actual.getParameters().get("text")).isEqualTo(SHELTER_TEST_1.getInfo());
    }


    private Update getUpdate(String json, String replaced) {
        return BotUtils.fromJson(json.replace("%command%", replaced), Update.class);
    }
}