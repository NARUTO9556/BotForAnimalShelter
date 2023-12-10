package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Pet;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Pet findById(long id);
}
