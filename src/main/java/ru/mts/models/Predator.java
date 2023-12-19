package ru.mts.models;

import java.math.BigDecimal;

public abstract class Predator extends AbstractAnimal {

    public Predator(String breed, String name, BigDecimal cost) {
        super(breed, name, cost, "aggressive");
    }
}
