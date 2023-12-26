package ru.mts.services;

import ru.mts.models.AbstractAnimal;

/**
 * Интерфейс, представляющий собой сервис для работы (поиска) с массивом животных.
 */
public interface SearchAnimalService {

    /**
     * Метод, который находит всех животных, родившихся в високосный год.
     * @throws IllegalArgumentException В случае, если исходный массив null или пуст.
     * @return Массив имен искомых животных.
     */
    String[] findLeapYearNames() throws IllegalArgumentException;

    /**
     * Метод, находящий всех животных, возраст которых старше N лет.
     * @param n Число лет, по которому происходит поиск животных.
     * @throws IllegalArgumentException В случае, если исходный массив null или пуст.
     * @return Массив животных, удовлетворяющих условиям.
     */
    AbstractAnimal[] findOlderAnimal(int n) throws IllegalArgumentException;

    /**
     * Метод, удаляющий дубликаты животных из массива.
     * @throws IllegalArgumentException В случае, если исходный массив null или пуст.
     */
    void findDuplicate() throws IllegalArgumentException;

    /**
     * Проверка массива на корректность для дальнейшей работы.
     * @throws IllegalArgumentException В случае, если исходный массив null или пуст.
     */
    void checkData() throws IllegalArgumentException;
}
