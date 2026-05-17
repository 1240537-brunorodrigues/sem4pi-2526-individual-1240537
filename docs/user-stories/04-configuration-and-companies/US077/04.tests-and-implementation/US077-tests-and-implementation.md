# US077 - Remove a Pilot

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the removal of a pilot from an air transport company's active pilot roster.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of the selected pilot;
* validation that the pilot belongs to the selected company;
* validation that the pilot is active;
* verification that the pilot has no assigned flight plans;
* update of the pilot status to inactive;
* persistence of the updated pilot.

The pilot must not be physically deleted from the system.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RemovePilotUI`
* `RemovePilotController`
* `RemovePilotService`
* `RemovePilotRequest`
* `PilotDTO`
* `Pilot`
* `PilotLicenseNumber`
* `PilotDeactivationPolicy`
* `FlightPlan`
* `AirTransportCompany`
* `CustomerCollaborator`
* `User`
* `Email`
* `PhoneNumber`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `PilotRepository`
* `FlightPlanRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryPilotRepository`
* `InMemoryFlightPlanRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can make an active pilot inactive.
* Verify that an unauthorized user cannot remove a pilot.
* Verify that a collaborator cannot remove a pilot from a company they do not belong to.
* Verify that a pilot cannot be removed from a non-existing company.
* Verify that a non-existing pilot cannot be removed.
* Verify that a pilot cannot be removed if they do not belong to the selected company.
* Verify that an inactive pilot cannot be removed again or returns an appropriate result.
* Verify that a pilot cannot be made inactive if there are assigned flight plans.
* Verify that successful removal changes the pilot status to inactive.
* Verify that successful removal does not physically delete the pilot.
* Verify that successful removal preserves the associated system user.
* Verify that an inactive pilot does not appear in the active pilot roster.
* Verify that an inactive pilot is not available for future flight plan assignment.
* Verify that failed removal does not change pilot data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful pilot removal

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** an active pilot belongs to that company
* **And** the pilot has no assigned flight plans
* **When** the collaborator removes the pilot from the roster
* **Then** the pilot should become inactive
* **And** the pilot should no longer appear in the active pilot roster

**Test 2:** Pilot with assigned flight plans

* **Given** an authenticated Air Transport Company Collaborator
* **And** an active pilot belongs to the selected company
* **And** the pilot has assigned flight plans
* **When** the collaborator tries to remove the pilot from the roster
* **Then** the system should reject the operation

**Test 3:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the company that owns the pilot
* **When** the collaborator tries to remove the pilot
* **Then** the system should reject the operation

**Test 4:** Non-existing pilot

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing pilot license number
* **When** the collaborator tries to remove the pilot
* **Then** the system should display a pilot not found message

**Test 5:** Already inactive pilot

* **Given** an authenticated Air Transport Company Collaborator
* **And** the selected pilot is already inactive
* **When** the collaborator tries to remove the pilot
* **Then** the system should display an appropriate message

**Test 6:** Pilot is not physically deleted

* **Given** an existing pilot
* **When** the pilot is removed from the active roster
* **Then** the pilot should remain stored in the system

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to remove pilots
* **When** the user tries to remove a pilot
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.