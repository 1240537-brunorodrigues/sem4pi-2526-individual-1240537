# US070 - Add an Aircraft to an Air Transport Company

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of an aircraft in an air transport company's fleet.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of the selected aircraft model;
* lookup of the selected aircraft engine model;
* validation that the selected engine model is certified for the selected aircraft model;
* validation of aircraft registration number;
* uniqueness verification of aircraft registration number;
* validation of registered country;
* validation of operational status;
* validation of cabin configuration;
* verification that total seats do not exceed the aircraft model capacity;
* creation of the aircraft;
* persistence of the aircraft in the company's fleet.
* validation of number of flight crew members;

---

### 4.2. Main Classes to Implement

Possible main classes:

* `AddAircraftToCompanyFleetUI`
* `AddAircraftToCompanyFleetController`
* `AddAircraftToCompanyFleetService`
* `AddAircraftToCompanyFleetRequest`
* `AircraftDTO`
* `AirTransportCompany`
* `Fleet`
* `CustomerCollaborator`
* `Aircraft`
* `AircraftRegistrationNumber`
* `AircraftModel`
* `AircraftEngineModel`
* `AircraftConfiguration`
* `AircraftConfigurationPolicy`
* `CabinConfiguration`
* `OperationalStatus`
* `Country`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `AircraftModelRepository`
* `AircraftEngineModelRepository`
* `AircraftRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryAircraftRepository`
* `FlightCrewSize`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an aircraft can be added to a company fleet with valid data.
* Verify that an unauthorized user cannot add an aircraft to a fleet.
* Verify that a collaborator cannot add aircraft to a company they do not belong to.
* Verify that an aircraft cannot be added to a non-existing company.
* Verify that an aircraft cannot be added with a non-existing aircraft model.
* Verify that an aircraft cannot be added with a non-existing engine model.
* Verify that an aircraft cannot be added with an engine model not certified for the aircraft model.
* Verify that an aircraft cannot be added without a registration number.
* Verify that a duplicated registration number is rejected.
* Verify that invalid registered country is rejected.
* Verify that invalid operational status is rejected.
* Verify that negative seat counts are rejected.
* Verify that total seats cannot exceed the aircraft model capacity.
* Verify that a successfully added aircraft is stored.
* Verify that failed registration does not change the company's fleet.
* Verify that an aircraft cannot be added without a number of flight crew members.
* Verify that zero or negative flight crew size is rejected.
* Verify that a successfully added aircraft stores the number of flight crew members.

---

### 4.4. Acceptance Tests

**Test 1:** Successful aircraft registration

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** an existing aircraft model
* **And** a certified engine model for that aircraft model
* **And** valid aircraft data, including cabin configuration and number of flight crew members
* **When** the collaborator adds the aircraft to the company fleet
* **Then** the system should register the aircraft
* **And** the aircraft should belong to the selected company's fleet

**Test 2:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to add an aircraft to that company
* **Then** the system should reject the operation

**Test 3:** Duplicated aircraft registration number

* **Given** an authenticated Air Transport Company Collaborator
* **And** an existing aircraft with a given registration number
* **When** the collaborator tries to add another aircraft with the same registration number
* **Then** the system should reject the registration

**Test 4:** Invalid engine configuration

* **Given** an authenticated Air Transport Company Collaborator
* **And** an existing aircraft model
* **And** an engine model not certified for that aircraft model
* **When** the collaborator tries to add the aircraft
* **Then** the system should reject the operation

**Test 5:** Cabin configuration exceeds model capacity

* **Given** an authenticated Air Transport Company Collaborator
* **And** an aircraft model with a defined maximum capacity
* **And** a cabin configuration whose total seats exceed that capacity
* **When** the collaborator tries to add the aircraft
* **Then** the system should reject the registration

**Test 6:** Non-existing aircraft model

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing aircraft model
* **When** the collaborator tries to add an aircraft using that model
* **Then** the system should display an aircraft model not found message

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to add aircraft to a company fleet
* **When** the user tries to add an aircraft
* **Then** the system should deny access

**Test 8:** Invalid flight crew size

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** valid aircraft model and engine configuration
* **And** aircraft data with an invalid number of flight crew members
* **When** the collaborator tries to add the aircraft
* **Then** the system should reject the registration

---

### 4.5. Implementation Status

Not implemented yet.