package ru.mts;

import ru.mts.services.impl.CreateAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl createAnimalService = new CreateAnimalServiceImpl();

        System.out.println("====================createAnimals()====================");
        createAnimalService.createAnimals();

        System.out.println("====================createAnimals(5)====================");
        createAnimalService.createAnimals(5);
    }
}