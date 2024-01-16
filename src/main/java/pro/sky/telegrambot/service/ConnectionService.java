package pro.sky.telegrambot.service;

import java.time.LocalDateTime;

public interface ConnectionService {
    void create(Long petId, Long chatId, LocalDateTime dateTime);
}
