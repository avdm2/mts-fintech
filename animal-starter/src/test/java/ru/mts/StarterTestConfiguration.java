package ru.mts;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mts.services.CreateAnimalServiceImpl;

@TestConfiguration
public class StarterTestConfiguration {

    @Bean
    public CreateAnimalServiceImpl createAnimalService() {
        return Mockito.mock(CreateAnimalServiceImpl.class);
    }
}
