# US072d - List Fleet by Age

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the listing of an air transport company's fleet by aircraft age.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* retrieval of aircraft belonging to the company;
* calculation of aircraft age from a reference date;
* optional filtering by minimum and maximum age;
* optional sorting by ascending or descending age;
* mapping of aircraft data to DTOs;
* display of an empty list message when no aircraft match the age criteria.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ListFleetByAgeUI`
* `ListFleetByAgeController`
* `ListFleetByAgeService`
* `ListFleetByAgeRequest`
* `AircraftDTO`
* `AgeCriteria`
* `AircraftAgeCalculator`
* `SortingOrder`
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

* Verify that an authorized company collaborator can list their company's fleet by age.
* Verify that an unauthorized user cannot list a company fleet by age.
* Verify that a collaborator cannot list the fleet of a company they do not belong to.
* Verify that a fleet cannot be listed by age for a non-existing company.
* Verify that aircraft age is calculated from the aircraft reference date.
* Verify that aircraft are ordered by ascending age when requested.
* Verify that aircraft are ordered by descending age when requested.
* Verify that only aircraft within the selected age interval are returned.
* Verify that aircraft outside the selected age interval are not returned.
* Verify that invalid age criteria are rejected.
* Verify that the list includes aircraft registration number.
* Verify that the list includes aircraft model.
* Verify that the list includes aircraft age.
* Verify that the list includes engine configuration.
* Verify that the list includes total seats.
* Verify that the list includes registered country.
* Verify that the list includes operational status.
* Verify that decommissioned aircraft remain visible.
* Verify that an empty list is returned when no aircraft match the age criteria.
* Verify that listing the fleet by age does not modify aircraft or company data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful fleet listing by ascending age

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has aircraft in its fleet
* **When** the collaborator lists the fleet by ascending age
* **Then** the system should display the aircraft ordered from youngest to oldest

**Test 2:** Successful fleet listing by descending age

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has aircraft in its fleet
* **When** the collaborator lists the fleet by descending age
* **Then** the system should display the aircraft ordered from oldest to youngest

**Test 3:** Age interval filter

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has aircraft with different ages
* **When** the collaborator lists aircraft with age between a minimum and maximum value
* **Then** only aircraft within that interval should be displayed

**Test 4:** No aircraft match age criteria

* **Given** an authenticated Air Transport Company Collaborator
* **And** no aircraft match the selected age criteria
* **When** the collaborator lists the fleet by age
* **Then** the system should display an empty list message

**Test 5:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to list that company's fleet by age
* **Then** the system should reject the operation

**Test 6:** Invalid age criteria

* **Given** an authenticated Air Transport Company Collaborator
* **And** invalid age criteria
* **When** the collaborator tries to list the fleet by age
* **Then** the system should reject the operation

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to list company fleets
* **When** the user tries to list a company fleet by age
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.