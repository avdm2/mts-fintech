package ru.mts.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.mts.models.enums.AnimalType;
import ru.mts.services.CreateAnimalService;

@Configuration
public class CreateAnimalServiceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof CreateAnimalService)) {
            return bean;
        }

        switch ((int) (System.currentTimeMillis() % 4)) {
            case 0 -> ((CreateAnimalService) bean).setAnimalType(AnimalType.CAT);
            case 1 -> ((CreateAnimalService) bean).setAnimalType(AnimalType.DOG);
            case 2 -> ((CreateAnimalService) bean).setAnimalType(AnimalType.SHARK);
            case 3 -> ((CreateAnimalService) bean).setAnimalType(AnimalType.WOLF);
        }
        return bean;
    }
}
