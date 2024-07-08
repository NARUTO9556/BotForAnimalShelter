package pro.sky.animalshelterbot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animalshelterbot.model.Customer;
import pro.sky.animalshelterbot.model.Pet;
import pro.sky.animalshelterbot.repository.PetRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetService petService;

    private final Long id1 = 1L;
    private final String petName1 = "testPet1";
    private final Integer age1 = 3;
    private final byte[] photo1 = {1, 1, 1};
    private final String kindOfAnimal1 = "cat";
    private final String animalBreed1 = "siamese";
    private final Long shelterId1 = 1L;
    private final Customer customer1 = new Customer();

    private final Long id2 = 2L;
    private final String petName2 = "testPet2";
    private final Integer age2 = 4;
    private final byte[] photo2 = {2, 2, 2};
    private final String kindOfAnimal2 = "dog";
    private final String animalBreed2 = "chihuahua";
    private final Long shelterId2 = 2L;
    private final Customer customer2 = new Customer();

    private final Pet PET1 = new Pet(id1, petName1, age1, photo1, kindOfAnimal1, animalBreed1, shelterId1, customer1);
    private final Pet PET2 = new Pet(id2, petName2, age2, photo2, kindOfAnimal2, animalBreed2, shelterId2, customer2);

    private final List<Pet> listOfPets = List.of(PET1, PET2);
    private final long idTest = 1;


    @Test
    void createPetTest() {
        when(petRepository.save(PET1)).thenReturn(PET1);
        Pet expected = PET1;
        Pet actual = petService.createPet(PET1);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPetName(), actual.getPetName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getKindOfAnimal(), actual.getKindOfAnimal());
        assertEquals(expected.getAnimalBreed(), actual.getAnimalBreed());
        assertEquals(expected.getShelterId(), actual.getShelterId());
        assertEquals(expected.getCustomer(), actual.getCustomer());
        verify(petRepository, only()).save(PET1);
    }

    @Test
    void findPetById() {
        when(petRepository.findById(idTest)).thenReturn(PET1);
        Pet expected = PET1;
        Pet actual = petService.findPetById(idTest);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPetName(), actual.getPetName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getKindOfAnimal(), actual.getKindOfAnimal());
        assertEquals(expected.getAnimalBreed(), actual.getAnimalBreed());
        assertEquals(expected.getShelterId(), actual.getShelterId());
        assertEquals(expected.getCustomer(), actual.getCustomer());
        verify(petRepository, only()).findById(idTest);
    }

    @Test
    void updatePet() {
        when(petRepository.existsById(PET2.getId())).thenReturn(true);
        when(petRepository.save(PET2)).thenReturn(PET2);

        Pet expected = PET2;
        Pet actual = petService.updatePet(PET2);
        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPetName(), actual.getPetName());
        assertEquals(expected.getAge(), actual.getAge());
        assertEquals(expected.getPhoto(), actual.getPhoto());
        assertEquals(expected.getKindOfAnimal(), actual.getKindOfAnimal());
        assertEquals(expected.getAnimalBreed(), actual.getAnimalBreed());
        assertEquals(expected.getShelterId(), actual.getShelterId());
        assertEquals(expected.getCustomer(), actual.getCustomer());
        verify(petRepository, times(1)).save(PET2);
        verify(petRepository, times(1)).existsById(PET2.getId());

    }

    @Test
    void deletePetTest() {
        petService.deletePet(idTest);
        verify(petRepository, only()).deleteById(idTest);
    }

    @Test
    void findAllPetTest() {
        when(petRepository.findAll()).thenReturn(listOfPets);
        Collection<Pet> expected = listOfPets;
        Collection<Pet> actual = petService.findAllPets();
        assertEquals(expected, actual);
        verify(petRepository, only()).findAll();
    }
}