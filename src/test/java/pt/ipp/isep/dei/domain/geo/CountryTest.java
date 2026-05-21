package pt.ipp.isep.dei.domain.geo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {

    @Test
    void shouldCreateValidCountry() {
        Country country = new Country("Portugal", "PT");

        assertEquals("Portugal", country.name());
        assertEquals("PT", country.code());
    }

    @Test
    void shouldNormalizeCountryCode() {
        Country country = new Country("Portugal", "pt");

        assertEquals("PT", country.code());
    }

    @Test
    void shouldTrimCountryNameAndCode() {
        Country country = new Country("  Portugal  ", "  pt  ");

        assertEquals("Portugal", country.name());
        assertEquals("PT", country.code());
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Country("   ", "PT"));
    }

    @Test
    void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Country(null, "PT"));
    }

    @Test
    void shouldRejectBlankCode() {
        assertThrows(IllegalArgumentException.class, () -> new Country("Portugal", "   "));
    }

    @Test
    void shouldRejectNullCode() {
        assertThrows(IllegalArgumentException.class, () -> new Country("Portugal", null));
    }

    @Test
    void shouldRejectCodeWithWrongLength() {
        assertThrows(IllegalArgumentException.class, () -> new Country("Portugal", "PRT"));
    }

    @Test
    void shouldRejectCodeWithNumbers() {
        assertThrows(IllegalArgumentException.class, () -> new Country("Portugal", "P1"));
    }

    @Test
    void countriesWithSameCodeShouldBeEqual() {
        Country country1 = new Country("Portugal", "PT");
        Country country2 = new Country("Portuguese Republic", "pt");

        assertEquals(country1, country2);
        assertEquals(country1.hashCode(), country2.hashCode());
    }
}