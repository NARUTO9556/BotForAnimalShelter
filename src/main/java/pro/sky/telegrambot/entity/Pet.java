package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String petName;
    private Integer age;
    private byte[] photo;
    private String kindOfAnimal;
    private String animalBreed;
    private Long shelterId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Pet() {
    }

    public Pet(String petName, Integer age, byte[] photo, String kindOfAnimal, String animalBreed, Long shelterId, Customer customer) {
        this.petName = petName;
        this.age = age;
        this.photo = photo;
        this.kindOfAnimal = kindOfAnimal;
        this.animalBreed = animalBreed;
        this.shelterId = shelterId;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getKindOfAnimal() {
        return kindOfAnimal;
    }

    public void setKindOfAnimal(String kindOfAnimal) {
        this.kindOfAnimal = kindOfAnimal;
    }

    public String getAnimalBreed() {
        return animalBreed;
    }

    public void setAnimalBreed(String animalBreed) {
        this.animalBreed = animalBreed;
    }

    public Long getShelterId() {
        return shelterId;
    }

    public void setShelterId(Long shelterId) {
        this.shelterId = shelterId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(petName, pet.petName) && Objects.equals(age, pet.age) && Arrays.equals(photo, pet.photo) && Objects.equals(kindOfAnimal, pet.kindOfAnimal) && Objects.equals(animalBreed, pet.animalBreed) && Objects.equals(shelterId, pet.shelterId) && Objects.equals(customer, pet.customer);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, petName, age, kindOfAnimal, animalBreed, shelterId, customer);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
