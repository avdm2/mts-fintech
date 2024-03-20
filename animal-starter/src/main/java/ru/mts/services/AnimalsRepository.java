package ru.mts.services;

import ru.mts.exceptions.InvalidListSizeException;
import ru.mts.models.templates.AbstractAnimal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс, представляющий собой сервис для работы (поиска) с массивом животных.
 */
public interface AnimalsRepository {

    /**
     * Метод, находящий полное количество лет животного.
     * @param animal Животного, возраст которого следует найти.
     * @return Возраст животного.
     */
    int getAnimalAge(AbstractAnimal animal);

    /**
     * Метод, который находит всех животных, родившихся в високосный год.
     * @return Словарь искомых животных, в котором ключ вида {animalType} {animalName}, а значение - дата рождения.
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Метод, находящий всех животных, возраст которых старше N лет.
     * @param age Число лет, по которому происходит поиск животных.
     * @return Словарь животных, удовлетворяющих условиям. Ключ - найденное животное, значение - возраст.
     */
    Map<AbstractAnimal, Integer> findOlderAnimal(int age);

    /**
     * Метод, находящий дубликаты животных в списке.
     * @return Словарь, ключ которого - тип животного, а значение - список дубликатов.
     */
    Map<String, List<AbstractAnimal>> findDuplicate();


    /**
     * Метод, выводящий в стандартный поток вывода информацию о дублирующихся животных.
     */
    void printDuplicate();

    /**
     * Метод, находящий средний возраст всех животных.
     * @return Средний возраст животных в коллекции.
     */
    double findAverageAge();

    /**
     * Метод, находящий животных, возраст которых больше 5 лет, а стоимость выше средней.
     * @return Список животных согласно требованию, отсортированный по дате рождения (по возрастанию) список.
     */
    List<AbstractAnimal> findOldAndExpensive();

    /**
     * Метод, находящий трех самых дешевых животных.
     * @return Список животных согласно требованию, отсортированный по именам в обратном алфавитном порядке.
     */
    List<AbstractAnimal> findMinCostAnimals() throws InvalidListSizeException;
}
