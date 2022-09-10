package ru.netology.geo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {

    @BeforeAll
    public static void beforeTests() {
        System.out.println("Test of GeoService started");
    }

    @Test
    public void testByIp_whenIpStartsFrom172_thenReturnLocationMoscow() {
        String ip = "172.0.13.29";
        String expectedCity = "Moscow";
        Country expectedCountry = Country.RUSSIA;
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(ip);
        assertThat(expectedCity, is(equalTo(location.getCity())));
        assertThat(expectedCountry, is(equalTo(location.getCountry())));
    }

    @Test
    public void testByCoordinates_whenValidArguments_thenThrowRuntimeException() {
        double latitude = 55.755864;
        double longitude = 37.617698;
        GeoService geoService = new GeoServiceImpl();
        Assertions.assertThrowsExactly(RuntimeException.class, () -> geoService.byCoordinates(latitude, longitude));
    }

    @AfterAll
    public static void afterTests() {
        System.out.println("Test of GeoService completed");
    }
}
