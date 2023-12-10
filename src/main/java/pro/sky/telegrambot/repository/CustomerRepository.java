package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findById(long id);
}
