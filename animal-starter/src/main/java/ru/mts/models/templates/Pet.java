package ru.mts.models.templates;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Абстрактный класс, представляющий собой домашнее животное.
 */
public abstract class Pet extends AbstractAnimal {

    /**
     * Создание нового домашнего животного с дружелюбным характером.
     * @param breed Порода животного.
     * @param name Имя животного.
     * @param cost Цена животного в магазине.
     * @param birthDate Дата рождения животного.
     */
    protected Pet(String breed, String name, BigDecimal cost, LocalDate birthDate) {
        super(breed, name, cost, "friendly", birthDate);
    }
}
