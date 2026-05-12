# US072b - List Fleet by Maker

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the listing of an air transport company's fleet by aircraft maker/manufacturer.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of the selected maker/manufacturer;
* retrieval of aircraft belonging to the company and matching the selected maker;
* mapping of aircraft data to DTOs;
* display of an empty list message when no aircraft match the maker.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ListFleetByMakerUI`
* `ListFleetByMakerController`
* `ListFleetByMakerService`
* `ListFleetByMakerRequest`
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
* `ManufacturerRepository`
* `AircraftRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryManufacturerRepository`
* `InMemoryAircraftRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can list their company's fleet by maker.
* Verify that an unauthorized user cannot list a company fleet by maker.
* Verify that a collaborator cannot list the fleet of a company they do not belong to.
* Verify that a fleet cannot be listed by maker for a non-existing company.
* Verify that a non-existing maker is rejected.
* Verify that only aircraft from the selected maker are returned.
* Verify that aircraft from other makers are not returned.
* Verify that the list includes aircraft registration number.
* Verify that the list includes aircraft maker.
* Verify that the list includes aircraft model.
* Verify that the list includes engine configuration.
* Verify that the list includes cabin configuration or total seats.
* Verify that the list includes registered country.
* Verify that the list includes operational status.
* Verify that decommissioned aircraft from the selected maker remain visible.
* Verify that an empty list is returned when no aircraft match the selected maker.
* Verify that listing the fleet by maker does not modify aircraft or company data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful fleet listing by maker

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has aircraft from the selected maker
* **When** the collaborator lists the fleet by maker
* **Then** the system should display only aircraft from that maker

**Test 2:** No aircraft from selected maker

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has no aircraft from the selected maker
* **When** the collaborator lists the fleet by maker
* **Then** the system should display an empty list message

**Test 3:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to list that company's fleet by maker
* **Then** the system should reject the operation

**Test 4:** Non-existing maker

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing maker
* **When** the collaborator tries to list the fleet by that maker
* **Then** the system should display a maker not found message

**Test 5:** Decommissioned aircraft remains visible

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has a decommissioned aircraft from the selected maker
* **When** the collaborator lists the fleet by maker
* **Then** the decommissioned aircraft should appear with its operational status

**Test 6:** Unauthorized operation

* **Given** an authenticated user without permission to list company fleets
* **When** the user tries to list a company fleet by maker
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.