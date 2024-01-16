package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void create(Long chatId, String text, LocalDateTime dateTime) {
        logger.info("Method create has been run");
        Notification notification = new Notification();
        notification.setChatId(chatId);
        notification.setNotificationText(text);
        notification.setDateTime(dateTime.truncatedTo(ChronoUnit.MINUTES));
        notificationRepository.save(notification);
    }
}
