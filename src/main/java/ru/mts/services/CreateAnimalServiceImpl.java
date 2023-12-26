package ru.mts.services;

import ru.mts.models.AbstractAnimal;
import ru.mts.services.factories.AnimalFactory;
import ru.mts.services.factories.CatFactory;
import ru.mts.services.factories.DogFactory;
import ru.mts.services.factories.SharkFactory;
import ru.mts.services.factories.WolfFactory;

import java.util.Random;

/**
 * Сервис для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Override
    public AbstractAnimal[] createAnimals(int amount) {
        AnimalFactory[] factories = new AnimalFactory[] {
                new CatFactory(), new DogFactory(), new SharkFactory(), new WolfFactory()
        };

        Random random = new Random();
        AbstractAnimal[] array = new AbstractAnimal[amount];
        for (int i = 0; i < amount; ++i) {
            array[i] = factories[random.nextInt(factories.length)].createAnimal();
        }

        return array;
    }
}
