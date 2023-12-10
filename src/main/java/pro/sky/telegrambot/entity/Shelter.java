package pro.sky.telegrambot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameShelter;
    private String address;
    private String info;
    private byte[] map;

    public Shelter() {
    }

    public Shelter(String nameShelter, String address, String info, byte[] map) {
        this.nameShelter = nameShelter;
        this.address = address;
        this.info = info;
        this.map = map;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return nameShelter;
    }

    public void setName(String name) {
        this.nameShelter = nameShelter;
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
        return Objects.equals(id, shelter.id) && Objects.equals(nameShelter, shelter.nameShelter) && Objects.equals(address, shelter.address) && Objects.equals(info, shelter.info) && Arrays.equals(map, shelter.map);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nameShelter, address, info);
        result = 31 * result + Arrays.hashCode(map);
        return result;
    }
}
