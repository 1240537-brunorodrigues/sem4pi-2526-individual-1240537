# US072 - List an Air Transport Company's Fleet

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the listing of an air transport company's fleet.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* retrieval of all aircraft in the company's fleet;
* mapping of aircraft data to DTOs;
* display of an empty list message when the fleet has no aircraft.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ListCompanyFleetUI`
* `ListCompanyFleetController`
* `ListCompanyFleetService`
* `ListCompanyFleetRequest`
* `AircraftDTO`
* `AirTransportCompany`
* `Fleet`
* `CustomerCollaborator`
* `Aircraft`
* `AircraftRegistrationNumber`
* `AircraftConfiguration`
* `AircraftModel`
* `AircraftEngineModel`
* `CabinConfiguration`
* `Country`
* `OperationalStatus`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `AircraftRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryAircraftRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can list their company's fleet.
* Verify that an unauthorized user cannot list a company fleet.
* Verify that a collaborator cannot list the fleet of a company they do not belong to.
* Verify that a fleet cannot be listed for a non-existing company.
* Verify that the list includes aircraft registration number.
* Verify that the list includes aircraft model.
* Verify that the list includes engine configuration.
* Verify that the list includes cabin configuration or total seats.
* Verify that the list includes registered country.
* Verify that the list includes operational status.
* Verify that decommissioned aircraft remain visible in the fleet list.
* Verify that an empty list is returned when the company has no aircraft.
* Verify that listing the fleet does not modify aircraft or company data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful fleet listing

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has aircraft in its fleet
* **When** the collaborator lists the company fleet
* **Then** the system should display the aircraft in the fleet

**Test 2:** Empty fleet

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has no aircraft in its fleet
* **When** the collaborator lists the company fleet
* **Then** the system should display an empty fleet message

**Test 3:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to list that company's fleet
* **Then** the system should reject the operation

**Test 4:** Decommissioned aircraft remains visible

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has a decommissioned aircraft
* **When** the collaborator lists the company fleet
* **Then** the decommissioned aircraft should appear with its operational status

**Test 5:** Non-existing company

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing company
* **When** the collaborator tries to list the fleet
* **Then** the system should display a company not found message

**Test 6:** Unauthorized operation

* **Given** an authenticated user without permission to list company fleets
* **When** the user tries to list a company fleet
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.