package ru.mts.services;

import org.springframework.stereotype.Repository;
import ru.mts.models.enums.AnimalType;
import ru.mts.models.templates.AbstractAnimal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Сервис для работы с массивом животных.
 */
@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {

    private final CreateAnimalService createAnimalService;

    private final AnimalType animalType;
    private List<AbstractAnimal> animalsList;

    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.createAnimalService = createAnimalService;
        this.animalType = createAnimalService.getAnimalType();
    }

    @PostConstruct
    public void initData() {
        Map<String, List<AbstractAnimal>> animalsData = createAnimalService.createAnimals(10);
        animalsList = animalsData.get(animalType.toString());
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> result = new HashMap<>();
        for (AbstractAnimal currentAnimal : animalsList) {
            if (currentAnimal.getBirthDate().isLeapYear()) {
                result.put(animalType.toString() + " " + currentAnimal.getName(), currentAnimal.getBirthDate());
            }
        }

        return result;
    }

    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int n) {
        Map<AbstractAnimal, Integer> result = new HashMap<>();
        AbstractAnimal oldestAnimal = null;
        int maxAge = 0;
        for (AbstractAnimal currentAnimal : animalsList) {
            int age = Period.between(currentAnimal.getBirthDate(), LocalDate.now()).getYears();
            if (age > maxAge) {
                maxAge = age;
                oldestAnimal = currentAnimal;
            }
            if (age > n) {
                result.put(currentAnimal, age);
            }
        }

        if (result.isEmpty()) {
            result.put(oldestAnimal, maxAge);
        }
        return result;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        Set<AbstractAnimal> animalSet = new HashSet<>(animalsList);
        return Map.of(animalType.toString(), animalsList.size() - animalSet.size());
    }

    @Override
    public void printDuplicate() {
        System.out.println(animalType.toString() + "=" + findDuplicate().get(animalType.toString()));
    }
}
