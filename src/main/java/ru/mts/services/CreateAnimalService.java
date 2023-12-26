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
     * @return Массив созданных животных.
     */
    AbstractAnimal[] createAnimals(int amount);
}
