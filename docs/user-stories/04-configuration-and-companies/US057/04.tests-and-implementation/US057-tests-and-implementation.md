# US057 - Add an Engine Model to an Aircraft Model

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the association of an existing aircraft engine model with an existing aircraft model.

The implementation should include:

* authorization validation for the Backoffice Operator;
* lookup of the aircraft model;
* lookup of the aircraft engine model;
* validation of engine type compatibility;
* validation that the engine model is not already certified for the aircraft model;
* update of the aircraft model certified engine list;
* persistence of the updated aircraft model.

This operation should not create new aircraft models or aircraft engine models.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `AddEngineModelToAircraftModelUI`
* `AddEngineModelToAircraftModelController`
* `AddEngineModelToAircraftModelService`
* `AddEngineModelToAircraftModelRequest`
* `AircraftModelDTO`
* `AircraftModel`
* `AircraftEngineModel`
* `AircraftModelName`
* `AircraftType`
* `EngineModelName`
* `EngineType`
* `EngineCompatibilityPolicy`
* `User`
* `Permission`
* `AuthorizationService`
* `AircraftModelRepository`
* `AircraftEngineModelRepository`
* `InMemoryAircraftModelRepository`
* `InMemoryAircraftEngineModelRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that a compatible engine model can be added to an aircraft model.
* Verify that an unauthorized user cannot add an engine model to an aircraft model.
* Verify that an engine model cannot be added to a non-existing aircraft model.
* Verify that a non-existing engine model cannot be added to an aircraft model.
* Verify that an incompatible engine model cannot be added to an aircraft model.
* Verify that the same engine model cannot be added twice to the same aircraft model.
* Verify that the aircraft model certified engine list is updated after a successful operation.
* Verify that the aircraft model remains unchanged when the operation fails.
* Verify that the updated aircraft model is stored.
* Verify that compatibility rules are applied through the compatibility policy.

---

### 4.4. Acceptance Tests

**Test 1:** Successful engine model addition

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model
* **And** an existing compatible aircraft engine model
* **When** the Backoffice Operator adds the engine model to the aircraft model
* **Then** the engine model should be added to the aircraft model's certified engine list

**Test 2:** Duplicate certified engine model

* **Given** an authenticated Backoffice Operator
* **And** an aircraft model that already has a given certified engine model
* **When** the Backoffice Operator tries to add the same engine model again
* **Then** the system should reject the operation

**Test 3:** Incompatible engine model

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model
* **And** an incompatible aircraft engine model
* **When** the Backoffice Operator tries to add the engine model
* **Then** the system should reject the operation

**Test 4:** Non-existing aircraft model

* **Given** an authenticated Backoffice Operator
* **And** a non-existing aircraft model
* **When** the Backoffice Operator tries to add an engine model to it
* **Then** the system should display an aircraft model not found error

**Test 5:** Non-existing engine model

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model
* **And** a non-existing aircraft engine model
* **When** the Backoffice Operator tries to add the engine model
* **Then** the system should display an engine model not found error

**Test 6:** Unauthorized operation

* **Given** an authenticated user without permission to manage aircraft models
* **When** the user tries to add an engine model to an aircraft model
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.