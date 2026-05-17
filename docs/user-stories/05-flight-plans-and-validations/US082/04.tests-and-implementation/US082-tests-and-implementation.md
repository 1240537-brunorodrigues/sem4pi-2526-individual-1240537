# US082 - Insert Weather Data in a Flight

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the insertion of weather data into a Pilot's flight plan.

The implementation should include:

* authorization validation for the Pilot;
* lookup of the selected flight plan;
* validation that the flight plan belongs to the authenticated Pilot;
* validation of weather data;
* association of weather data with the flight plan;
* verification of whether the flight plan had previously been tested;
* invalidation of previous test results when applicable;
* marking the flight plan as requiring a new test when previous test results are voided;
* persistence of the updated flight plan.

The previous test result should be marked as void rather than deleted.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `InsertWeatherDataInFlightUI`
* `InsertWeatherDataInFlightController`
* `InsertWeatherDataInFlightService`
* `InsertWeatherDataRequest`
* `WeatherDataInputDTO`
* `FlightPlanDTO`
* `FlightPlan`
* `WeatherData`
* `AirControlArea`
* `FlightPlanTest`
* `TestStatus`
* `ValidationResult`
* `FlightPlanWeatherUpdatePolicy`
* `Pilot`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `FlightPlanRepository`
* `WeatherDataRepository`
* `InMemoryFlightPlanRepository`
* `InMemoryWeatherDataRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Pilot can add valid weather data to their flight plan.
* Verify that an unauthorized user cannot add weather data to a flight plan.
* Verify that weather data cannot be added to a non-existing flight plan.
* Verify that a Pilot cannot add weather data to another Pilot's flight plan.
* Verify that invalid weather data is rejected.
* Verify that valid weather data is associated with the flight plan.
* Verify that adding weather data to an untested flight plan does not void any test.
* Verify that adding weather data to a previously tested flight plan marks the previous test as void.
* Verify that after weather data is added to a previously tested flight plan, the flight plan requires a new test.
* Verify that voided test results are preserved and not deleted.
* Verify that failed weather data insertion does not modify the flight plan.
* Verify that failed weather data insertion does not void existing test results.

---

### 4.4. Acceptance Tests

**Test 1:** Add weather data to untested flight plan

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to that Pilot
* **And** the flight plan has not been previously tested
* **When** the Pilot adds valid weather data to the flight plan
* **Then** the weather data should be associated with the flight plan
* **And** no test result should be voided

**Test 2:** Add weather data to previously tested flight plan

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to that Pilot
* **And** the flight plan has been previously tested
* **When** the Pilot adds valid weather data to the flight plan
* **Then** the weather data should be associated with the flight plan
* **And** the previous test result should be marked as void
* **And** the flight plan should require a new test

**Test 3:** Flight plan belongs to another Pilot

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to another Pilot
* **When** the Pilot tries to add weather data to that flight plan
* **Then** the system should reject the operation

**Test 4:** Invalid weather data

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to that Pilot
* **And** invalid weather data
* **When** the Pilot tries to add the weather data
* **Then** the system should reject the operation

**Test 5:** Non-existing flight plan

* **Given** an authenticated Pilot
* **And** a non-existing flight plan
* **When** the Pilot tries to add weather data
* **Then** the system should display a flight plan not found message

**Test 6:** Failed operation does not void previous test

* **Given** an authenticated Pilot
* **And** an existing previously tested flight plan
* **And** invalid weather data
* **When** the Pilot tries to add the weather data
* **Then** the previous test result should remain valid
* **And** the flight plan should remain unchanged

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to insert weather data in a flight
* **When** the user tries to add weather data
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.