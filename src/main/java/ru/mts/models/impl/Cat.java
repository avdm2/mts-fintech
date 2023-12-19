package ru.mts.models.impl;

import ru.mts.models.Pet;

import java.math.BigDecimal;

public class Cat extends Pet {

    public Cat(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

    @Override
    public String toString() {
        return "[Cat] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character;
    }
}
