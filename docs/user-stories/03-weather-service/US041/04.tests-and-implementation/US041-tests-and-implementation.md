# US041 - Register Weather Data

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of weather data for a specific air control area.

The implementation should include:

* authorization validation for the Weather Person;
* lookup of the selected air control area;
* validation of required weather data;
* validation of wind direction;
* validation of wind speed;
* creation of a weather data record;
* persistence of the registered weather data.

The first implementation may use simplified weather data, mainly focused on wind direction and wind speed.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterWeatherDataUI`
* `RegisterWeatherDataController`
* `RegisterWeatherDataService`
* `RegisterWeatherDataRequest`
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

* Verify that weather data can be registered with valid data.
* Verify that weather data cannot be registered by an unauthorized user.
* Verify that weather data cannot be registered for a non-existing air control area.
* Verify that weather data cannot be registered without a date/time.
* Verify that wind speed cannot be negative.
* Verify that wind direction must be a valid angle.
* Verify that weather data is stored after successful registration.
* Verify that no weather data is stored when validation fails.
* Verify that manually registered weather data has the correct source.
* Verify that registered weather data is associated with the correct air control area.

---

### 4.4. Acceptance Tests

**Test 1:** Successful weather data registration

* **Given** an authenticated Weather Person
* **And** an existing air control area
* **And** valid weather data
* **When** the Weather Person registers the weather data
* **Then** the system should store the weather data

**Test 2:** Non-existing air control area

* **Given** an authenticated Weather Person
* **And** weather data for a non-existing air control area
* **When** the Weather Person registers the weather data
* **Then** the system should reject the registration

**Test 3:** Invalid wind speed

* **Given** an authenticated Weather Person
* **And** weather data with a negative wind speed
* **When** the Weather Person registers the weather data
* **Then** the system should reject the registration

**Test 4:** Invalid wind direction

* **Given** an authenticated Weather Person
* **And** weather data with an invalid wind direction
* **When** the Weather Person registers the weather data
* **Then** the system should reject the registration

**Test 5:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register weather data
* **When** the user tries to register weather data
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.