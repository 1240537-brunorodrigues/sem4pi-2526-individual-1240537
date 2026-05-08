# US055 - Create an Aircraft Model

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of aircraft models.

The implementation should include:

* authorization validation for the Backoffice Operator;
* validation of required aircraft model data;
* validation of the selected manufacturer;
* validation of aircraft type;
* validation of technical flight characteristics;
* verification that at least one certified engine model exists;
* uniqueness verification of the model name and manufacturer combination;
* creation of an aircraft model;
* persistence of the registered aircraft model;
* support for registering aircraft models through bootstrap.

This user story is implemented after US056 because aircraft models require at least one existing certified engine model.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterAircraftModelUI`
* `RegisterAircraftModelController`
* `RegisterAircraftModelService`
* `RegisterAircraftModelRequest`
* `AircraftModelDTO`
* `BootstrapAircraftModelLoader`
* `AircraftModel`
* `AircraftModelName`
* `AircraftType`
* `AircraftCharacteristics`
* `AircraftEngineModel`
* `EngineModelName`
* `Manufacturer`
* `Country`
* `User`
* `Permission`
* `AuthorizationService`
* `AircraftModelRepository`
* `AircraftEngineModelRepository`
* `ManufacturerRepository`
* `InMemoryAircraftModelRepository`
* `InMemoryAircraftEngineModelRepository`
* `InMemoryManufacturerRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an aircraft model can be registered with valid data.
* Verify that an unauthorized user cannot register an aircraft model.
* Verify that an aircraft model cannot be registered without a model name.
* Verify that an aircraft model cannot be registered without a manufacturer.
* Verify that an aircraft model cannot be registered with a non-existing manufacturer.
* Verify that an aircraft model cannot be registered without an aircraft type.
* Verify that an aircraft model cannot be registered without certified engine models.
* Verify that an aircraft model cannot be registered with non-existing certified engine models.
* Verify that a duplicated model name and manufacturer combination is rejected.
* Verify that invalid empty weight is rejected.
* Verify that invalid maximum take-off weight is rejected.
* Verify that invalid maximum zero fuel weight is rejected.
* Verify that invalid maximum fuel capacity is rejected.
* Verify that invalid service ceiling is rejected.
* Verify that invalid cruise speed is rejected.
* Verify that invalid wing area is rejected.
* Verify that invalid drag coefficient is rejected.
* Verify that invalid lift coefficient is rejected.
* Verify that a successfully registered aircraft model is stored.
* Verify that failed registration does not change the repository.
* Verify that bootstrap registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful aircraft model registration

* **Given** an authenticated Backoffice Operator
* **And** an existing manufacturer
* **And** at least one existing aircraft engine model
* **And** valid aircraft model data
* **When** the Backoffice Operator registers the aircraft model
* **Then** the system should store the aircraft model
* **And** the aircraft model should contain at least one certified engine model

**Test 2:** Duplicated model name and manufacturer

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model with a given model name and manufacturer
* **When** the Backoffice Operator registers another aircraft model with the same model name and manufacturer
* **Then** the system should reject the registration

**Test 3:** Missing certified engine model

* **Given** an authenticated Backoffice Operator
* **And** aircraft model data without certified engine models
* **When** the Backoffice Operator registers the aircraft model
* **Then** the system should reject the registration

**Test 4:** Non-existing certified engine model

* **Given** an authenticated Backoffice Operator
* **And** aircraft model data referencing a non-existing engine model
* **When** the Backoffice Operator registers the aircraft model
* **Then** the system should reject the registration

**Test 5:** Invalid aircraft characteristics

* **Given** an authenticated Backoffice Operator
* **And** aircraft model data with invalid technical characteristics
* **When** the Backoffice Operator registers the aircraft model
* **Then** the system should reject the registration

**Test 6:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register aircraft models
* **When** the user tries to register an aircraft model
* **Then** the system should deny access

**Test 7:** Bootstrap registration

* **Given** valid bootstrap aircraft model data
* **When** the bootstrap process runs
* **Then** the default aircraft models should be registered

---

### 4.5. Implementation Status

Not implemented yet.