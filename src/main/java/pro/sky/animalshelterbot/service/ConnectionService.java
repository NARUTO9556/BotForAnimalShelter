package pro.sky.animalshelterbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.sky.animalshelterbot.model.Connection;
import pro.sky.animalshelterbot.repository.ConnectoinRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *Класс сервиса для работы с {@link ConnectoinRepository} и сущностью {@link Connection}
 */
@Service
public class ConnectionService {

    private final ConnectoinRepository connectionRepository;

    private final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

    public ConnectionService(ConnectoinRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    /**
     * Метод создает экземпляр класса {@link Connection} и устанавливает значение его полей согласно значениям
     * параметров метода, после этого сохраняет сущность в БД с помошью метода {@link ConnectoinRepository#save(Object)}.
     *
     * @param petId идентификатор питомца, не может быть {@code null}
     * @param chatId идентификатор чата, не может быть {@code null}
     * @param dateTime дата и время взятия питомца из приюта, не может быть {@code null}
     */
    @Transactional
        public void create(long petId, long chatId, LocalDateTime dateTime) {
            logger.info("Method create has been run");

            Connection connection = new Connection();
            connection.setPetId(petId);
            connection.setChatId(chatId);
            connection.setDateTime(dateTime.truncatedTo(ChronoUnit.MINUTES));
            connectionRepository.save(connection);
        }

}
