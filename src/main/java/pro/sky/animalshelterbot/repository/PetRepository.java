package pro.sky.animalshelterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.animalshelterbot.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    Pet findById(long id);

}
