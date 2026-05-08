# US056 - Create an Aircraft Engine Model

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of aircraft engine models.

The implementation should include:

* authorization validation for the Backoffice Operator;
* validation of required engine model data;
* validation of the selected manufacturer;
* validation of engine type;
* validation of power;
* validation of fuel type;
* validation of efficiency;
* uniqueness verification of the model name and manufacturer combination;
* creation of an aircraft engine model;
* persistence of the registered engine model;
* support for registering aircraft engine models through bootstrap.

This user story should be implemented before US055 because aircraft models require at least one certified engine model.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterAircraftEngineModelUI`
* `RegisterAircraftEngineModelController`
* `RegisterAircraftEngineModelService`
* `RegisterAircraftEngineModelRequest`
* `AircraftEngineModelDTO`
* `BootstrapAircraftEngineModelLoader`
* `AircraftEngineModel`
* `EngineModelName`
* `Manufacturer`
* `Country`
* `EngineType`
* `Power`
* `FuelType`
* `Efficiency`
* `User`
* `Permission`
* `AuthorizationService`
* `AircraftEngineModelRepository`
* `ManufacturerRepository`
* `InMemoryAircraftEngineModelRepository`
* `InMemoryManufacturerRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an aircraft engine model can be registered with valid data.
* Verify that an unauthorized user cannot register an aircraft engine model.
* Verify that an aircraft engine model cannot be registered without a model name.
* Verify that an aircraft engine model cannot be registered without a manufacturer.
* Verify that an aircraft engine model cannot be registered with a non-existing manufacturer.
* Verify that an aircraft engine model cannot be registered without an engine type.
* Verify that invalid power information is rejected.
* Verify that invalid efficiency information is rejected.
* Verify that a duplicated model name and manufacturer combination is rejected.
* Verify that a successfully registered aircraft engine model is stored.
* Verify that failed registration does not change the repository.
* Verify that bootstrap registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful aircraft engine model registration

* **Given** an authenticated Backoffice Operator
* **And** an existing manufacturer
* **And** valid aircraft engine model data
* **When** the Backoffice Operator registers the aircraft engine model
* **Then** the system should store the aircraft engine model

**Test 2:** Duplicated model name and manufacturer

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft engine model with a given model name and manufacturer
* **When** the Backoffice Operator registers another aircraft engine model with the same model name and manufacturer
* **Then** the system should reject the registration

**Test 3:** Non-existing manufacturer

* **Given** an authenticated Backoffice Operator
* **And** aircraft engine model data referencing a non-existing manufacturer
* **When** the Backoffice Operator registers the aircraft engine model
* **Then** the system should reject the registration

**Test 4:** Invalid power

* **Given** an authenticated Backoffice Operator
* **And** aircraft engine model data with invalid power
* **When** the Backoffice Operator registers the aircraft engine model
* **Then** the system should reject the registration

**Test 5:** Invalid efficiency

* **Given** an authenticated Backoffice Operator
* **And** aircraft engine model data with invalid efficiency
* **When** the Backoffice Operator registers the aircraft engine model
* **Then** the system should reject the registration

**Test 6:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register aircraft engine models
* **When** the user tries to register an aircraft engine model
* **Then** the system should deny access

**Test 7:** Bootstrap registration

* **Given** valid bootstrap aircraft engine model data
* **When** the bootstrap process runs
* **Then** the default aircraft engine models should be registered

---

### 4.5. Implementation Status

Not implemented yet.