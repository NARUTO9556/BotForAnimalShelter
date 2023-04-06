package pro.sky.animalshelterbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.animalshelterbot.model.Notification;
import pro.sky.animalshelterbot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *Класс сервиса для работы с {@link NotificationRepository} и сущностью {@link Notification}
 */
@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    /**
     * Метод создает экземпляр класса {@link Notification} и устанавливает значение его полей согласно значениям
     * параметров метода, после этого сохраняет сущность в БД
     * с помошью метода {@link NotificationRepository#save(Object)}.
     *
     * @param chatId идентификатор чата, не может быть {@code null}
     * @param text текст уведомления, не может быть {@code null}
     * @param dateTime дата и время отправления уведомления, не может быть {@code null}
     */
    @Transactional
    public void create(long chatId, String text, LocalDateTime dateTime) {
        logger.info("Method create has been run");

        Notification notification = new Notification();
        notification.setChatId(chatId);
        notification.setNotificationText(text);
        notification.setDateTime(dateTime.truncatedTo(ChronoUnit.MINUTES));
        notificationRepository.save(notification);
    }
}
