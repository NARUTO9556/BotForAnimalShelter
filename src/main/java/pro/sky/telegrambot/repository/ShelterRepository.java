package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Shelter;
@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Shelter findById(long id);
}
