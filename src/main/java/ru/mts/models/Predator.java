package ru.mts.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс, представляющий собой хищное животное.
 */
public abstract class Predator extends AbstractAnimal {

    /**
     * Создание нового хищника с агрессивным характером.
     * @param breed Порода животного.
     * @param name Имя животного.
     * @param cost Цена животного в магазине.
     * @param birthDate Дата рождения животного.
     */
    public Predator(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, "aggressive", birthDate);
    }
}
