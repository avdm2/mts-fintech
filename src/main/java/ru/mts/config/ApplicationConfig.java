package ru.mts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.bpp.CreateAnimalServiceBeanPostProcessor;
import ru.mts.services.AnimalsRepository;
import ru.mts.services.AnimalsRepositoryImpl;
import ru.mts.services.CreateAnimalService;
import ru.mts.services.CreateAnimalServiceImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public CreateAnimalServiceBeanPostProcessor createAnimalServiceBeanPostProcessor() {
        return new CreateAnimalServiceBeanPostProcessor();
    }

    @Bean
    public AnimalsRepository animalsRepository() {
        return new AnimalsRepositoryImpl(createAnimalService());
    }
}
