package ru.mts.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mts.services.CreateAnimalService;
import ru.mts.services.CreateAnimalServiceImpl;

@Configuration
@EnableConfigurationProperties(StarterConfigurationProperties.class)
public class StarterConfig {

    StarterConfigurationProperties starterConfigurationProperties;

    public StarterConfig(StarterConfigurationProperties starterConfigurationProperties) {
        this.starterConfigurationProperties = starterConfigurationProperties;
    }

    @Bean
    @Scope("prototype")
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl(starterConfigurationProperties);
    }
}
