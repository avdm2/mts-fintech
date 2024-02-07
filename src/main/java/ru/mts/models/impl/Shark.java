package ru.mts.models.impl;

import ru.mts.models.templates.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Класс, описывающий акулу.
 */
public class Shark extends Predator {

    /**
     * Создание новой акулы.
     * @param breed Порода акулы.
     * @param name Имя акулы.
     * @param cost Стоимость акулы в магазине.
     * @param birthDate Дата рождения акулы.
     */
    public Shark(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, birthDate);
    }

    @Override
    public String toString() {
        return "[Shark] Breed=" + breed +
                "; Name=" + name +
                "; Cost=" + cost +
                "; Character=" + character +
                "; BirthDate=" + birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
