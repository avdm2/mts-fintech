package ru.mts.models.factories;

import ru.mts.models.templates.AbstractAnimal;
import ru.mts.models.impl.Cat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;

/**
 * Фабрика, создающая котов.
 */
public class CatFactory extends AnimalFactory {

    private final BigDecimal maxCatCost = BigDecimal.valueOf(10_000);

    private final List<String> names;

    public CatFactory(List<String> names) {
        this.names = names;
    }

    @Override
    public AbstractAnimal createAnimal(Random random) {
        int randomValue = random.nextInt(0, 1_000_000);
        LocalDate start = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2023, Month.DECEMBER, 31);

        return new Cat("Breed#" + randomValue % 100,
                names.get(randomValue % names.size()),
                BigDecimal.valueOf(randomValue % maxCatCost.doubleValue() + 0.2748),
                LocalDate.ofEpochDay(random.nextLong(start.toEpochDay(), end.toEpochDay() + 1)));
    }
}
