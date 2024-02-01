package ru.mts.services;

import ru.mts.models.AbstractAnimal;

/**
 * Интерфейс, представляющий собой сервис для работы (поиска) с массивом животных.
 */
public interface SearchAnimalService {

    /**
     * Метод, который находит всех животных, родившихся в високосный год.
     * @return Массив имен искомых животных.
     */
    String[] findLeapYearNames();

    /**
     * Метод, находящий всех животных, возраст которых старше N лет.
     * @param n Число лет, по которому происходит поиск животных.
     * @return Массив животных, удовлетворяющих условиям.
     */
    AbstractAnimal[] findOlderAnimal(int n);

    /**
     * Метод, удаляющий дубликаты животных из массива.
     * @return Массив всех повторяющихся животных.
     */
    AbstractAnimal[] findDuplicate();
}
