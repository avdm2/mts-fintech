package ru.mts;

import ru.mts.models.AbstractAnimal;
import ru.mts.models.impl.Cat;
import ru.mts.models.impl.Dog;
import ru.mts.models.impl.Shark;
import ru.mts.models.impl.Wolf;
import ru.mts.services.CreateAnimalServiceImpl;
import ru.mts.services.utils.AbstractAnimalTesterImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        AbstractAnimalTesterImpl tester = new AbstractAnimalTesterImpl();

        AbstractAnimal[] data = new CreateAnimalServiceImpl().createAnimals(10);
        tester.test(data);

        AbstractAnimal[] dataWithDuplicates = {
                new Dog("breed", "name", BigDecimal.ONE, LocalDate.of(2010, 10, 10)),
                new Wolf("breed", "name", BigDecimal.valueOf(59_000), LocalDate.now()),
                new Cat("black and white", "cat", BigDecimal.valueOf(9_999), LocalDate.of(2015, 10, 10)),
                new Shark("hammerhead", "shark", BigDecimal.valueOf(1_000_000), LocalDate.of(2010, 10, 10)),
                new Dog("breed", "name", BigDecimal.ONE, LocalDate.of(2010, 10, 10))
        };
        tester.test(dataWithDuplicates);
    }
}