package ru.mts.models.impl;

import ru.mts.models.templates.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий волка.
 */
public class Wolf extends Predator {

    /**
     * Создание нового волка.
     * @param breed Порода волка.
     * @param name Имя волка.
     * @param cost Стоимость волка в магазине.
     * @param birthDate Дата рождения волка.
     */
    public Wolf(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "[Wolf] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character +
                "; BirthDate=" + birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
