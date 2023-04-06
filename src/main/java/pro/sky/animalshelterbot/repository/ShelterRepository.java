package pro.sky.animalshelterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.animalshelterbot.model.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    Shelter findById(long id);
}
