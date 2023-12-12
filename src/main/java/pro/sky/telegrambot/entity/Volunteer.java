package pro.sky.telegrambot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameVolunteer;
    private String chatId;
    private String phoneNumber;

    public Volunteer() {
    }

    public Volunteer(String nameVolunteer, String chatId, String phoneNumber) {
        this.nameVolunteer = nameVolunteer;
        this.chatId = chatId;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getNameVolunteer() {
        return nameVolunteer;
    }

    public void setNameVolunteer(String name) {
        this.nameVolunteer = nameVolunteer;
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
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id) && Objects.equals(nameVolunteer, volunteer.nameVolunteer) && Objects.equals(chatId, volunteer.chatId) && Objects.equals(phoneNumber, volunteer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameVolunteer, chatId, phoneNumber);
    }
}
