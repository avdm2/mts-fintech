package ru.mts.services.impl;

import ru.mts.services.CreateAnimalService;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    public void createAnimals(int amount) {
        for (int i = 0; i < amount; ++i) {
            System.out.println(createAnimal());
        }
    }

    @Override
    public void createAnimals() {
        int amount = 0;
        do {
            System.out.println(createAnimal());
            ++amount;
        } while (amount < 10);
    }
}
