# US074 - Delete a Flight Route

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the logical deletion/deactivation of a flight route from a given date onwards.

The implementation should include:

* authorization validation for the Air Transport Company Collaborator;
* validation that the collaborator belongs to the selected company;
* lookup of the selected air transport company;
* lookup of the selected flight route;
* validation that the route belongs to the selected company;
* validation of the deactivation date;
* verification that there are no planned flights after the deactivation date;
* deactivation of the route from the selected date onwards;
* persistence of the updated route.

The route must not be physically deleted from the system.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `DeleteFlightRouteUI`
* `DeleteFlightRouteController`
* `DeleteFlightRouteService`
* `DeleteFlightRouteRequest`
* `FlightRouteDTO`
* `FlightRoute`
* `RouteName`
* `RouteStatus`
* `RouteDeactivationPolicy`
* `AirTransportCompany`
* `CustomerCollaborator`
* `Airport`
* `IATACode`
* `ICAOCode`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `CustomerCollaboratorRepository`
* `FlightRouteRepository`
* `FlightPlanRepository`
* `InMemoryAirTransportCompanyRepository`
* `InMemoryFlightRouteRepository`
* `InMemoryFlightPlanRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized company collaborator can deactivate a flight route.
* Verify that an unauthorized user cannot deactivate a flight route.
* Verify that a collaborator cannot deactivate a route from a company they do not belong to.
* Verify that a route cannot be deactivated for a non-existing company.
* Verify that a non-existing route cannot be deactivated.
* Verify that a route cannot be deactivated if it does not belong to the selected company.
* Verify that a route cannot be deactivated without a deactivation date.
* Verify that a route cannot be deactivated if there are planned flights after the deactivation date.
* Verify that successful deactivation updates the route status.
* Verify that successful deactivation stores the deactivation date.
* Verify that deactivation does not physically delete the route.
* Verify that a deactivated route cannot be used to create new flights from the deactivation date onwards.
* Verify that failed deactivation does not change route data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful route deactivation

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator belongs to the selected company
* **And** an existing route belongs to that company
* **And** there are no planned flights after the selected deactivation date
* **When** the collaborator deactivates the route from that date onwards
* **Then** the route should be marked as deactivated
* **And** the deactivation date should be stored

**Test 2:** Route with planned flights after deactivation date

* **Given** an authenticated Air Transport Company Collaborator
* **And** an existing route belongs to that company
* **And** there are planned flights after the selected deactivation date
* **When** the collaborator tries to deactivate the route
* **Then** the system should reject the operation

**Test 3:** Collaborator from another company

* **Given** an authenticated Air Transport Company Collaborator
* **And** the collaborator does not belong to the company that owns the route
* **When** the collaborator tries to deactivate the route
* **Then** the system should reject the operation

**Test 4:** Non-existing route

* **Given** an authenticated Air Transport Company Collaborator
* **And** a non-existing route
* **When** the collaborator tries to deactivate the route
* **Then** the system should display a route not found message

**Test 5:** Route is not physically deleted

* **Given** an existing route
* **When** the route is deactivated
* **Then** the route should remain stored in the system

**Test 6:** Deactivated route cannot be used for new flights

* **Given** a route deactivated from a given date onwards
* **When** a user tries to create a new flight for that route after that date
* **Then** the system should reject the operation

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to deactivate flight routes
* **When** the user tries to deactivate a flight route
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.