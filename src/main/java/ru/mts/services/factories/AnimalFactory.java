package ru.mts.services.factories;

import ru.mts.models.AbstractAnimal;

/**
 * Фабрика для создания животных.
 */
public abstract class AnimalFactory {

    /**
     * Метод для создания одного животного, которое является наследником типа AbstractAnimal.
     * @return Созданное животное типа AbstractAnimal.
     */
    public abstract AbstractAnimal createAnimal();
}
