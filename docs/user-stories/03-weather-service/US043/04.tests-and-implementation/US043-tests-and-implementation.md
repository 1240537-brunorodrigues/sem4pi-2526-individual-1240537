# US043 - Consult Weather Data

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the consultation of weather data by air control area and date.

The implementation should include:

* authorization validation;
* validation of the selected air control area;
* validation of the provided date;
* retrieval of weather data by air control area and date;
* mapping of weather data to DTOs;
* presentation of the results;
* handling of empty results.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ConsultWeatherDataUI`
* `ConsultWeatherDataController`
* `ConsultWeatherDataService`
* `ConsultWeatherDataRequest`
* `WeatherDataDTO`
* `WeatherData`
* `Wind`
* `WeatherSource`
* `AirControlArea`
* `User`
* `Permission`
* `AuthorizationService`
* `AirControlAreaRepository`
* `WeatherDataRepository`
* `InMemoryAirControlAreaRepository`
* `InMemoryWeatherDataRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Weather Person can consult weather data.
* Verify that an authorized Pilot can consult weather data.
* Verify that an authorized Flight Control Operator can consult weather data.
* Verify that an unauthorized user cannot consult weather data.
* Verify that consultation fails for a non-existing air control area.
* Verify that consultation returns weather data for an existing area and date.
* Verify that consultation returns an empty result when no weather data exists.
* Verify that the returned data includes wind direction and wind speed.
* Verify that the returned data includes source information when available.
* Verify that consulting weather data does not modify stored weather data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful weather data consultation

* **Given** an authenticated authorized user
* **And** an existing air control area
* **And** weather data registered for the selected date
* **When** the user consults weather data for that area and date
* **Then** the system should display the matching weather data

**Test 2:** No weather data found

* **Given** an authenticated authorized user
* **And** an existing air control area
* **And** no weather data exists for the selected date
* **When** the user consults weather data
* **Then** the system should display a no weather data found message

**Test 3:** Non-existing air control area

* **Given** an authenticated authorized user
* **And** a non-existing air control area code
* **When** the user consults weather data
* **Then** the system should display an area not found message

**Test 4:** Unauthorized consultation attempt

* **Given** an authenticated user without permission to consult weather data
* **When** the user tries to consult weather data
* **Then** the system should deny access

**Test 5:** Read-only operation

* **Given** existing weather data
* **When** an authorized user consults weather data
* **Then** the stored weather data should remain unchanged

---

### 4.5. Implementation Status

Not implemented yet.