package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Connection;
@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
