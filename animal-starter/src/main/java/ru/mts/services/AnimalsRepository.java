package ru.mts.services;

import ru.mts.models.templates.AbstractAnimal;

import java.util.Set;

/**
 * Интерфейс, представляющий собой сервис для работы (поиска) с массивом животных.
 */
public interface AnimalsRepository {

    /**
     * Метод, который находит всех животных, родившихся в високосный год.
     * @return Массив имен искомых животных.
     */
    String[] findLeapYearNames();

    /**
     * Метод, находящий всех животных, возраст которых старше N лет.
     * @param age Число лет, по которому происходит поиск животных.
     * @return Массив животных, удовлетворяющих условиям.
     */
    AbstractAnimal[] findOlderAnimal(int age);

    /**
     * Метод, удаляющий дубликаты животных из массива.
     * @return Сет всех повторяющихся животных.
     */
    Set<AbstractAnimal> findDuplicate();


    /**
     * Метод, выводящий в стандартный поток вывода информацию о дублирующихся животных.
     */
    void printDuplicate();
}
