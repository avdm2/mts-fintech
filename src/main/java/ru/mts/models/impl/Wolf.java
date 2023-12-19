package ru.mts.models.impl;

import ru.mts.models.Predator;

import java.math.BigDecimal;

public class Wolf extends Predator {

    public Wolf(String breed, String name, BigDecimal cost) {
        super(breed, name, cost);
    }

    @Override
    public String toString() {
        return "[Wolf] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character;
    }
}
