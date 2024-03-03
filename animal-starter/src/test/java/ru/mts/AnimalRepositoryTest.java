package ru.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.models.enums.AnimalType;
import ru.mts.models.impl.Cat;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.services.AnimalsRepositoryImpl;
import ru.mts.services.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: test [findAverageAge, findOldAndExpensive, findMinCostAnimals] + fix old tests
@SpringBootTest(classes = {AnimalsRepositoryImpl.class, StarterTestConfiguration.class})
@ActiveProfiles(value = "test")
public class AnimalRepositoryTest {

    @Autowired
    private CreateAnimalServiceImpl createAnimalService;

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год")
    void testFindLeapYearNames() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2021, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2016, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<String, LocalDate> expected = Map.of(
                "CAT catName1", LocalDate.of(2020, 1, 1),
                "CAT catName3", LocalDate.of(2016, 1, 1)
        );
        assertEquals(expected, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, когда их нет")
    void testFindLeapYearNamesEmptyResult() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2022, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2021, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2017, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();
        assertEquals(new HashMap<>(), animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных старше 3 лет")
    void testFindOlderAnimal() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<AbstractAnimal, Integer> expected = Map.of(
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)), 4,
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1)), 6
        );
        assertEquals(expected, animalsRepository.findOlderAnimal(3));
    }

    @Test
    @DisplayName("Поиск животных старше -1 года")
    void testFindOlderAnimalIncorrectYearValue() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<AbstractAnimal, Integer> expected = Map.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)), 0,
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)), 2,
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)), 4,
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1)), 6
        );
        assertEquals(expected, animalsRepository.findOlderAnimal(-1));
    }

    @Test
    @DisplayName("Краевой случай, когда нет животных старше n лет")
    void testFindOlderAnimalEmptyResult() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<AbstractAnimal, Integer> expected = Map.of(
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)), 7
        );
        assertEquals(expected, animalsRepository.findOlderAnimal(100));
    }

    @Test
    @DisplayName("Проверка дубликатов")
    void testFindDuplicate() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<String, Integer> expected = Map.of(
                "CAT", 3
        );
        assertEquals(expected, animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Проверка дубликатов, когда их нет")
    void testFindDuplicateEmptyResult() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<String, Integer> expected = Map.of(
                "CAT", 0
        );
        assertEquals(expected, animalsRepository.findDuplicate());
    }
}
