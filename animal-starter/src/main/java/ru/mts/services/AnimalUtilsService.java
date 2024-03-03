package ru.mts.services;

import ru.mts.models.templates.AbstractAnimal;

public interface AnimalUtilsService {

    /**
     * Метод, вычисляющий возраст животного.
     * @param animal Животное, возраст которого требуется вычислить.
     * @return Возраст переданного в метод животного.
     */
    int getAnimalAge(AbstractAnimal animal);
}
