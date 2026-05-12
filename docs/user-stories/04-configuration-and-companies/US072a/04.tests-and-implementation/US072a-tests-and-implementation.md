# US072a - List Fleet by Model

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the listing of an air transport company's fleet by aircraft model.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of the selected aircraft model;
* retrieval of aircraft belonging to the company and matching the selected model;
* mapping of aircraft data to DTOs;
* display of an empty list message when no aircraft match the model.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ListFleetByModelUI`
* `ListFleetByModelController`
* `ListFleetByModelService`
* `ListFleetByModelRequest`
* `AircraftDTO`
* `AirTransportCompany`
* `Fleet`
* `CustomerCollaborator`
* `Aircraft`
* `AircraftRegistrationNumber`
* `AircraftConfiguration`
* `AircraftModel`
* `AircraftEngineModel`
* `Manufacturer`
* `CabinConfiguration`
* `Country`
* `OperationalStatus`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `AircraftModelRepository`
* `AircraftRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryAircraftModelRepository`
* `InMemoryAircraftRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can list their company's fleet by model.
* Verify that an unauthorized user cannot list a company fleet by model.
* Verify that a collaborator cannot list the fleet of a company they do not belong to.
* Verify that a fleet cannot be listed by model for a non-existing company.
* Verify that a non-existing aircraft model is rejected.
* Verify that only aircraft of the selected model are returned.
* Verify that aircraft of other models are not returned.
* Verify that the list includes aircraft registration number.
* Verify that the list includes aircraft model.
* Verify that the list includes engine configuration.
* Verify that the list includes cabin configuration or total seats.
* Verify that the list includes registered country.
* Verify that the list includes operational status.
* Verify that decommissioned aircraft of the selected model remain visible.
* Verify that an empty list is returned when no aircraft match the selected model.
* Verify that listing the fleet by model does not modify aircraft or company data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful fleet listing by model

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has aircraft of the selected model
* **When** the collaborator lists the fleet by model
* **Then** the system should display only aircraft of that model

**Test 2:** No aircraft of selected model

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has no aircraft of the selected model
* **When** the collaborator lists the fleet by model
* **Then** the system should display an empty list message

**Test 3:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to list that company's fleet by model
* **Then** the system should reject the operation

**Test 4:** Non-existing aircraft model

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing aircraft model
* **When** the collaborator tries to list the fleet by that model
* **Then** the system should display an aircraft model not found message

**Test 5:** Decommissioned aircraft remains visible

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has a decommissioned aircraft of the selected model
* **When** the collaborator lists the fleet by model
* **Then** the decommissioned aircraft should appear with its operational status

**Test 6:** Unauthorized operation

* **Given** an authenticated user without permission to list company fleets
* **When** the user tries to list a company fleet by model
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.