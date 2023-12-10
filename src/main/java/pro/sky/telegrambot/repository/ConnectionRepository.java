package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entity.Connection;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
