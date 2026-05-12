# US071 - Decommission an Aircraft

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the decommissioning of an aircraft from an air transport company's fleet.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of the selected aircraft;
* validation that the aircraft belongs to the selected company's fleet;
* verification that the aircraft has no pending flights;
* update of the aircraft operational status;
* persistence of the updated aircraft.

The aircraft must not be deleted from the system or removed from the fleet.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `DecommissionAircraftUI`
* `DecommissionAircraftController`
* `DecommissionAircraftService`
* `DecommissionAircraftRequest`
* `AircraftDTO`
* `AirTransportCompany`
* `Fleet`
* `CustomerCollaborator`
* `Aircraft`
* `AircraftRegistrationNumber`
* `OperationalStatus`
* `Flight`
* `AircraftDecommissionPolicy`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `AircraftRepository`
* `FlightRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryAircraftRepository`
* `InMemoryFlightRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an aircraft can be decommissioned when it belongs to the collaborator's company and has no pending flights.
* Verify that an unauthorized user cannot decommission an aircraft.
* Verify that a collaborator cannot decommission aircraft from a company they do not belong to.
* Verify that an aircraft cannot be decommissioned if the company does not exist.
* Verify that a non-existing aircraft cannot be decommissioned.
* Verify that an aircraft cannot be decommissioned if it does not belong to the selected company.
* Verify that an aircraft cannot be decommissioned if it has pending flights.
* Verify that decommissioning updates the aircraft operational status.
* Verify that decommissioning does not remove the aircraft from the fleet.
* Verify that failed decommissioning does not change aircraft status.
* Verify that a decommissioned aircraft is not available for new active flight assignments.

---

### 4.4. Acceptance Tests

**Test 1:** Successful aircraft decommissioning

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** an aircraft belongs to that company's fleet
* **And** the aircraft has no pending flights
* **When** the collaborator decommissions the aircraft
* **Then** the aircraft operational status should become decommissioned
* **And** the aircraft should remain in the company's fleet

**Test 2:** Aircraft with pending flights

* **Given** an authenticated Air Transport Company Collaborator
* **And** an aircraft has pending flights
* **When** the collaborator tries to decommission the aircraft
* **Then** the system should reject the operation

**Test 3:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the company that owns the aircraft
* **When** the collaborator tries to decommission the aircraft
* **Then** the system should reject the operation

**Test 4:** Non-existing aircraft

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing aircraft registration number
* **When** the collaborator tries to decommission the aircraft
* **Then** the system should display an aircraft not found message

**Test 5:** Aircraft is not removed from fleet

* **Given** an aircraft belonging to a company fleet
* **When** the aircraft is decommissioned
* **Then** the aircraft should still belong to the company's fleet

**Test 6:** Unauthorized operation

* **Given** an authenticated user without permission to decommission aircraft
* **When** the user tries to decommission an aircraft
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.