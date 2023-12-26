package ru.mts.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Некоторое абстрактное животное.
 */
public abstract class AbstractAnimal implements Animal {

    protected String breed;
    protected String name;
    protected BigDecimal cost;
    protected String character;
    protected LocalDate birthDate;

    /**
     * Создание абстрактного животного.
     * @param breed Порода животного.
     * @param name Имя животного.
     * @param cost Цена животного в магазине.
     * @param character Характер животного.
     * @param birthDate Дата рождения животного.
     */
    public AbstractAnimal(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_EVEN);
        this.character = character;
        this.birthDate = birthDate;
    }

    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getCharacter() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractAnimal castedObj = (AbstractAnimal) obj;
        return breed.equals(castedObj.breed) &&
                name.equals(castedObj.name) &&
                cost.equals(castedObj.cost) &&
                character.equals(castedObj.character) &&
                birthDate.equals(castedObj.birthDate);
    }
}
