package ru.mts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.models.impl.Cat;
import ru.mts.models.impl.Dog;
import ru.mts.models.impl.Shark;
import ru.mts.models.impl.Wolf;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.services.AnimalsRepositoryImpl;
import ru.mts.services.CreateAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
        AbstractAnimal[] animals = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2020, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2021, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2016, 1, 1))
        };

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(animals);
        animalsRepository.initData();
        assertArrayEquals(new String[]{"catName", null, "wolfName"}, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных, родившихся в високосный год, когда их нет")
    void testFindLeapYearNamesEmptyResult() {
        AbstractAnimal[] animals = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2022, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2021, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2017, 1, 1))
        };

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(animals);
        animalsRepository.initData();
        assertArrayEquals(new String[]{null, null, null}, animalsRepository.findLeapYearNames());
    }

    @Test
    @DisplayName("Поиск животных старше 3 лет")
    void testFindOlderAnimal() {
        AbstractAnimal[] animals = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Shark("sharkBreed", "sharkName", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        };

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(animals);
        animalsRepository.initData();

        AbstractAnimal[] expected = {
                null,
                null,
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Shark("sharkBreed", "sharkName", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        };
        assertArrayEquals(expected, animalsRepository.findOlderAnimal(3));
    }

    @Test
    @DisplayName("Поиск животных старше -1 года")
    void testFindOlderAnimalIncorrectYearValue() {
        AbstractAnimal[] animals = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Shark("sharkBreed", "sharkName", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        };

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(animals);
        animalsRepository.initData();

        AbstractAnimal[] expected = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Shark("sharkBreed", "sharkName", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        };
        assertArrayEquals(expected, animalsRepository.findOlderAnimal(-1));
    }

    @Test
    @DisplayName("Проверка дубликатов")
    void testFindDuplicate() {
        AbstractAnimal[] animals = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1))
        };

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(animals);
        animalsRepository.initData();

        Set<AbstractAnimal> set = new HashSet<>() {{
            new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1));
        }};
        assertArrayEquals(set.toArray(), animalsRepository.findDuplicate().toArray());
    }

    @Test
    @DisplayName("Проверка дубликатов, когда их нет")
    void testFindDuplicateEmptyResult() {
        AbstractAnimal[] animals = {
                new Cat("catBreed", "catName", BigDecimal.valueOf(10_000), LocalDate.of(2024, 1, 1)),
                new Dog("dogBreed", "dogName", BigDecimal.valueOf(20_000), LocalDate.of(2022, 1, 1)),
                new Wolf("wolfBreed", "wolfName", BigDecimal.valueOf(30_000), LocalDate.of(2020, 1, 1)),
                new Shark("sharkBreed", "sharkName", BigDecimal.valueOf(40_000), LocalDate.of(2018, 1, 1))
        };

        Mockito.when(createAnimalService.createAnimals(10)).thenReturn(animals);
        animalsRepository.initData();

        Set<AbstractAnimal> set = new HashSet<>();
        assertArrayEquals(set.toArray(), animalsRepository.findDuplicate().toArray());
    }
}
