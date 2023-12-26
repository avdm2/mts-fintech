package ru.mts.models.impl;

import ru.mts.models.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий собаку.
 */
public class Dog extends Pet {

    /**
     * Создание новой собаки.
     * @param breed Порода собаки.
     * @param name Имя собаки.
     * @param cost Стоимость собаки в магазине.
     * @param birthDate Дата рождения собаки.
     */
    public Dog(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "[Dog] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character +
                "; BirthDate=" + birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
