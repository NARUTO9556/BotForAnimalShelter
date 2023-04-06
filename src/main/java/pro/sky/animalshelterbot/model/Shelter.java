package pro.sky.animalshelterbot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;

/**
 * Класс содержит информацию о приюте
 */
@Entity
public class Shelter {
    /**
     * идентификатор записи, primary key
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Название приюта
     */
    private String name;

    /**
     * Адрес приюта
     */
    private String address;

    /**
     * Дополнительная информация о приюте
     */
    private String info;

    /**
     * Карта, как добраться до приюта
     */
    private byte[] map;

    public Shelter() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public byte[] getMap() {
        return map;
    }

    public void setMap(byte[] map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shelter shelter = (Shelter) o;
        return Objects.equals(id, shelter.id) && Objects.equals(name, shelter.name) && Objects.equals(address, shelter.address) && Objects.equals(info, shelter.info) && Arrays.equals(map, shelter.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, address, info);
        result = 31 * result + Arrays.hashCode(map);
        return result;
    }
}
