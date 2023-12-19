package ru.mts.services;

import ru.mts.models.AbstractAnimal;
import ru.mts.models.impl.Cat;
import ru.mts.models.impl.Dog;
import ru.mts.models.impl.Shark;
import ru.mts.models.impl.Wolf;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Сервис для создания животных.
 */
public interface CreateAnimalService {
    Random random = new Random();
    BigDecimal maxCatCost = BigDecimal.valueOf(10_000);
    BigDecimal maxDogCost = BigDecimal.valueOf(25_000);
    BigDecimal maxSharkCost = BigDecimal.valueOf(1_000_000);
    BigDecimal maxWolfCost = BigDecimal.valueOf(100_000);

    /**
     * Метод создания одного случайного животного.
     * @return Созданное животное.
     */
    default AbstractAnimal createAnimal() {
        int randomValue = random.nextInt(0, 1_000_000);
        String breed = "Breed#" + randomValue % 100;
        String name;
        BigDecimal cost;

        switch (randomValue % 4) {
            case 0:
                name = "Cat#" + randomValue % 100;
                cost = BigDecimal.valueOf(randomValue % maxCatCost.doubleValue() + 0.2748);
                return new Cat(breed, name, cost);
            case 1:
                name = "Dog#" + randomValue % 100;
                cost = BigDecimal.valueOf(randomValue % maxDogCost.doubleValue() + 0.3419);
                return new Dog(breed, name, cost);
            case 2:
                name = "Shark#" + randomValue % 100;
                cost = BigDecimal.valueOf(randomValue % maxSharkCost.doubleValue() + 0.2191);
                return new Shark(breed, name, cost);
            case 3:
                name = "Wolf#" + randomValue % 100;
                cost = BigDecimal.valueOf(randomValue % maxWolfCost.doubleValue() + 0.9385);
                return new Wolf(breed, name, cost);
            default:
                return null;
        }
    }

    /**
     * Метод для создания 10 животных.
     */
    default void createAnimals() {
        int count = 0;
        while (count < 10) {
            System.out.println(createAnimal());
            ++count;
        }
    }
}
