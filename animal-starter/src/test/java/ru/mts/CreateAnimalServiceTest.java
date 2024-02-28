package ru.mts;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.mts.config.StarterConfig;
import ru.mts.models.templates.AbstractAnimal;
import ru.mts.services.CreateAnimalServiceImpl;

import java.util.List;
import java.util.Map;

@SpringBootTest(classes = {CreateAnimalServiceImpl.class, StarterConfig.class})
@ActiveProfiles(value = "test")
public class CreateAnimalServiceTest {

    @Autowired
    private CreateAnimalServiceImpl createAnimalService;
    @ParameterizedTest
    @ValueSource(ints = {-10, 0})
    @DisplayName("Проверка создания некорректного количества животных - {0}")
    void testCreateAnimalsWithIncorrectAmount(int amount) {
        assertThrows(IllegalArgumentException.class, () -> createAnimalService.createAnimals(amount));
    }

    @DisplayName("Проверка создания корректного количества животных")
    void testCreateAnimals() {
        // Протестировать корректное создание животных не получится из-за особенностей программы. Для этого необходимо
        // переделывать логику всех фабрик и делать из них бины, а затем с помощью моков изменять возвращаемые значения.
    }

    @DisplayName("Проверка создания животного с именем из application-test.yml")
    @Test
    void testCreationWithNameFromYml() {
        Map<String, List<AbstractAnimal>> animals = createAnimalService.createAnimals(1);
        List<String> names = List.of("catname1", "dogname1", "sharkname1", "wolfname1");
        assertTrue(names.contains(animals.get(createAnimalService.getAnimalType().toString()).get(0).getName()));
    }
}
