package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
