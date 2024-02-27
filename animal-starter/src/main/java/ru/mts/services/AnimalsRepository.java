package ru.mts.services;

import ru.mts.models.templates.AbstractAnimal;

import java.time.LocalDate;
import java.util.Map;

/**
 * Интерфейс, представляющий собой сервис для работы (поиска) с массивом животных.
 */
public interface AnimalsRepository {

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
     * Метод, удаляющий дубликаты животных из массива.
     * @return Сет повторяющихся животных. Ключ - тип животного, значение - количество дубликатов.
     */
    Map<String, Integer> findDuplicate();


    /**
     * Метод, выводящий в стандартный поток вывода информацию о дублирующихся животных.
     */
    void printDuplicate();
}
