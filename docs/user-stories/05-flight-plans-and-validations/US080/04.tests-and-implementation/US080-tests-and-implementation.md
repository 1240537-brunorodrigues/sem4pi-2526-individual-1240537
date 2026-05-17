# US080 - Create a Flight Plan

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the creation of a flight plan for a route.

The implementation should include:

* authorization validation for the Pilot;
* lookup of the selected flight route;
* validation that the route is active on the selected departure date/time;
* lookup of the selected aircraft;
* validation that the aircraft belongs to the route's company;
* validation that the aircraft is operational;
* validation that the aircraft is available for the selected departure date/time;
* lookup of the selected pilot;
* validation that the pilot belongs to the route's company;
* validation that the pilot is active;
* validation that the pilot is certified for the selected aircraft model;
* validation that the pilot is available for the selected departure date/time;
* validation of departure date/time;
* validation of fuel quantity;
* validation that fuel quantity does not exceed aircraft model maximum fuel capacity;
* creation of the flight plan with status `draft`;
* persistence of the flight plan.

This user story creates the flight plan but does not execute the complete multi-step validation process.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `CreateFlightPlanUI`
* `CreateFlightPlanController`
* `CreateFlightPlanService`
* `CreateFlightPlanRequest`
* `FlightPlanDTO`
* `FlightPlan`
* `FlightPlanStatus`
* `FlightPlanCreationPolicy`
* `FlightRoute`
* `RouteName`
* `Aircraft`
* `AircraftRegistrationNumber`
* `AircraftModel`
* `Pilot`
* `PilotLicenseNumber`
* `PilotCertification`
* `DepartureDateTime`
* `FuelQuantity`
* `AirTransportCompany`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `FlightRouteRepository`
* `AircraftRepository`
* `PilotRepository`
* `FlightPlanRepository`
* `InMemoryFlightRouteRepository`
* `InMemoryAircraftRepository`
* `InMemoryPilotRepository`
* `InMemoryFlightPlanRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Pilot can create a flight plan with valid data.
* Verify that an unauthorized user cannot create a flight plan.
* Verify that a flight plan cannot be created for a non-existing route.
* Verify that a flight plan cannot be created for a route inactive on the departure date/time.
* Verify that a flight plan cannot be created with a non-existing aircraft.
* Verify that a flight plan cannot be created with an aircraft from another company.
* Verify that a flight plan cannot be created with a retired/decommissioned aircraft.
* Verify that a flight plan cannot be created if the aircraft is unavailable at the departure date/time.
* Verify that a flight plan cannot be created with a non-existing pilot.
* Verify that a flight plan cannot be created with an inactive pilot.
* Verify that a flight plan cannot be created with a pilot from another company.
* Verify that a flight plan cannot be created if the pilot is not certified for the aircraft model.
* Verify that a flight plan cannot be created if the pilot is unavailable at the departure date/time.
* Verify that a flight plan cannot be created without departure date/time.
* Verify that a flight plan cannot be created with invalid departure date/time.
* Verify that a flight plan cannot be created without fuel quantity.
* Verify that zero or negative fuel quantity is rejected.
* Verify that fuel quantity above aircraft model maximum fuel capacity is rejected.
* Verify that a successfully created flight plan has status `draft`.
* Verify that a successfully created flight plan is stored.
* Verify that failed flight plan creation does not change repositories.

---

### 4.4. Acceptance Tests

**Test 1:** Successful flight plan creation

* **Given** an authenticated Pilot
* **And** an existing active route
* **And** an operational aircraft belonging to the route's company
* **And** an active pilot belonging to the route's company
* **And** the pilot is certified for the aircraft model
* **And** valid departure date/time and fuel quantity
* **When** the pilot creates a flight plan
* **Then** the system should store the flight plan
* **And** the flight plan status should be `draft`

**Test 2:** Pilot from another company

* **Given** an authenticated Pilot
* **And** an existing route belonging to one company
* **And** a selected pilot belonging to another company
* **When** the flight plan is created
* **Then** the system should reject the operation

**Test 3:** Aircraft from another company

* **Given** an authenticated Pilot
* **And** an existing route belonging to one company
* **And** a selected aircraft belonging to another company
* **When** the flight plan is created
* **Then** the system should reject the operation

**Test 4:** Pilot not certified for aircraft model

* **Given** an authenticated Pilot
* **And** an active pilot belonging to the route's company
* **And** an operational aircraft belonging to the same company
* **And** the pilot is not certified for the aircraft model
* **When** the flight plan is created
* **Then** the system should reject the operation

**Test 5:** Decommissioned aircraft

* **Given** an authenticated Pilot
* **And** a selected aircraft is retired or decommissioned
* **When** the pilot tries to create a flight plan using that aircraft
* **Then** the system should reject the operation

**Test 6:** Inactive route

* **Given** an authenticated Pilot
* **And** a selected route is deactivated from a given date onwards
* **And** the departure date/time is after that deactivation date
* **When** the pilot tries to create a flight plan for that route
* **Then** the system should reject the operation

**Test 7:** Invalid fuel quantity

* **Given** an authenticated Pilot
* **And** otherwise valid flight plan data
* **When** the pilot provides zero, negative or excessive fuel quantity
* **Then** the system should reject the operation

**Test 8:** Aircraft unavailable

* **Given** an authenticated Pilot
* **And** an aircraft already assigned to another flight plan at the selected departure date/time
* **When** the pilot tries to create another flight plan using that aircraft at the same time
* **Then** the system should reject the operation

**Test 9:** Pilot unavailable

* **Given** an authenticated Pilot
* **And** a pilot already assigned to another flight plan at the selected departure date/time
* **When** another flight plan is created using the same pilot at the same time
* **Then** the system should reject the operation

**Test 10:** Unauthorized operation

* **Given** an authenticated user without permission to create flight plans
* **When** the user tries to create a flight plan
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.