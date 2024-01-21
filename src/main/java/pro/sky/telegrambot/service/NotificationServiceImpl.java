package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

 /**
 *Класс сервиса для работы с {@link NotificationRepository} и сущностью {@link Notification}
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
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
