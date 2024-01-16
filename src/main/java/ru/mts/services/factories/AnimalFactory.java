package ru.mts.services.factories;

import ru.mts.models.AbstractAnimal;

import java.util.Random;

/**
 * Фабрика для создания животных.
 */
public abstract class AnimalFactory {

    /**
     * Метод для создания одного животного, которое является наследником типа AbstractAnimal.
     * @param random Экземпляр класса Random для генерации случайного животного.
     * @return Созданное животное типа AbstractAnimal.
     */
    public abstract AbstractAnimal createAnimal(Random random);
}
