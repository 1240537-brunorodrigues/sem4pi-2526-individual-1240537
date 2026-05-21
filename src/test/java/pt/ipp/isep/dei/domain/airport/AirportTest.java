package pt.ipp.isep.dei.domain.airport;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.domain.aircontrol.AirControlArea;
import pt.ipp.isep.dei.domain.aircontrol.AreaCode;
import pt.ipp.isep.dei.domain.geo.Coordinate;
import pt.ipp.isep.dei.domain.geo.Country;
import pt.ipp.isep.dei.domain.geo.GeographicBoundary;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTest {

    private AirControlArea validAirControlArea() {
        return new AirControlArea(
                new AreaCode("LPPC"),
                "Lisbon FIR",
                new GeographicBoundary(List.of(
                        new Coordinate(41.0, -8.0),
                        new Coordinate(42.0, -8.0),
                        new Coordinate(42.0, -7.0)
                ))
        );
    }

    private Airport validAirport() {
        return new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        );
    }

    @Test
    void shouldCreateValidAirport() {
        Airport airport = validAirport();

        assertEquals("Francisco Sá Carneiro Airport", airport.name());
        assertEquals("Porto", airport.town());
        assertEquals(new Country("Portugal", "PT"), airport.country());
        assertEquals(69, airport.elevation());
        assertEquals(new IATACode("OPO"), airport.iataCode());
        assertEquals(new ICAOCode("LPPR"), airport.icaoCode());
        assertEquals(new Coordinate(41.2481, -8.6814), airport.coordinate());
        assertEquals(new AreaCode("LPPC"), airport.airControlArea().code());
    }

    @Test
    void shouldTrimNameAndTown() {
        Airport airport = new Airport(
                "  Francisco Sá Carneiro Airport  ",
                "  Porto  ",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        );

        assertEquals("Francisco Sá Carneiro Airport", airport.name());
        assertEquals("Porto", airport.town());
    }

    @Test
    void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "   ",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectBlankTown() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "   ",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectNullCountry() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                null,
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectNegativeElevation() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                -1,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectNullIATACode() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                null,
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectNullICAOCode() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                null,
                new Coordinate(41.2481, -8.6814),
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectNullCoordinate() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                null,
                validAirControlArea()
        ));
    }

    @Test
    void shouldRejectNullAirControlArea() {
        assertThrows(IllegalArgumentException.class, () -> new Airport(
                "Francisco Sá Carneiro Airport",
                "Porto",
                new Country("Portugal", "PT"),
                69,
                new IATACode("OPO"),
                new ICAOCode("LPPR"),
                new Coordinate(41.2481, -8.6814),
                null
        ));
    }

    @Test
    void airportsWithSameIATACodeShouldBeEqual() {
        Airport airport1 = validAirport();

        Airport airport2 = new Airport(
                "Different Airport",
                "Different Town",
                new Country("Portugal", "PT"),
                100,
                new IATACode("OPO"),
                new ICAOCode("LPPP"),
                new Coordinate(40.0, -8.0),
                validAirControlArea()
        );

        assertEquals(airport1, airport2);
    }

    @Test
    void airportsWithSameICAOCodeShouldBeEqual() {
        Airport airport1 = validAirport();

        Airport airport2 = new Airport(
                "Different Airport",
                "Different Town",
                new Country("Portugal", "PT"),
                100,
                new IATACode("LIS"),
                new ICAOCode("LPPR"),
                new Coordinate(40.0, -8.0),
                validAirControlArea()
        );

        assertEquals(airport1, airport2);
    }
}