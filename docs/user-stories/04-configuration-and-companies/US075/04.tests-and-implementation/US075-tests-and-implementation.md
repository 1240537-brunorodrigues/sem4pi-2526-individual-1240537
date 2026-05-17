# US075 - Add a Pilot

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the addition of a pilot to an air transport company.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* validation of pilot required data;
* validation of pilot email;
* verification that the pilot email is unique among system users;
* validation of pilot phone number;
* validation and uniqueness check of pilot license number;
* validation that at least one aircraft model certification is provided;
* validation that all selected aircraft model certifications reference existing aircraft models;
* creation of the pilot as a system user;
* creation of the pilot entity;
* creation of pilot certifications;
* association of the pilot with the selected air transport company;
* persistence of the system user and pilot.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `AddPilotUI`
* `AddPilotController`
* `AddPilotService`
* `AddPilotRequest`
* `PilotDTO`
* `Pilot`
* `PilotLicenseNumber`
* `PilotCertification`
* `AirTransportCompany`
* `CustomerCollaborator`
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
* `UserRepository`
* `AircraftModelRepository`
* `PilotRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryUserRepository`
* `InMemoryAircraftModelRepository`
* `InMemoryPilotRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can add a pilot with valid data.
* Verify that an unauthorized user cannot add a pilot.
* Verify that a collaborator cannot add a pilot to a company they do not belong to.
* Verify that a pilot cannot be added to a non-existing company.
* Verify that a pilot cannot be added without a name.
* Verify that a pilot cannot be added without an email.
* Verify that a pilot cannot be added with an invalid email.
* Verify that a pilot cannot be added with an email already used by another system user.
* Verify that a pilot cannot be added without a phone number.
* Verify that a pilot cannot be added without a license number.
* Verify that a duplicated pilot license number is rejected.
* Verify that a pilot cannot be added without at least one aircraft model certification.
* Verify that a certification cannot reference a non-existing aircraft model.
* Verify that a successfully added pilot is created as a system user.
* Verify that a successfully added pilot is associated with the selected company.
* Verify that a successfully added pilot stores the selected aircraft model certifications.
* Verify that failed pilot registration does not create a system user.
* Verify that failed pilot registration does not create a pilot.
* Verify that failed pilot registration does not change the company's pilot roster.

---

### 4.4. Acceptance Tests

**Test 1:** Successful pilot registration

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** valid pilot data
* **And** at least one existing aircraft model certification
* **When** the collaborator adds the pilot
* **Then** the system should create a system user for the pilot
* **And** create the pilot
* **And** associate the pilot with the selected company
* **And** store the pilot's aircraft model certifications

**Test 2:** Pilot with duplicated email

* **Given** an authenticated Air Transport Company Collaborator
* **And** an existing system user with a given email
* **When** the collaborator tries to add a pilot with the same email
* **Then** the system should reject the operation

**Test 3:** Duplicated license number

* **Given** an authenticated Air Transport Company Collaborator
* **And** an existing pilot with a given license number
* **When** the collaborator tries to add another pilot with the same license number
* **Then** the system should reject the registration

**Test 4:** Missing aircraft model certification

* **Given** an authenticated Air Transport Company Collaborator
* **And** valid pilot data
* **And** no aircraft model certification
* **When** the collaborator tries to add the pilot
* **Then** the system should reject the operation

**Test 5:** Non-existing aircraft model certification

* **Given** an authenticated Air Transport Company Collaborator
* **And** pilot data referencing a non-existing aircraft model certification
* **When** the collaborator tries to add the pilot
* **Then** the system should reject the operation

**Test 6:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to add a pilot to that company
* **Then** the system should reject the operation

**Test 7:** Non-existing company

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing company
* **When** the collaborator tries to add a pilot to that company
* **Then** the system should display a company not found message

**Test 8:** Unauthorized operation

* **Given** an authenticated user without permission to add pilots
* **When** the user tries to add a pilot
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.