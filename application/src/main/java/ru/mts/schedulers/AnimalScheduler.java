package ru.mts.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.exceptions.EmptyResultException;
import ru.mts.exceptions.IllegalAgeException;
import ru.mts.exceptions.InvalidListSizeException;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.services.AnimalsRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class AnimalScheduler {

    AnimalsRepository animalsRepository;

    public AnimalScheduler(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 60_000)
    public void invoke() {
        System.out.println("\nfindLeapYearNames()");
        try {
            Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
            for (Map.Entry<String, LocalDate> entry : leapYearNames.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        } catch (EmptyResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("unknown exception");
        }

        System.out.println("\nfindOlderAnimal(5)");
        try {
            Map<AbstractAnimal, Integer> findOlderAnimals = animalsRepository.findOlderAnimal(5);
            for (Map.Entry<AbstractAnimal, Integer> entry : findOlderAnimals.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue() + " year(s)");
            }
        } catch (IllegalAgeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("unknown exception");
        }

        System.out.println("\nprintDuplicate()");
        try {
            animalsRepository.printDuplicate();
        } catch (EmptyResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("unknown exception");
        }

        System.out.println("\nfindAverageAge()");
        System.out.println(animalsRepository.findAverageAge());

        System.out.println("\nfindOldAndExpensive");
        try {
            List<AbstractAnimal> findOldAndExpensive = animalsRepository.findOldAndExpensive();
            for (AbstractAnimal animal : findOldAndExpensive) {
                System.out.println(animal);
            }
        } catch (EmptyResultException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("unknown exception");
        }

        System.out.println("\nfindMinCostAnimals");
        try {
            List<AbstractAnimal> findMinCostAnimals = animalsRepository.findMinCostAnimals();
            for (AbstractAnimal animal : findMinCostAnimals) {
                System.out.println(animal);
            }
        } catch (InvalidListSizeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("unknown exception");
        }
    }
}
