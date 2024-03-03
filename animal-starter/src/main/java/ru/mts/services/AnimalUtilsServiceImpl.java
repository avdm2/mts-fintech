package ru.mts.services;

import org.springframework.stereotype.Service;
import ru.mts.models.templates.AbstractAnimal;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AnimalUtilsServiceImpl implements AnimalUtilsService {
    @Override
    public int getAnimalAge(AbstractAnimal animal) {
        return Period.between(animal.getBirthDate(), LocalDate.now()).getYears();
    }
}
