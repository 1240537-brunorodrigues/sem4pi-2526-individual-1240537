# US058 - Remove an Engine Model from an Aircraft Model

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the removal of a certified engine model from an aircraft model.

The implementation should include:

* authorization validation for the Backoffice Operator;
* lookup of the aircraft model;
* lookup of the aircraft engine model;
* validation that the engine model is currently certified for the aircraft model;
* validation that no actual aircraft use the aircraft model and engine model configuration;
* validation that the aircraft model retains at least one certified engine model;
* update of the aircraft model certified engine list;
* persistence of the updated aircraft model.

This operation should not delete the aircraft model or the aircraft engine model.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RemoveEngineModelFromAircraftModelUI`
* `RemoveEngineModelFromAircraftModelController`
* `RemoveEngineModelFromAircraftModelService`
* `RemoveEngineModelFromAircraftModelRequest`
* `AircraftModelDTO`
* `AircraftModel`
* `AircraftEngineModel`
* `Aircraft`
* `AircraftConfiguration`
* `AircraftConfigurationUsagePolicy`
* `AircraftModelName`
* `AircraftType`
* `EngineModelName`
* `EngineType`
* `User`
* `Permission`
* `AuthorizationService`
* `AircraftModelRepository`
* `AircraftEngineModelRepository`
* `AircraftRepository`
* `InMemoryAircraftModelRepository`
* `InMemoryAircraftEngineModelRepository`
* `InMemoryAircraftRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that a certified engine model can be removed from an aircraft model when allowed.
* Verify that an unauthorized user cannot remove an engine model from an aircraft model.
* Verify that an engine model cannot be removed from a non-existing aircraft model.
* Verify that a non-existing engine model cannot be removed from an aircraft model.
* Verify that an engine model cannot be removed if it is not certified for the aircraft model.
* Verify that an engine model cannot be removed if actual aircraft use that configuration.
* Verify that an engine model cannot be removed if it would leave the aircraft model without certified engine models.
* Verify that the certified engine list is updated after a successful removal.
* Verify that the aircraft model remains unchanged when the operation fails.
* Verify that the updated aircraft model is stored.
* Verify that the aircraft engine model itself is not deleted from the system.

---

### 4.4. Acceptance Tests

**Test 1:** Successful engine model removal

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model with more than one certified engine model
* **And** an engine model certified for that aircraft model
* **And** no actual aircraft using that configuration
* **When** the Backoffice Operator removes the engine model
* **Then** the engine model should be removed from the aircraft model's certified engine list

**Test 2:** Removing the last certified engine model

* **Given** an authenticated Backoffice Operator
* **And** an aircraft model with only one certified engine model
* **When** the Backoffice Operator tries to remove that engine model
* **Then** the system should reject the operation

**Test 3:** Engine model used by actual aircraft

* **Given** an authenticated Backoffice Operator
* **And** an aircraft model with a certified engine model
* **And** an actual aircraft using that aircraft model and engine model configuration
* **When** the Backoffice Operator tries to remove the engine model
* **Then** the system should reject the operation

**Test 4:** Engine model not certified for aircraft model

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model
* **And** an existing engine model not certified for that aircraft model
* **When** the Backoffice Operator tries to remove the engine model
* **Then** the system should reject the operation

**Test 5:** Non-existing aircraft model

* **Given** an authenticated Backoffice Operator
* **And** a non-existing aircraft model
* **When** the Backoffice Operator tries to remove an engine model from it
* **Then** the system should display an aircraft model not found error

**Test 6:** Non-existing engine model

* **Given** an authenticated Backoffice Operator
* **And** an existing aircraft model
* **And** a non-existing aircraft engine model
* **When** the Backoffice Operator tries to remove the engine model
* **Then** the system should display an engine model not found error

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to manage aircraft models
* **When** the user tries to remove an engine model from an aircraft model
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.