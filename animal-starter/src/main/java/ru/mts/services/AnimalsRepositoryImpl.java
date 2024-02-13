package ru.mts.services;

import org.springframework.stereotype.Repository;
import ru.mts.models.templates.AbstractAnimal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для работы с массивом животных.
 */
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final CreateAnimalService createAnimalService;
    private AbstractAnimal[] data;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
    }

    @PostConstruct
    public void initData() {
        data = createAnimalService.createAnimals(10);
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
            int age = Period.between(currentAnimal.getBirthDate(), LocalDate.now()).getYears();
            if (age > n) {
                result[i] = currentAnimal;
            }
        }

        return result;
    }

    @Override
    public Set<AbstractAnimal> findDuplicate() {
        Set<AbstractAnimal> set = new HashSet<>();
        return Arrays.stream(data)
                .filter(animal -> !set.add(animal))
                .collect(Collectors.toSet());
    }

    @Override
    public void printDuplicate() {
        System.out.println(findDuplicate());
    }
}
