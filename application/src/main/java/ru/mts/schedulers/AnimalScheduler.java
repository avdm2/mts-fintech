package ru.mts.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mts.services.AnimalsRepository;

import java.util.Arrays;

@Component
public class AnimalScheduler {

    AnimalsRepository animalsRepository;

    public AnimalScheduler(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Scheduled(fixedRate = 60_000)
    public void invoke() {
        System.out.println("\nfindLeapYearNames()");
        System.out.println(Arrays.toString(animalsRepository.findLeapYearNames()));

        System.out.println("\nfindOlderAnimal(5)");
        Arrays.stream(animalsRepository.findOlderAnimal(5))
                .forEach(System.out::println);

        System.out.println("\nprintDuplicate()");
        animalsRepository.printDuplicate();
    }
}
