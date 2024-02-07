package ru.mts.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import ru.mts.models.enums.AnimalType;

import java.lang.reflect.Field;

@Configuration
public class CreateAnimalServiceBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (!(beanName.equals("createAnimalService"))) {
            return bean;
        }

        Field animalTypeField;
        try {
            animalTypeField = bean.getClass().getDeclaredField("animalType");
        } catch (NoSuchFieldException noSuchFieldException) {
            System.out.println("BPP Error");
            return bean;
        }

        animalTypeField.setAccessible(true);
        switch ((int) (System.currentTimeMillis() % 4)) {
            case 0 -> ReflectionUtils.setField(animalTypeField, bean, AnimalType.CAT);
            case 1 -> ReflectionUtils.setField(animalTypeField, bean, AnimalType.DOG);
            case 2 -> ReflectionUtils.setField(animalTypeField, bean, AnimalType.SHARK);
            case 3 -> ReflectionUtils.setField(animalTypeField, bean, AnimalType.WOLF);
        }

        System.out.println("ok");
        return bean;
    }
}
