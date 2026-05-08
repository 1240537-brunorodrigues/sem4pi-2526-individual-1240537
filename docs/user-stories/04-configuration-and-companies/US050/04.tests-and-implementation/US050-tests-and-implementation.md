# US050 - Register an Air Control Area

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of air control areas.

The implementation should include:

* authorization validation for the Backoffice Operator;
* validation of the air control area code;
* uniqueness verification of the area code;
* validation of geographic boundaries;
* creation of an air control area;
* persistence of the registered air control area;
* support for registering air control areas through bootstrap.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterAirControlAreaUI`
* `RegisterAirControlAreaController`
* `RegisterAirControlAreaService`
* `RegisterAirControlAreaRequest`
* `AirControlAreaDTO`
* `BootstrapAirControlAreaLoader`
* `AirControlArea`
* `AreaCode`
* `GeographicBoundary`
* `Coordinate`
* `User`
* `Permission`
* `AuthorizationService`
* `AirControlAreaRepository`
* `InMemoryAirControlAreaRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an air control area can be registered with valid data.
* Verify that an unauthorized user cannot register an air control area.
* Verify that an air control area cannot be registered without a code.
* Verify that an air control area cannot be registered with a duplicated code.
* Verify that an air control area cannot be registered without geographic boundaries.
* Verify that invalid coordinates are rejected.
* Verify that invalid geographic boundaries are rejected.
* Verify that a successfully registered air control area is stored.
* Verify that failed registration does not change the repository.
* Verify that bootstrap registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful air control area registration

* **Given** an authenticated Backoffice Operator
* **And** valid air control area data
* **When** the Backoffice Operator registers the air control area
* **Then** the system should store the air control area

**Test 2:** Duplicate area code

* **Given** an authenticated Backoffice Operator
* **And** an existing air control area with a given code
* **When** the Backoffice Operator registers another air control area with the same code
* **Then** the system should reject the registration

**Test 3:** Invalid geographic boundaries

* **Given** an authenticated Backoffice Operator
* **And** air control area data with invalid geographic boundaries
* **When** the Backoffice Operator registers the air control area
* **Then** the system should reject the registration

**Test 4:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register air control areas
* **When** the user tries to register an air control area
* **Then** the system should deny access

**Test 5:** Bootstrap registration

* **Given** valid bootstrap air control area data
* **When** the bootstrap process runs
* **Then** the default air control areas should be registered

---

### 4.5. Implementation Status

Not implemented yet.