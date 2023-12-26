package ru.mts.services.utils;

import ru.mts.models.AbstractAnimal;

/**
 * Интерфейс, представляющий сервис для тестирования массива животных.
 */
public interface AbstractAnimalTester {

    /**
     * Тестирование массива.
     * @param data Массив, который будет использован для тестирования.
     */
    void test(AbstractAnimal[] data);
}
