package pro.sky.animalshelterbot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс содержит данные о питомце
 */
@Entity
public class Pet {

    /**
     * идентификатор записи, primary key
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * имя животного
     */
    private String petName;

    /**
     * возраст животного
     */
    private Integer age;

    /**
     * фотография животного
     */
    private byte[] photo;

    /**
     * вид животного
     */
    private String kindOfAnimal;

    /**
     * порода животного
     */
    private String animalBreed;

    /**
     * id приюта за которым закреплено животное
     */
    private Long shelterId;

    /**
     * id владельца животного
     */
    private Long ownerId;

    // второй вариант вместо private Long ownerId:
    // @ManyToOne
    //@JoinColumn(name = "customer_id")
    //private Customer customer;


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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(petName, pet.petName) && Objects.equals(age, pet.age) && Arrays.equals(photo, pet.photo) && Objects.equals(kindOfAnimal, pet.kindOfAnimal) && Objects.equals(animalBreed, pet.animalBreed) && Objects.equals(shelterId, pet.shelterId) && Objects.equals(ownerId, pet.ownerId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, petName, age, kindOfAnimal, animalBreed, shelterId, ownerId);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
