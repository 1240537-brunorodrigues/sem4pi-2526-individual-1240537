# US100 - Simulate Flights in a Given Area

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the base simulation of flights in a given area.

The implementation should include:

* authorization validation for the Flight Control Operator;
* validation of all required simulation parameters;
* validation of geographic area;
* validation of time range;
* validation of included flights or flight plans;
* validation of weather conditions;
* validation of safety thresholds;
* validation of performance settings;
* preparation of input data for the C simulation component;
* execution of the C simulation component;
* forking of one process per flight;
* pipe-based communication between main process and flight processes;
* signal handling;
* execution of each designated flight plan by its flight process;
* tracking of aircraft positions over time by the main process;
* parsing of simulation output;
* safe handling of child process failures.

The simulation component must be implemented in C and must use processes, pipes and signals.

---

### 4.2. Main Classes / Components to Implement

Possible Java/application-side classes:

* `SimulateFlightsInAreaUI`
* `SimulateFlightsInAreaController`
* `SimulateFlightsInAreaService`
* `SimulationRequest`
* `SimulationResultDTO`
* `SafetyThresholdDTO`
* `PerformanceSettingsDTO`
* `SimulationParameterValidator`
* `SimulationInputMapper`
* `CSimulationRunner`
* `SimulationInput`
* `CSimulationOutput`
* `SimulationResultParser`
* `AuthorizationService`
* `FlightControlOperator`
* `GeographicArea`
* `FlightPlan`
* `WeatherData`
* `GeographicAreaRepository`
* `FlightPlanRepository`
* `WeatherDataRepository`

Possible C-side components:

* `main_simulation_process`
* `flight_process`
* `pipe_channel`
* `signal_handler`
* `position_history`
* `aircraft_position`
* `simulation_config`
* `simulation_result`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Flight Control Operator can start a simulation with valid parameters.
* Verify that an unauthorized user cannot start a simulation.
* Verify that a simulation cannot start without a geographic area.
* Verify that an invalid geographic area is rejected.
* Verify that a simulation cannot start without a time range.
* Verify that an invalid time range is rejected.
* Verify that a simulation cannot start without included flights.
* Verify that invalid flight plans are rejected.
* Verify that invalid weather conditions are rejected.
* Verify that invalid safety thresholds are rejected.
* Verify that invalid performance settings are rejected.
* Verify that valid simulation parameters are mapped to C simulation input.
* Verify that C simulation output is parsed into a simulation result.
* Verify that C simulation execution errors are handled safely.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that the main simulation process initializes correctly.
* Verify that a pipe is created for each flight process.
* Verify that the system forks one child process per included flight.
* Verify that each flight process receives or loads its designated flight plan.
* Verify that each flight process sends position updates through its pipe.
* Verify that the main process reads position updates from pipes.
* Verify that the main process stores aircraft positions over time.
* Verify that the main process handles child process termination.
* Verify that signal handlers are registered.
* Verify that cleanup is performed when a process exits.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify Java/application code can execute the C simulation component.
* Verify the C component receives valid simulation input from the application layer.
* Verify the C component returns a valid simulation output.
* Verify a simulation with one flight creates one flight process.
* Verify a simulation with multiple flights creates one process per flight.
* Verify position updates are received by the main process.
* Verify the main process tracks positions for all simulated flights.
* Verify process failure is reported to the application layer.

---

### 4.6. Acceptance Tests

**Test 1:** Successful simulation with one flight

* **Given** an authenticated Flight Control Operator
* **And** a valid geographic area
* **And** a valid time range
* **And** one valid included flight plan
* **And** valid weather conditions, safety thresholds and performance settings
* **When** the operator starts the simulation
* **Then** the C simulation component should start
* **And** the main process should fork one flight process
* **And** the flight process should execute its designated flight plan
* **And** the main process should track aircraft positions over time

**Test 2:** Successful simulation with multiple flights

* **Given** an authenticated Flight Control Operator
* **And** multiple valid included flight plans
* **When** the operator starts the simulation
* **Then** the main process should fork one process per flight
* **And** each flight process should execute its designated flight plan
* **And** each flight process should communicate with the main process through pipes

**Test 3:** Invalid simulation parameters

* **Given** an authenticated Flight Control Operator
* **And** invalid simulation parameters
* **When** the operator tries to start the simulation
* **Then** the system should reject the operation
* **And** no flight process should be forked

**Test 4:** Invalid flight plan

* **Given** an authenticated Flight Control Operator
* **And** an included flight plan that is not executable by the simulation
* **When** the operator tries to start the simulation
* **Then** the system should reject the operation

**Test 5:** Pipe communication

* **Given** a running simulation
* **And** one or more flight processes
* **When** the flight processes execute their flight plans
* **Then** each flight process should send position updates through a pipe
* **And** the main process should receive those updates

**Test 6:** Position tracking

* **Given** a running simulation
* **When** flight processes send position updates
* **Then** the main process should store aircraft positions over time using an appropriate data structure

**Test 7:** Child process failure

* **Given** a running simulation
* **And** a flight process fails unexpectedly
* **When** the main process detects the failure
* **Then** the failure should be handled safely
* **And** the simulation result should report the issue

**Test 8:** Unauthorized operation

* **Given** an authenticated user without permission to simulate flights
* **When** the user tries to start a simulation
* **Then** the system should deny access

---

### 4.7. Implementation Status

Not implemented yet.