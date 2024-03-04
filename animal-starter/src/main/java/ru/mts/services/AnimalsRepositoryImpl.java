package ru.mts.services;

import org.springframework.stereotype.Repository;
import ru.mts.models.enums.AnimalType;
import ru.mts.models.templates.AbstractAnimal;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public int getAnimalAge(AbstractAnimal animal) {
        return Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        return animalsList.stream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.getClass().getSimpleName() + " " + animal.getName(), AbstractAnimal::getBirthDate));
    }

    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int n) {
        Map<AbstractAnimal, Integer> result = animalsList.stream()
                .filter(animal -> getAnimalAge(animal) > n)
                .collect(Collectors.toMap(entry -> entry, this::getAnimalAge));

        if (!result.isEmpty()) {
            return result;
        }

        AbstractAnimal oldestAnimal = animalsList.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getBirthDate))
                .toList().get(0);
        return Map.of(oldestAnimal, getAnimalAge(oldestAnimal));
    }

    @Override
    public Map<String, List<AbstractAnimal>> findDuplicate() {
        return animalsList.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> Map.entry(entry.getKey().getClass().getSimpleName(), entry.getKey()))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    @Override
    public void printDuplicate() {
        Map<String, List<AbstractAnimal>> duplicates = findDuplicate();
        if (duplicates.isEmpty()) {
            System.out.println("No duplicates");
        }

        for (Map.Entry<String, List<AbstractAnimal>> entry : duplicates.entrySet()) {
            System.out.println(entry.getKey());
            for (AbstractAnimal animal : entry.getValue()) {
                System.out.println("   - " + animal);
            }
        }
    }

    @Override
    public double findAverageAge() {
        return animalsList.stream()
                .mapToDouble(this::getAnimalAge)
                .average()
                .orElse(-1);
    }

    @Override
    public List<AbstractAnimal> findOldAndExpensive() {
        double avgCost = animalsList.stream()
                .mapToDouble(animal -> animal.getCost().intValue())
                .average()
                .orElse(-1);
        return animalsList.stream()
                .filter(animal -> getAnimalAge(animal) > 5 && animal.getCost().doubleValue() > avgCost)
                .sorted(Comparator.comparing(AbstractAnimal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<AbstractAnimal> findMinCostAnimals() {
        return animalsList.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost))
                .limit(3)
                .sorted((first, second) -> -first.getName().compareTo(second.getName()))
                .collect(Collectors.toList());
    }
}
