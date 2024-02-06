package ru.mts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import ru.mts.models.AbstractAnimal;
import ru.mts.models.impl.Cat;
import ru.mts.models.impl.Dog;
import ru.mts.models.impl.Shark;
import ru.mts.models.impl.Wolf;
import ru.mts.services.SearchAnimalService;
import ru.mts.services.SearchAnimalServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AnimalsTest {

    private static final int SAMPLE_PRICE = 10_000;

    @Nested
    @DisplayName("Тесты корректности методов equals в созданных классах животных")
    class AnimalEqualsTests {
        @Test
        @DisplayName("Два одинаковых кота")
        void testEqualCats() {
            Cat first = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Cat second = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertTrue(result);
        }

        @Test
        @DisplayName("Два разных кота")
        void testNotEqualCats() {
            Cat first = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Cat second = new Cat("cat2", "catname2", BigDecimal.valueOf(SAMPLE_PRICE * 2), LocalDate.now().plusDays(2));
            boolean result = first.equals(second);

            assertFalse(result);
        }

        @Test
        @DisplayName("Две одинаковые собаки")
        void testEqualDogs() {
            Dog first = new Dog("dog1", "dogname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Dog second = new Dog("dog1", "dogname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertTrue(result);
        }

        @Test
        @DisplayName("Две разные собаки")
        void testNotEqualDogs() {
            Dog first = new Dog("dog1", "dogname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Dog second = new Dog("dog2", "dogname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertFalse(result);
        }

        @Test
        @DisplayName("Две одинаковые акулы")
        void testEqualSharks() {
            Shark first = new Shark("shark1", "sharkname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Shark second = new Shark("shark1", "sharkname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertTrue(result);
        }

        @Test
        @DisplayName("Две разные акулы")
        void testNotEqualSharks() {
            Shark first = new Shark("shark1", "sharkname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Shark second = new Shark("shark2", "sharkname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertFalse(result);
        }

        @Test
        @DisplayName("Два одинаковых волка")
        void testEqualWolfs() {
            Wolf first = new Wolf("wolf1", "wolfname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Wolf second = new Wolf("wolf1", "wolfname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertTrue(result);
        }

        @Test
        @DisplayName("Два разных волка")
        void testNotEqualWolfs() {
            Wolf first = new Wolf("wolf1", "wolfname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Wolf second = new Wolf("wolf2", "wolfname2", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now().plusDays(2));
            boolean result = first.equals(second);

            assertFalse(result);
        }

        @Test
        @DisplayName("Два объекта, один из которых null")
        void testNullableAnimal() {
            Cat first = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Cat second = null;
            boolean result = first.equals(second);

            assertFalse(result);
        }

        @Test
        @DisplayName("Два одинаковых по ссылке объекта (сравнение с самим собой)")
        void testEqualByReferenceAnimals() {
            Cat first = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Cat second = first;
            boolean result = first.equals(second);

            assertTrue(result);
        }

        @Test
        @DisplayName("Два объекта разного типа")
        void testDifferentClasses() {
            Cat first = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            Dog second = new Dog("dog1", "dogname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            boolean result = first.equals(second);

            assertFalse(result);
        }
    }

    @Nested
    @DisplayName("Тесты корректности методов в классе AnimalService")
    class AnimalServiceTests {

        @Test
        @DisplayName("Проверка метода findLeapYearNames")
        void testFindLeapYearNames() {
            AbstractAnimal first = mock(AbstractAnimal.class);
            when(first.getName()).thenReturn("cat");
            when(first.getBirthDate()).thenReturn(LocalDate.now());

            AbstractAnimal second = mock(AbstractAnimal.class);
            when(second.getName()).thenReturn("dog");
            when(second.getBirthDate()).thenReturn(LocalDate.of(2016, 1, 1));

            AbstractAnimal third = mock(AbstractAnimal.class);
            when(third.getName()).thenReturn("wolf");
            when(third.getBirthDate()).thenReturn(LocalDate.of(2015, 1, 1));

            AbstractAnimal[] animals = {first, second, third};
            SearchAnimalService searchAnimalService = new SearchAnimalServiceImpl(animals);
            String[] leapYearNames = searchAnimalService.findLeapYearNames();

            assertArrayEquals(new String[]{"cat", "dog", null}, leapYearNames);
        }

        @ParameterizedTest(name = "Старше {arguments} лет")
        @DisplayName("Проверка метода findOlderAnimal (все животные старше)")
        @ValueSource(ints = {3, 4, 5})
        void testFindOlderAnimalAllOlder(int n) {
            AbstractAnimal first = mock(AbstractAnimal.class);
            when(first.getBirthDate()).thenReturn(LocalDate.of(2012, 1, 1));

            AbstractAnimal second = mock(AbstractAnimal.class);
            when(second.getBirthDate()).thenReturn(LocalDate.of(2014, 1, 1));

            AbstractAnimal third = mock(AbstractAnimal.class);
            when(third.getBirthDate()).thenReturn(LocalDate.of(2016, 1, 1));

            AbstractAnimal[] animals = {first, second, third};
            SearchAnimalService searchAnimalService = new SearchAnimalServiceImpl(animals);
            AbstractAnimal[] olderAnimals = searchAnimalService.findOlderAnimal(n);

            assertArrayEquals(new AbstractAnimal[]{first, second, third}, olderAnimals);
        }

        @ParameterizedTest(name = "Старше {arguments} лет")
        @DisplayName("Проверка метода findOlderAnimal (все животные младше)")
        @ValueSource(ints = {12, 13, 14})
        void testFindOlderAnimalAllYounger(int n) {
            AbstractAnimal first = mock(AbstractAnimal.class);
            when(first.getBirthDate()).thenReturn(LocalDate.of(2012, 1, 1));

            AbstractAnimal second = mock(AbstractAnimal.class);
            when(second.getBirthDate()).thenReturn(LocalDate.of(2014, 1, 1));

            AbstractAnimal third = mock(AbstractAnimal.class);
            when(third.getBirthDate()).thenReturn(LocalDate.of(2016, 1, 1));

            AbstractAnimal[] animals = {first, second, third};
            SearchAnimalService searchAnimalService = new SearchAnimalServiceImpl(animals);
            AbstractAnimal[] olderAnimals = searchAnimalService.findOlderAnimal(n);

            assertArrayEquals(new AbstractAnimal[]{null, null, null}, olderAnimals);
        }

        @Test
        @DisplayName("Проверка метода findOlderAnimal")
        void testFindOlderAnimal() {
            AbstractAnimal first = mock(AbstractAnimal.class);
            when(first.getBirthDate()).thenReturn(LocalDate.of(2012, 1, 1));

            AbstractAnimal second = mock(AbstractAnimal.class);
            when(second.getBirthDate()).thenReturn(LocalDate.of(2014, 1, 1));

            AbstractAnimal third = mock(AbstractAnimal.class);
            when(third.getBirthDate()).thenReturn(LocalDate.of(2020, 1, 1));

            AbstractAnimal[] animals = {first, second, third};
            SearchAnimalService searchAnimalService = new SearchAnimalServiceImpl(animals);
            AbstractAnimal[] olderAnimals = searchAnimalService.findOlderAnimal(5);

            assertArrayEquals(new AbstractAnimal[]{first, second, null}, olderAnimals);
        }

        @Test
        @DisplayName("Проверка метода findDuplicates")
        void testFindDuplicates() {
            AbstractAnimal first = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            AbstractAnimal second = new Dog("dog1", "dogname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());
            AbstractAnimal third = new Cat("cat1", "catname1", BigDecimal.valueOf(SAMPLE_PRICE), LocalDate.now());

            AbstractAnimal[] animals = {first, second, third};
            SearchAnimalService searchAnimalService = new SearchAnimalServiceImpl(animals);
            AbstractAnimal[] duplicateAnimals = searchAnimalService.findDuplicate();

            assertArrayEquals(new AbstractAnimal[]{first, null, null}, duplicateAnimals);
        }
    }
}
