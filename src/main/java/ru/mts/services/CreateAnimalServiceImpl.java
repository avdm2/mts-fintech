package ru.mts.services;

import ru.mts.models.enums.AnimalType;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.models.factories.AnimalFactory;
import ru.mts.models.factories.CatFactory;
import ru.mts.models.factories.DogFactory;
import ru.mts.models.factories.SharkFactory;
import ru.mts.models.factories.WolfFactory;

import java.util.Random;

/**
 * Сервис для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final AnimalType animalType = AnimalType.CAT;
    private static final Random random = new Random();

    @Override
    public AbstractAnimal[] createAnimals(int amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Check data for correctness.");
        }

        AnimalFactory factory = new CatFactory();
        switch (animalType) {
            case DOG -> factory = new DogFactory();
            case SHARK -> factory = new SharkFactory();
            case WOLF -> factory = new WolfFactory();
        }

        AbstractAnimal[] array = new AbstractAnimal[amount];
        for (int i = 0; i < amount; ++i) {
            array[i] = factory.createAnimal(random);
        }

        return array;
    }
}
