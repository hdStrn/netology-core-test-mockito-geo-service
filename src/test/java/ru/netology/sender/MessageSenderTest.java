package ru.netology.sender;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MessageSenderTest {

    @BeforeAll
    public static void beforeTests() {
        System.out.println("Test of MessageSender started");
    }

    @Test
    public void testSend_whenIpFromRussia_thenReturnWelcomeInRussian() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.13.89"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Добро пожаловать";
        String actual = messageSender.send(Map.of("x-real-ip", "172.0.13.89"));
        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    public void testSend_whenIpFromUS_thenReturnWelcomeInEnglish() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.0.1.2"))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Welcome";
        String actual = messageSender.send(Map.of("x-real-ip", "96.0.1.2"));
        assertThat(expected, is(equalTo(actual)));
    }

    @AfterAll
    public static void afterTests() {
        System.out.println("\nTest of MessageSender completed");
    }
}
