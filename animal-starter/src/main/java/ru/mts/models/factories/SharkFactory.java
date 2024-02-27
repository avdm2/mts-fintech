package ru.mts.models.factories;

import ru.mts.models.templates.AbstractAnimal;
import ru.mts.models.impl.Shark;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Random;

/**
 * Фабрика, создающая акул.
 */
public class SharkFactory extends AnimalFactory {

    private final BigDecimal maxSharkCost = BigDecimal.valueOf(1_000_000);

    private final List<String> names;

    public SharkFactory(List<String> names) {
        this.names = names;
    }

    @Override
    public AbstractAnimal createAnimal(Random random) {
        int randomValue = random.nextInt(0, 1_000_000);
        LocalDate start = LocalDate.of(2010, Month.JANUARY, 1);
        LocalDate end = LocalDate.of(2023, Month.DECEMBER, 31);

        return new Shark("Breed#" + randomValue % 100,
                names.get(randomValue % names.size()),
                BigDecimal.valueOf(randomValue % maxSharkCost.doubleValue() + 0.2191),
                LocalDate.ofEpochDay(random.nextLong(start.toEpochDay(), end.toEpochDay() + 1)));
    }
}
