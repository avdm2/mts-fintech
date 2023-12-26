package ru.mts.services.utils;

import ru.mts.models.AbstractAnimal;
import ru.mts.services.SearchAnimalServiceImpl;

/**
 * Сервис, используемый для тестирования массива животных.
 */
public class AbstractAnimalTesterImpl implements AbstractAnimalTester {

    @Override
    public void test(AbstractAnimal[] data) {
        System.out.println("======= DATA =======");
        for (AbstractAnimal animal : data) {
            System.out.println(animal);
        }
        System.out.println();

        SearchAnimalServiceImpl service = new SearchAnimalServiceImpl(data);
        String[] findLeapYearNames;
        AbstractAnimal[] findOlderAnimal;
        int ageToSearch = 5;

        try {
            findLeapYearNames = service.findLeapYearNames();
            findOlderAnimal = service.findOlderAnimal(ageToSearch);

            System.out.println("======= findDuplicates =======");
            service.findDuplicate();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        int nullCounter = 0;

        System.out.println("======= findLeapYearNames =======");
        for (String name : findLeapYearNames) {
            if (name != null) {
                System.out.println(name);
            } else {
                nullCounter++;
            }
        }

        if (nullCounter == findLeapYearNames.length) {
            System.out.println("No animals that have been born on a leap year.");
        }

        System.out.println("======= findOlderAnimal =======");
        nullCounter = 0;
        for (AbstractAnimal animal : findOlderAnimal) {
            if (animal != null) {
                System.out.println(animal);
            } else {
                nullCounter++;
            }
        }

        if (nullCounter == findOlderAnimal.length) {
            System.out.println("No animals are older than " + ageToSearch + " years old.");
        }

        System.out.println();
    }
}
