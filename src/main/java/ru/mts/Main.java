package ru.mts;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mts.config.ApplicationConfig;
import ru.mts.services.AnimalsRepository;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        AnimalsRepository animalsRepository = ctx.getBean(AnimalsRepository.class);

        System.out.println("\nfindLeapYearNames()");
        System.out.println(Arrays.toString(animalsRepository.findLeapYearNames()));

        System.out.println("\nfindOlderAnimal(5)");
        Arrays.stream(animalsRepository.findOlderAnimal(5))
                .forEach(System.out::println);

        System.out.println("\nprintDuplicate()");
        animalsRepository.printDuplicate();
    }
}