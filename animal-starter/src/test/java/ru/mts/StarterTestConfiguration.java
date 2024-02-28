package ru.mts;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.models.enums.AnimalType;
import ru.mts.services.CreateAnimalServiceImpl;

@TestConfiguration
public class StarterTestConfiguration {

    @Bean
    public CreateAnimalServiceImpl createAnimalService() {
        CreateAnimalServiceImpl createAnimalService = Mockito.mock(CreateAnimalServiceImpl.class);
        Mockito.when(createAnimalService.getAnimalType()).thenReturn(AnimalType.CAT);
        return createAnimalService;
    }
}
