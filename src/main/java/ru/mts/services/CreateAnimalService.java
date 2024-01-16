package ru.mts.services;

import ru.mts.models.AbstractAnimal;

/**
 * Интерфейс, представляющий собой сервис для создания животных.
 */
public interface CreateAnimalService {

    /**
     * Метод создания массива животных.
     *
     * @param amount Количество животных, которое должно быть сгенерировано.
     * @throws IllegalArgumentException В случае, если желаемое число сгенерированных животных меньше или равно 0.
     * @return Массив созданных животных.
     */
    AbstractAnimal[] createAnimals(int amount) throws IllegalArgumentException;
}
