package pro.sky.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Connection;
import pro.sky.telegrambot.repository.ConnectionRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *Класс сервиса для работы с {@link ConnectionRepository} и сущностью {@link Connection}
 */
@Service
public class ConnectionServiceImpl implements ConnectionService {
    private final ConnectionRepository connectionRepository;
    private final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

    public ConnectionServiceImpl(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    /**
     * Метод создает экземпляр класса {@link Connection} и устанавливает значение его полей согласно значениям
     * параметров метода, после этого сохраняет сущность в БД с помошью метода {@link ConnectionRepository#save(Object)}.
     *
     * @param petId идентификатор питомца, не может быть {@code null}
     * @param chatId идентификатор чата, не может быть {@code null}
     * @param dateTime дата и время взятия питомца из приюта, не может быть {@code null}
     */
    @Override
    public void create(Long petId, Long chatId, LocalDateTime dateTime) {
        logger.info("Method create has been run");
        Connection connection = new Connection();
        connection.setPetId(petId);
        connection.setChatId(chatId);
        connection.setDateTime(dateTime.truncatedTo(ChronoUnit.MINUTES));
        connectionRepository.save(connection);
    }
}
