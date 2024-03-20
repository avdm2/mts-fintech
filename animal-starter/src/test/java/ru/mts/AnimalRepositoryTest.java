package ru.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.exceptions.EmptyResultException;
import ru.mts.exceptions.IllegalAgeException;
import ru.mts.exceptions.InvalidListSizeException;
import ru.mts.models.enums.AnimalType;
import ru.mts.models.impl.Cat;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.services.AnimalsRepositoryImpl;
import ru.mts.services.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {AnimalsRepositoryImpl.class, StarterTestConfiguration.class})
@ActiveProfiles(value = "test")
public class AnimalRepositoryTest {

    @Autowired
    private CreateAnimalServiceImpl createAnimalService;

    @Autowired
    private AnimalsRepositoryImpl animalsRepository;

    @BeforeEach
    void initData() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "abc", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "def", BigDecimal.valueOf(20_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed3", "ghi", BigDecimal.valueOf(30_000), LocalDate.of(2015, 1, 1)),
                new Cat("catBreed4", "jkl", BigDecimal.valueOf(40_000), LocalDate.of(2021, 1, 1)),
                new Cat("catBreed5", "mno", BigDecimal.valueOf(50_000), LocalDate.of(2022, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год")
    void testFindLeapYearNames() {
        Map<String, LocalDate> expected = Map.of(
                "Cat abc", LocalDate.of(2024, 1, 1),
                "Cat def", LocalDate.of(2020, 1, 1)
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

        assertThrows(EmptyResultException.class, () -> animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных старше 3 лет")
    void testFindOlderAnimal() {
        Map<AbstractAnimal, Integer> expected = Map.of(
                new Cat("catBreed2", "def", BigDecimal.valueOf(20_000), LocalDate.of(2020, 1, 1)), 4,
                new Cat("catBreed3", "ghi", BigDecimal.valueOf(30_000), LocalDate.of(2015, 1, 1)), 9
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

        assertThrows(IllegalAgeException.class, () -> animalsRepository.findOlderAnimal(-1));
    }

    @Test
    @DisplayName("Краевой случай, когда нет животных старше n лет")
    void testFindOlderAnimalEmptyResult() {
        Map<AbstractAnimal, Integer> expected = Map.of(
                new Cat("catBreed3", "ghi", BigDecimal.valueOf(30_000), LocalDate.of(2015, 1, 1)), 9
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

        // Здесь пришлось это оставить из-за того, что в initData() генерирующаяся коллекция не подходит для теста.
        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        Map<String, List<AbstractAnimal>> expected = Map.of(
                "Cat", List.of(
                        new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)),
                        new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1))
                )
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

        assertThrows(EmptyResultException.class, () -> animalsRepository.findDuplicate());
    }

    @Test
    @DisplayName("Нахождение среднего возраста животных")
    void testFindAverageAge() {
        double expected = (0 + 4 + 9 + 3 + 2) / 5.0;
        assertEquals(expected, animalsRepository.findAverageAge());
    }

    @Test
    @DisplayName("Нахождение взрослых и дорогих животных")
    void testFindOldAndExpensive() {
        // Средняя стоимость = 30_000
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2017, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1)),
                new Cat("catBreed5", "catName5", BigDecimal.valueOf(50_000), LocalDate.of(2013, 1, 1))
        );

        // Здесь пришлось это оставить из-за того, что в initData() генерирующаяся коллекция не подходит для теста.
        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        List<AbstractAnimal> expected = List.of(
                new Cat("catBreed5", "catName5", BigDecimal.valueOf(50_000), LocalDate.of(2013, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        );
        assertEquals(expected, animalsRepository.findOldAndExpensive());
    }

    @Test
    @DisplayName("Нахождение взрослых и дорогих животных в случае, когда все взрослые животные дешевые")
    void testFindOldAndExpensiveEdgeCase1() {
        assertEquals(new ArrayList<>(), animalsRepository.findOldAndExpensive());
    }

    @Test
    @DisplayName("Нахождение взрослых и дорогих животных в случае, когда нет животных старше 5 лет")
    void testFindOldAndExpensiveEdgeCase2() {
        // Средняя стоимость = 30_000
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "catName1", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "catName2", BigDecimal.valueOf(20_000), LocalDate.of(2023, 1, 1)),
                new Cat("catBreed3", "catName3", BigDecimal.valueOf(30_000), LocalDate.of(2022, 1, 1)),
                new Cat("catBreed4", "catName4", BigDecimal.valueOf(40_000), LocalDate.of(2021, 1, 1)),
                new Cat("catBreed5", "catName5", BigDecimal.valueOf(50_000), LocalDate.of(2020, 1, 1))
        );

        // Здесь пришлось это оставить из-за того, что в initData() генерирующаяся коллекция не подходит для теста.
        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        assertThrows(EmptyResultException.class, () -> animalsRepository.findOldAndExpensive());
    }

    @Test
    @DisplayName("Нахождение животных с наименьшей стоимостью")
    void testFindMinCostAnimals() throws InvalidListSizeException {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "abc", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "def", BigDecimal.valueOf(20_000), LocalDate.of(2023, 1, 1)),
                new Cat("catBreed3", "ghi", BigDecimal.valueOf(30_000), LocalDate.of(2022, 1, 1)),
                new Cat("catBreed4", "jkl", BigDecimal.valueOf(40_000), LocalDate.of(2021, 1, 1)),
                new Cat("catBreed5", "mno", BigDecimal.valueOf(50_000), LocalDate.of(2020, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        List<AbstractAnimal> expected = List.of(
                new Cat("catBreed3", "ghi", BigDecimal.valueOf(30_000), LocalDate.of(2015, 1, 1)),
                new Cat("catBreed2", "def", BigDecimal.valueOf(20_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed1", "abc", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1))
        );
        assertEquals(expected, animalsRepository.findMinCostAnimals());
    }

    @Test
    @DisplayName("Нахождение животных с наименьшей стоимостью и проверка исключения при некорректном размере списка")
    void testFindMinCostAnimalsWithThrownException() {
        List<AbstractAnimal> animals = List.of(
                new Cat("catBreed1", "abc", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Cat("catBreed2", "def", BigDecimal.valueOf(20_000), LocalDate.of(2023, 1, 1))
        );

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(Map.of(AnimalType.CAT.toString(), animals));
        animalsRepository.initData();

        assertThrows(InvalidListSizeException.class, () -> animalsRepository.findMinCostAnimals());
    }
}
