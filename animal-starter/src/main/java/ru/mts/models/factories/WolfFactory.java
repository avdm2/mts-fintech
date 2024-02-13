package ru.mts.models.factories;

import ru.mts.models.templates.AbstractAnimal;
import ru.mts.models.impl.Wolf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

/**
 * Фабрика, создающая волков.
 */
public class WolfFactory extends AnimalFactory {

    private final BigDecimal maxWolfCost = BigDecimal.valueOf(100_000);

    private final String[] names;

    public WolfFactory(String[] names) {
        this.names = names;
    }

    @Override
    public AbstractAnimal createAnimal(Random random) {
        int randomValue = random.nextInt(0, 1_000_000);
        LocalDate start = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2023, Month.DECEMBER, 31);

        return new Wolf("Breed#" + randomValue % 100,
                names[randomValue % names.length],
                BigDecimal.valueOf(randomValue % maxWolfCost.doubleValue() + 0.9385),
                LocalDate.ofEpochDay(random.nextLong(start.toEpochDay(), end.toEpochDay() + 1)));
    }
}
