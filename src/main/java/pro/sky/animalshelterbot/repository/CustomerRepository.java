package pro.sky.animalshelterbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.animalshelterbot.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(long id);
}
