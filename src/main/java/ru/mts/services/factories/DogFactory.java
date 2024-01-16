package ru.mts.services.factories;

import ru.mts.models.AbstractAnimal;
import ru.mts.models.impl.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

/**
 * Фабрика, создающая собак.
 */
public class DogFactory extends AnimalFactory {

    private final BigDecimal maxDogCost = BigDecimal.valueOf(25_000);

    @Override
    public AbstractAnimal createAnimal(Random random) {
        int randomValue = random.nextInt(0, 1_000_000);
        LocalDate start = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2023, Month.DECEMBER, 31);

        return new Dog("Breed#" + randomValue % 100,
                "Dog#" + randomValue % 100,
                BigDecimal.valueOf(randomValue % maxDogCost.doubleValue() + 0.3419),
                LocalDate.ofEpochDay(random.nextLong(start.toEpochDay(), end.toEpochDay() + 1)));
    }
}
