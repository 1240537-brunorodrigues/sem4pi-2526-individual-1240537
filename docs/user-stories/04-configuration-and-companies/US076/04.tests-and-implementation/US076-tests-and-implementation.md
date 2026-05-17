# US076 - List Pilot Roster

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the listing of an air transport company's active pilot roster.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* retrieval of active pilots associated with the company;
* retrieval of pilot system user information;
* retrieval of pilot aircraft model certifications;
* mapping of pilot data to DTOs;
* display of an empty roster message when no active pilots exist.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ListPilotRosterUI`
* `ListPilotRosterController`
* `ListPilotRosterService`
* `ListPilotRosterRequest`
* `PilotDTO`
* `AirTransportCompany`
* `CustomerCollaborator`
* `Pilot`
* `PilotLicenseNumber`
* `PilotCertification`
* `AircraftModel`
* `AircraftModelName`
* `Manufacturer`
* `User`
* `Email`
* `PhoneNumber`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `PilotRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryPilotRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can list their company's pilot roster.
* Verify that an unauthorized user cannot list a pilot roster.
* Verify that a collaborator cannot list the pilot roster of a company they do not belong to.
* Verify that a pilot roster cannot be listed for a non-existing company.
* Verify that only pilots associated with the selected company are returned.
* Verify that only active pilots are returned.
* Verify that inactive pilots are not returned.
* Verify that an empty list is returned when the company has no active pilots.
* Verify that the returned pilot data includes name.
* Verify that the returned pilot data includes email.
* Verify that the returned pilot data includes phone number.
* Verify that the returned pilot data includes license number.
* Verify that the returned pilot data includes aircraft model certifications.
* Verify that listing the pilot roster does not modify pilot data.
* Verify that listing the pilot roster does not modify system user data.
* Verify that listing the pilot roster does not modify company data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful pilot roster listing

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has active pilots
* **When** the collaborator lists the pilot roster
* **Then** the system should display the active pilots of that company

**Test 2:** Inactive pilots are not listed

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has active and inactive pilots
* **When** the collaborator lists the pilot roster
* **Then** only active pilots should be displayed

**Test 3:** Pilot roster includes certifications

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has active pilots with aircraft model certifications
* **When** the collaborator lists the pilot roster
* **Then** each listed pilot should include their aircraft model certifications

**Test 4:** Empty pilot roster

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the company has no active pilots
* **When** the collaborator lists the pilot roster
* **Then** the system should display an empty roster message

**Test 5:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to list that company's pilot roster
* **Then** the system should reject the operation

**Test 6:** Non-existing company

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing company
* **When** the collaborator tries to list the pilot roster
* **Then** the system should display a company not found message

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to list pilot rosters
* **When** the user tries to list a pilot roster
* **Then** the system should deny access

**Test 8:** Read-only operation

* **Given** an existing company with active pilots
* **When** the collaborator lists the pilot roster
* **Then** no pilot, system user or company data should be changed

---

### 4.5. Implementation Status

Not implemented yet.