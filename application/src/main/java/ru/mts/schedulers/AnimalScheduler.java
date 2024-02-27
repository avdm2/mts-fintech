package ru.mts.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.services.AnimalsRepository;

import java.time.LocalDate;
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
        Map<String, LocalDate> leapYearNames = animalsRepository.findLeapYearNames();
        for (var entry : leapYearNames.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        System.out.println("\nfindOlderAnimal(5)");
        Map<AbstractAnimal, Integer> findOlderAnimals = animalsRepository.findOlderAnimal(5);
        for (var entry : findOlderAnimals.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + " year(s)");
        }

        System.out.println("\nprintDuplicate()");
        animalsRepository.printDuplicate();
    }
}
