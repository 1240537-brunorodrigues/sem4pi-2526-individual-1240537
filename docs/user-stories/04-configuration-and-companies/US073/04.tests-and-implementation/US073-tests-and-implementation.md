# US073 - Create a Flight Route

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the creation of a flight route for an air transport company.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of origin and destination airports;
* validation that origin and destination are different;
* validation of route name format;
* validation that the route name starts with the company's two-letter initials;
* validation that the route name contains up to four digits after the company initials;
* verification of route uniqueness within the company;
* creation of the flight route;
* persistence of the route.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `CreateFlightRouteUI`
* `CreateFlightRouteController`
* `CreateFlightRouteService`
* `CreateFlightRouteRequest`
* `FlightRouteDTO`
* `FlightRoute`
* `RouteName`
* `FlightRoutePolicy`
* `AirTransportCompany`
* `CustomerCollaborator`
* `Airport`
* `IATACode`
* `ICAOCode`
* `Coordinate`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `AirportRepository`
* `FlightRouteRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryAirportRepository`
* `InMemoryFlightRouteRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can create a valid flight route.
* Verify that an unauthorized user cannot create a flight route.
* Verify that a collaborator cannot create a route for a company they do not belong to.
* Verify that a route cannot be created for a non-existing company.
* Verify that a route cannot be created with a non-existing origin airport.
* Verify that a route cannot be created with a non-existing destination airport.
* Verify that origin and destination airports cannot be the same.
* Verify that a route cannot be created without a route name.
* Verify that a route name that does not start with the company's two-letter initials is rejected.
* Verify that a route name with more than four digits after the company initials is rejected.
* Verify that a duplicated route name is rejected.
* Verify that a duplicated route for the same company, origin and destination is rejected.
* Verify that a successfully created route is stored.
* Verify that failed route creation does not change the repository.

---

### 4.4. Acceptance Tests

**Test 1:** Successful route creation

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the origin and destination airports exist
* **And** the origin and destination airports are different
* **When** the collaborator creates a flight route
* **Then** the system should store the new flight route
* **And** the route should belong to the selected company
* **And** the route name follows the required format

**Test 2:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the selected company
* **When** the collaborator tries to create a route for that company
* **Then** the system should reject the operation

**Test 3:** Same origin and destination airport

* **Given** an authenticated Air Transport Company Collaborator
* **And** the same airport is selected as origin and destination
* **When** the collaborator tries to create the route
* **Then** the system should reject the operation

**Test 4:** Duplicated route

* **Given** an authenticated Air Transport Company Collaborator
* **And** an existing route for the same company, origin and destination
* **When** the collaborator tries to create the same route again
* **Then** the system should reject the operation

**Test 5:** Non-existing airport

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing origin or destination airport
* **When** the collaborator tries to create the route
* **Then** the system should display an airport not found message

**Test 6:** Unauthorized operation

* **Given** an authenticated user without permission to create flight routes
* **When** the user tries to create a flight route
* **Then** the system should deny access

**Test 7:** Invalid route name format

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** the origin and destination airports exist
* **And** the route name does not start with the company's two-letter initials or has more than four digits
* **When** the collaborator tries to create the route
* **Then** the system should reject the operation

---

### 4.5. Implementation Status

Not implemented yet.