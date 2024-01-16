package ru.mts.services.factories;

import ru.mts.models.AbstractAnimal;
import ru.mts.models.impl.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

/**
 * Фабрика, создающая волков.
 */
public class WolfFactory extends AnimalFactory {

    BigDecimal maxWolfCost = BigDecimal.valueOf(100_000);

    @Override
    public AbstractAnimal createAnimal(Random random) {
        int randomValue = random.nextInt(0, 1_000_000);
        LocalDate start = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2023, Month.DECEMBER, 31);

        return new Wolf("Breed#" + randomValue % 100,
                "Wolf#" + randomValue % 100,
                BigDecimal.valueOf(randomValue % maxWolfCost.doubleValue() + 0.9385),
                LocalDate.ofEpochDay(random.nextLong(start.toEpochDay(), end.toEpochDay() + 1)));
    }
}
