package ru.mts.models.impl;

import ru.mts.models.templates.Pet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий кота.
 */
public class Cat extends Pet {

    /**
     * Создание нового кота.
     * @param breed Порода кота.
     * @param name Имя кота.
     * @param cost Стоимость кота в магазине.
     * @param birthDate Дата рождения кота.
     */
    public Cat(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "[Cat] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character +
                "; BirthDate=" + birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
