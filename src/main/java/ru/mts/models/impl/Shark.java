package ru.mts.models.impl;

import ru.mts.models.Predator;

import java.math.BigDecimal;

public class Shark extends Predator {

    public Shark(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

    @Override
    public String toString() {
        return "[Shark] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character;
    }
}
