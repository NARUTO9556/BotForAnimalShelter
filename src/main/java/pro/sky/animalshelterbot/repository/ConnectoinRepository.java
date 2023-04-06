package pro.sky.animalshelterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.animalshelterbot.model.Connection;

@Repository
public interface ConnectoinRepository extends JpaRepository<Connection, Long> {
}
