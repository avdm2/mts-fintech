package ru.mts.models.impl;

import ru.mts.models.Pet;

import java.math.BigDecimal;

public class Dog extends Pet {

    public Dog(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

    @Override
    public String toString() {
        return "[Dog] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character;
    }
}
