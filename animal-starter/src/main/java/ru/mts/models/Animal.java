package ru.mts.models;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс, представляющий некое животное. Содержит методы, которые могут быть полезны при работе с
 * реализующим интерфейс объектами.
 */
public interface Animal {

    /**
     * Метод получения породы животного.
     * @return Порода животного.
     */
    String getBreed();

    /**
     * Метод получения клички животного.
     * @return Кличка животного.
     */
    String getName();

    /**
     * Метод получения цены в магазине на животное.
     * @return Цена животного в магазине.
     */
    BigDecimal getCost();

    /**
     * Метод получения характера животного.
     * @return Характер животного.
     */
    String getCharacter();

    /**
     * Метод получения дня рождения животного в формате dd-MM-yyyy.
     * @return Дата рождения животного.
     */
    LocalDate getBirthDate();
}
