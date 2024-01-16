package pro.sky.telegrambot.service;

import java.time.LocalDateTime;

public interface NotificationService {
    void create(Long chatId, String text, LocalDateTime dateTime);
}
