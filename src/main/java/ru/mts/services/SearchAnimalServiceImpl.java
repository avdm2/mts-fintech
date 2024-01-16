package ru.mts.services;

import ru.mts.models.AbstractAnimal;

import java.time.LocalDate;

/**
 * Сервис для работы с массивом животных.
 */
public class SearchAnimalServiceImpl implements SearchAnimalService {

    private final AbstractAnimal[] data;

    public SearchAnimalServiceImpl(AbstractAnimal[] data) {
        this.data = data;
    }

    @Override
    public String[] findLeapYearNames() {
        String[] result = new String[data.length];
        for (int i = 0; i < data.length; ++i) {
            AbstractAnimal currentAnimal = data[i];
            if (currentAnimal.getBirthDate().isLeapYear()) {
                result[i] = currentAnimal.getName();
            }
        }

        return result;
    }

    @Override
    public AbstractAnimal[] findOlderAnimal(int n) {
        AbstractAnimal[] result = new AbstractAnimal[data.length];
        for (int i = 0; i < data.length; ++i) {
            AbstractAnimal currentAnimal = data[i];
            int age = LocalDate.ofEpochDay((LocalDate.now().toEpochDay() - currentAnimal.getBirthDate().toEpochDay())).getYear();
            if (age > n) {
                result[i] = currentAnimal;
            }
        }

        return result;
    }

    @Override
    public void findDuplicate() {
        int duplicates = 0;
        for (int i = 0; i < data.length - 1; ++i) {
            AbstractAnimal first = data[i];

            for (int j = i + 1; j < data.length; ++j) {
                AbstractAnimal second = data[j];

                if (first.equals(second)) {
                    System.out.println("Duplicate: " + first);
                    ++duplicates;
                }
            }
        }

        if (duplicates == 0) {
            System.out.println("No duplicates.");
        }
    }
}
