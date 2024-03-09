package ru.mts.services;

import ru.mts.models.enums.AnimalType;
import ru.mts.models.templates.AbstractAnimal;

import java.util.List;
import java.util.Map;

/**
 * Интерфейс, представляющий собой сервис для создания животных.
 */
public interface CreateAnimalService {

    /**
     * Метод для получения типа животных, которые будут сгенерированы.
     * @return Тип сгенерированных животных.
     */
    AnimalType getAnimalType();

    /**
     * Метод для изменения типа сгенерированных животных.
     * @param animalType Желаемый новый тип животных.
     */
    void setAnimalType(AnimalType animalType);

    /**
     * Метод создания животных.
     *
     * @param amount Количество животных, которое должно быть сгенерировано.
     * @throws IllegalArgumentException В случае, если желаемое число сгенерированных животных меньше или равно 0.
     * @return Словарь сгенерированных животных, в котором ключ - тип животного, значение - список созданных животных.
     */
    Map<String, List<AbstractAnimal>> createAnimals(int amount) throws IllegalArgumentException;
}
