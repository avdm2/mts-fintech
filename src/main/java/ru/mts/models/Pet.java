package ru.mts.models;

import java.math.BigDecimal;

public abstract class Pet extends AbstractAnimal {

    public Pet(String breed, String name, BigDecimal cost) {
        super(breed, name, cost, "friendly");
    }
}
