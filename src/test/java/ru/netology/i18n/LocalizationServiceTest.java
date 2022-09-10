package ru.netology.i18n;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LocalizationServiceTest {

    @BeforeAll
    public static void beforeTests() {
        System.out.println("Test of LocalizationService started");
    }

    @Test
    public void testLocale_whenRussiaCountry_thenReturnWelcomeInRussian() {
        Country country = Country.RUSSIA;
        String expected = "Добро пожаловать";
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(country);
        assertThat(expected, is(equalTo(actual)));
    }

    @AfterAll
    public static void afterTests() {
        System.out.println("Test of LocalizationService completed");
    }
}
