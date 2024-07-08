package pro.sky.animalshelterbot.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.animalshelterbot.model.Shelter;
import pro.sky.animalshelterbot.repository.ShelterRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShelterServiceTest {

    @Mock
    private ShelterRepository shelterRepository;

    @InjectMocks
    private ShelterService shelterService;


    private final Long id1 = 1L;
    private final String name1 = "testShelter1";
    private final String address1 = "address1";
    private final String shelterSchedule1 = "shelterSchedule1";
    private final String securityInfo1 = "securityInfo1";
    private final String info1 = "info1";
    private final byte[] map1 = new byte[111];

    private final Long id2 = 1L;
    private final String name2 = "testShelter2";
    private final String address2 = "address2";
    private final String shelterSchedule2 = "shelterSchedule2";
    private final String securityInfo2 = "securityInfo2";
    private final String info2 = "info2";
    private final byte[] map2 = new byte[122];


    private final Shelter SHELTER1 = new Shelter(id1, name1, address1, shelterSchedule1, securityInfo1, info1, map1);
    private final Shelter SHELTER2 = new Shelter(id2, name2, address2, shelterSchedule2, securityInfo2, info2, map2);

    private final List<Shelter> listOfShelters = List.of(SHELTER1, SHELTER2);
    private final long idTest = 1;


    @Test
    void createShelterTest() {
        when(shelterRepository.save(SHELTER1)).thenReturn(SHELTER1);

        Shelter expected = SHELTER1;
        Shelter actual = shelterService.createShelter(SHELTER1);

        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAddress(),  actual.getAddress());
        assertEquals(expected.getShelterSchedule(),  actual.getShelterSchedule());
        assertEquals(expected.getSecurityInfo(),  actual.getSecurityInfo());
        assertEquals(expected.getInfo(), actual.getInfo());
        assertEquals(expected.getMap(), actual.getMap());

        verify(shelterRepository, only()).save(SHELTER1);
    }


    @Test
    void findShelterById() {
        when(shelterRepository.findById(idTest)).thenReturn(SHELTER1);

        Shelter expected = SHELTER1;
        Shelter actual = shelterService.findShelterById(idTest);

        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAddress(),  actual.getAddress());
        assertEquals(expected.getShelterSchedule(),  actual.getShelterSchedule());
        assertEquals(expected.getSecurityInfo(),  actual.getSecurityInfo());
        assertEquals(expected.getInfo(), actual.getInfo());
        assertEquals(expected.getMap(), actual.getMap());

        verify(shelterRepository, only()).findById(idTest);
    }

    @Test
    void updateShelter() {
        when(shelterRepository.existsById(SHELTER2.getId())).thenReturn(true);
        when(shelterRepository.save(SHELTER2)).thenReturn(SHELTER2);

        Shelter expected = SHELTER2;
        Shelter actual = shelterService.updateShelter(SHELTER2);

        assertEquals(expected, actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getAddress(),  actual.getAddress());
        assertEquals(expected.getShelterSchedule(),  actual.getShelterSchedule());
        assertEquals(expected.getSecurityInfo(),  actual.getSecurityInfo());
        assertEquals(expected.getInfo(), actual.getInfo());
        assertEquals(expected.getMap(), actual.getMap());

        verify(shelterRepository, times(1)).save(SHELTER2);
        verify(shelterRepository, times(1)).existsById(SHELTER2.getId());

    }
    @Test
    void deleteShelterTest() {
        shelterService.deleteShelter(idTest);
        verify(shelterRepository, times(1)).deleteById(idTest);
    }


    @Test
    void findAllShelterTest() {
        when(shelterRepository.findAll()).thenReturn(listOfShelters);
        Collection<Shelter> actual = shelterService.findAllShelters();
        assertEquals(listOfShelters, actual);
        verify(shelterRepository, only()).findAll();
    }
}