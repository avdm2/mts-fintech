package ru.mts.services;

import ru.mts.config.StarterConfigurationProperties;
import ru.mts.models.enums.AnimalType;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.models.factories.AnimalFactory;
import ru.mts.models.factories.CatFactory;
import ru.mts.models.factories.DogFactory;
import ru.mts.models.factories.SharkFactory;
import ru.mts.models.factories.WolfFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Сервис для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private AnimalType animalType = AnimalType.CAT;
    private static final Random random = new Random();

    private final StarterConfigurationProperties starterConfigurationProperties;

    public CreateAnimalServiceImpl(StarterConfigurationProperties starterConfigurationProperties) {
        this.starterConfigurationProperties = starterConfigurationProperties;
    }

    @Override
    public AnimalType getAnimalType() {
        return animalType;
    }

    @Override
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    @Override
    public Map<String, List<AbstractAnimal>> createAnimals(int amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Check data for correctness.");
        }

        List<String> names = starterConfigurationProperties.getCatNames();
        AnimalFactory factory = new CatFactory(names);

        switch (animalType) {
            case DOG:
                names = starterConfigurationProperties.getDogNames();
                factory = new DogFactory(names);
                break;
            case SHARK:
                names = starterConfigurationProperties.getSharkNames();
                factory = new SharkFactory(names);
                break;
            case WOLF:
                names = starterConfigurationProperties.getWolfNames();
                factory = new WolfFactory(names);
                break;
        }

        List<AbstractAnimal> animalList = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            animalList.add(factory.createAnimal(random));
        }

        return Map.of(animalType.getValue(), animalList);
    }
}
