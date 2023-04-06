package pro.sky.animalshelterbot.model;

import javax.persistence.*;
import java.util.Objects;


/**
 * Класс с данными о владельце животного
 */
@Entity
public class Customer {
    /**
            * идентификатор записи, primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * имя хозяина животного
     */
    private String name;

    /**
     * идентификатор чата хозяина животного в Telegram
     */
    private String chatId;

    /**
     * номер телефона хозяина животного
     */
    private String phoneNumber;

    public Customer() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
