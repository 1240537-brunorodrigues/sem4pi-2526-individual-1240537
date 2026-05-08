# US052 - Create an Airport

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of airports.

The implementation should include:

* authorization validation for the Backoffice Operator;
* validation of the selected air control area;
* validation of airport required data;
* validation of IATA code;
* validation of ICAO code;
* uniqueness verification of IATA and ICAO codes;
* validation of airport coordinates;
* validation of elevation;
* creation of an airport;
* persistence of the registered airport;
* support for registering airports through bootstrap.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterAirportUI`
* `RegisterAirportController`
* `RegisterAirportService`
* `RegisterAirportRequest`
* `AirportDTO`
* `BootstrapAirportLoader`
* `Airport`
* `IATACode`
* `ICAOCode`
* `Coordinate`
* `Country`
* `AirControlArea`
* `AreaCode`
* `User`
* `Permission`
* `AuthorizationService`
* `AirportRepository`
* `AirControlAreaRepository`
* `InMemoryAirportRepository`
* `InMemoryAirControlAreaRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an airport can be registered with valid data.
* Verify that an unauthorized user cannot register an airport.
* Verify that an airport cannot be registered without a name.
* Verify that an airport cannot be registered without a town.
* Verify that an airport cannot be registered without a country.
* Verify that an airport cannot be registered without an air control area.
* Verify that an airport cannot be registered for a non-existing air control area.
* Verify that an invalid IATA code is rejected.
* Verify that an invalid ICAO code is rejected.
* Verify that a duplicated IATA code is rejected.
* Verify that a duplicated ICAO code is rejected.
* Verify that invalid coordinates are rejected.
* Verify that a successfully registered airport is stored.
* Verify that failed registration does not change the repository.
* Verify that bootstrap registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful airport registration

* **Given** an authenticated Backoffice Operator
* **And** an existing air control area
* **And** valid airport data
* **When** the Backoffice Operator registers the airport
* **Then** the system should store the airport associated with that air control area

**Test 2:** Non-existing air control area

* **Given** an authenticated Backoffice Operator
* **And** airport data referencing a non-existing air control area
* **When** the Backoffice Operator registers the airport
* **Then** the system should reject the registration

**Test 3:** Duplicated IATA code

* **Given** an authenticated Backoffice Operator
* **And** an existing airport with a given IATA code
* **When** the Backoffice Operator registers another airport with the same IATA code
* **Then** the system should reject the registration

**Test 4:** Duplicated ICAO code

* **Given** an authenticated Backoffice Operator
* **And** an existing airport with a given ICAO code
* **When** the Backoffice Operator registers another airport with the same ICAO code
* **Then** the system should reject the registration

**Test 5:** Invalid coordinates

* **Given** an authenticated Backoffice Operator
* **And** airport data with invalid coordinates
* **When** the Backoffice Operator registers the airport
* **Then** the system should reject the registration

**Test 6:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register airports
* **When** the user tries to register an airport
* **Then** the system should deny access

**Test 7:** Bootstrap registration

* **Given** valid bootstrap airport data
* **When** the bootstrap process runs
* **Then** the default airports should be registered

---

### 4.5. Implementation Status

Not implemented yet.