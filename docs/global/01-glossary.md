# 📘 Glossary

---

## 📑 Index

| Concept | Description |
|--------|-------------|
| [User](#-user) | Person with access to the system |
| [Role](#-role) | Access profile assigned to a user |
| [Permission](#-permission) | Specific action allowed in the system |
| [Customer](#-customer) | Entity that can be an air transport company or air control area |
| [Air Transport Company](#-air-transport-company) | Airline/company that owns aircraft, routes and pilots |
| [Air Control Area](#-air-control-area) | Geographic area used for weather, airports and simulations |
| [Airport](#-airport) | Airport located inside an air control area |
| [Flight Route](#-flight-route) | Route between origin and destination airports |
| [Aircraft Model](#-aircraft-model) | Aircraft model with performance and physical properties |
| [Aircraft Engine Model](#-aircraft-engine-model) | Engine model compatible with aircraft models |
| [Aircraft](#-aircraft) | Registered aircraft owned by an air transport company |
| [Fleet](#-fleet) | Set of aircraft owned by an air transport company |
| [Pilot](#-pilot) | System user certified to operate aircraft models |
| [Pilot Certification](#-pilot-certification) | Certification that allows a pilot to operate an aircraft model |
| [Flight Plan](#-flight-plan) | Plan describing a programmed flight |
| [Core Flight DSL](#-core-flight-dsl) | Domain-specific language used to describe/import flight plans |
| [Flight Plan Test](#-flight-plan-test) | Validation/test performed on a flight plan |
| [Weather Data](#-weather-data) | Weather information associated with an air control area and date/time |
| [Wind](#-wind) | Wind speed and direction information |
| [Simulation](#-simulation) | Execution of one or more flight plans in a controlled environment |
| [Hybrid Simulation Environment](#-hybrid-simulation-environment) | C-based simulation architecture using processes, threads and shared memory |
| [Parent Simulation Process](#-parent-simulation-process) | Main process that coordinates the hybrid simulation |
| [Flight Process](#-flight-process) | Independent child process executing a flight plan |
| [Shared Memory Segment](#-shared-memory-segment) | Memory area shared between simulation processes |
| [Simulation Step / Time Step](#-simulation-step--time-step) | Discrete unit of simulation time |
| [Aircraft Position](#-aircraft-position) | Aircraft location at a given simulation time |
| [Velocity Vector](#-velocity-vector) | Direction and speed components of aircraft movement |
| [Safety Threshold](#-safety-threshold) | Separation and violation limits used during simulation |
| [Safety Violation](#-safety-violation) | Event representing unsafe proximity or conflict between aircraft |
| [Safety Violation Detection Thread](#-safety-violation-detection-thread) | Thread responsible for scanning shared memory for flight conflicts |
| [Report Generation Thread](#-report-generation-thread) | Thread responsible for compiling simulation reports |
| [Environment Thread](#-environment-thread) | Thread responsible for loading and publishing environmental data |
| [Semaphore](#-semaphore) | Process synchronization primitive used for step-by-step execution |
| [Mutex](#-mutex) | Thread synchronization primitive used to protect shared data |
| [Condition Variable](#-condition-variable) | Thread synchronization primitive used for event notification |
| [Simulation Report](#-simulation-report) | Report summarizing simulation results |
| [Final Simulation Report](#-final-simulation-report) | Comprehensive final report generated after simulation conclusion |
| [Monthly Statistics Report](#-monthly-statistics-report) | Monthly statistical report with reusable structure and branding |
| [Flights Logging Server](#-flights-logging-server) | UDP/HTTP server used for remote logging and visualization |
| [UDP Datagram](#-udp-datagram) | Network message sent by flight processes to the logging server |
| [Flight Progress Update](#-flight-progress-update) | Step-by-step progress data sent by a flight process |
| [Status Page](#-status-page) | Browser page showing simulated flights progress |
| [AJAX Request](#-ajax-request) | Browser request used to update visualization without reloading |

---

## 👤 User

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
A person with access to the system.

**Attributes:**
- `email`
- `name`
- `phoneNumber`
- `status`

**Constraints:**
- `email` must be unique.
- A user must have at least one role.
- A user may be enabled or disabled.
- Authentication must verify the user's credentials and status.

**Relationships:**
- Has one `Credential`.
- Has one or more [Roles](#-role).
- May have `SecurityClearance`.
- May have `SkillsAssessment`.
- Can be specialized as:
    - `Administrator`
    - `BackofficeOperator`
    - `WeatherPerson`
    - `FlightControlOperator`
    - `Pilot`
    - `CustomerCollaborator`

---

## 🛡️ Role

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Access profile assigned to a user, defining what the user is allowed to do.

**Attributes:**
- `name`

**Relationships:**
- Assigned to one or more [Users](#-user).
- Grants multiple [Permissions](#-permission).

---

## 🔐 Permission

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Specific action or operation that can be granted to a role.

**Attributes:**
- `name`

**Relationships:**
- Granted by a [Role](#-role).
- Used by authorization mechanisms to protect operations.

---

## 🧾 Customer

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Generic customer entity of the system.

**Attributes:**
- `id`
- `name`

**Specializations:**
- [Air Transport Company](#-air-transport-company)
- [Air Control Area](#-air-control-area)

**Relationships:**
- Has multiple `CustomerCollaborator` records.

---

## 🏢 Air Transport Company

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
An airline or air transport company that uses the system to manage aircraft, routes, pilots and flight plans.

**Attributes:**
- `name`
- `iataCode`
- `icaoCode`

**Constraints:**
- `iataCode` must be unique.
- `icaoCode` must be unique.

**Relationships:**
- Is a [Customer](#-customer).
- Has one [Fleet](#-fleet).
- Owns multiple [Aircraft](#-aircraft).
- Employs multiple [Pilots](#-pilot).
- Owns multiple [Flight Routes](#-flight-route).
- Has multiple `AirTransportCompanyCollaborator` users.

---

## 🗺️ Air Control Area

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
A geographic area used to organize airports, weather data and simulation contexts.

**Attributes:**
- `code`
- `name`
- `geographicBoundaries`

**Constraints:**
- `code` must be unique.
- Geographic boundaries must be valid.

**Relationships:**
- Is a [Customer](#-customer).
- Contains or groups [Airports](#-airport).
- Has associated [Weather Data](#-weather-data).
- Can be used as a simulation geographic context.

---

## 🛫 Airport

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Airport registered in the system and located inside an air control area.

**Attributes:**
- `name`
- `town`
- `country`
- `elevation`

**Identifiers:**
- `IATACode`
- `ICAOCode`

**Relationships:**
- Belongs to an [Air Control Area](#-air-control-area).
- Located at a `Coordinate`.
- Located in a `Country`.
- Can be used as origin or destination in a [Flight Route](#-flight-route).

---

## 🛣️ Flight Route

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Route owned by an air transport company, connecting an origin airport and a destination airport.

**Attributes:**
- `routeCode`
- `description`
- `status`
- `deactivationDate`

**Constraints:**
- Route code must be unique.
- Route code follows the required company initials plus numeric format defined in the specification.
- A route must have an origin and a destination airport.
- Origin and destination should not be the same airport.

**Relationships:**
- Owned by an [Air Transport Company](#-air-transport-company).
- Has one origin [Airport](#-airport).
- Has one destination [Airport](#-airport).
- May be used by multiple [Flight Plans](#-flight-plan).
- Has a `RouteStatus`.

---

## ✈️ Aircraft Model

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Commercial aircraft model characterized by physical, operational and aerodynamic properties.

**Attributes:**
- `modelName`
- `emptyWeight`
- `maximumTakeOffWeight`
- `maximumZeroFuelWeight`
- `maximumFuelCapacity`
- `serviceCeiling`
- `cruiseSpeed`
- `wingArea`
- `dragCoefficient`
- `liftCoefficient`
- `capacity`

**Constraints:**
- Weight values must be positive.
- `maximumTakeOffWeight > 0`
- `maximumFuelCapacity > 0`
- `cruiseSpeed > 0`
- Certified engine models must be compatible with the aircraft model.

**Relationships:**
- Made by an `AircraftManufacturer`.
- Has an `AircraftType`.
- Certifies one or more `CertifiedEngineModel`.
- Used by [Aircraft](#-aircraft) through `AircraftConfiguration`.
- Used by [Pilot Certifications](#-pilot-certification).

---

## ⚙️ Aircraft Engine Model

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Aircraft engine model that may be certified for specific aircraft models.

**Attributes:**
- `modelName`
- `power`
- `efficiency`

**Constraints:**
- `power > 0`
- `efficiency > 0`
- Engine model must be compatible with certified aircraft models.

**Relationships:**
- Made by an `EngineManufacturer`.
- Has an `EngineType`.
- Uses a `FuelType`.
- Referenced by `CertifiedEngineModel`.
- Used in `AircraftConfiguration`.

---

## 🛩️ Aircraft

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Specific registered aircraft owned by an air transport company.

**Attributes:**
- `registrationNumber`
- `registeredCountry`
- `operationalStatus`
- `flightCrewSize`

**Constraints:**
- `registrationNumber` must be unique.
- Aircraft must use a valid aircraft model and engine configuration.
- Aircraft must respect cabin capacity constraints.
- Only operational aircraft should be assigned to flight plans.

**Relationships:**
- Owned by an [Air Transport Company](#-air-transport-company).
- Belongs to a [Fleet](#-fleet).
- Has an `AircraftConfiguration`.
- Has a `CabinConfiguration`.
- Registered in a `Country`.
- Has an `OperationalStatus`.
- May be used by [Flight Plans](#-flight-plan).

---

## 🧳 Fleet

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Set of aircraft owned by an air transport company.

**Attributes:**
- `aircraft`

**Relationships:**
- Belongs to one [Air Transport Company](#-air-transport-company).
- Contains multiple [Aircraft](#-aircraft).

---

## 👨‍✈️ Pilot

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
A system user employed by an air transport company and certified to operate specific aircraft models.

**Attributes:**
- `licenseNumber`
- `status`

**Constraints:**
- A pilot is also a system user.
- `licenseNumber` must be unique.
- A pilot must hold valid certifications to operate assigned aircraft models.
- Only active pilots should be assigned to flight plans.

**Relationships:**
- Specialization of [User](#-user).
- Belongs to an [Air Transport Company](#-air-transport-company).
- Has one `PilotLicense`.
- Has one `PilotStatus`.
- Holds one or more [Pilot Certifications](#-pilot-certification).
- May own, create or be assigned to [Flight Plans](#-flight-plan).

---

## 📜 Pilot Certification

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Certification that authorizes a pilot to operate a specific aircraft model.

**Attributes:**
- `certificationDate`
- `validUntil`

**Constraints:**
- Certification must not be expired when assigning a pilot to a flight plan.
- Certification must reference a valid aircraft model.

**Relationships:**
- Belongs to a [Pilot](#-pilot).
- Certifies the pilot for an [Aircraft Model](#-aircraft-model).

---

## 🧭 Flight Plan

**Entity Type:** Domain Model  
**Source:** Core System

**Definition:**  
Structured plan describing a programmed flight.

**Attributes:**
- `flightNumber`
- `departureDateTime`
- `fuelQuantity`
- `status`
- `testStatus`

**Constraints:**
- Must reference a valid route.
- Must reference a valid aircraft.
- Must reference a valid pilot.
- Pilot must be certified for the aircraft model.
- Fuel quantity must satisfy validation rules.
- Flight plan may be draft, validated or rejected.
- Scheduled date/time must be valid according to business rules.

**Relationships:**
- Planned for a [Flight Route](#-flight-route).
- Uses an [Aircraft](#-aircraft).
- Assigned to a [Pilot](#-pilot).
- Has a `FlightPlanStatus`.
- Has a `FuelQuantity`.
- May use [Weather Data](#-weather-data).
- May have multiple [Flight Plan Tests](#-flight-plan-test).
- May be imported using the [Core Flight DSL](#-core-flight-dsl).

---

## 🧬 Core Flight DSL

**Entity Type:** Domain Model  
**Source:** Flight Plan Import / DSL

**Definition:**  
Domain-specific language used to describe and import flight plans.

**Attributes:**
- `version`
- `grammar`

**Related Concepts:**
- `DSLFile`
- `FlightPlanFile`
- `Grammar`
- `Lexer`
- `Parser`
- `ParseTree`
- `FlightPlanAst`
- `SemanticValidator`
- `InternalFlightRepresentation`

**Relationships:**
- Defines a `Grammar`.
- Flight plan files conform to it.
- Used to produce internal flight plan representations.

---

## ✅ Flight Plan Test

**Entity Type:** Domain Model  
**Source:** Flight Plan Validation

**Definition:**  
Validation or test execution performed on a flight plan.

**Attributes:**
- `testedAt`
- `result`
- `status`
- `details`

**Relationships:**
- Belongs to a [Flight Plan](#-flight-plan).
- Has a `TestStatus`.
- Stores a `ValidationResult`.
- May include `FuelValidationResult`.
- May include `AltitudeValidationResult`.

---

## 🌦️ Weather Data

**Entity Type:** Domain Model  
**Source:** Weather Module

**Definition:**  
Weather information associated with a date/time and an air control area.

**Attributes:**
- `dateTime`
- `windDirection`
- `windSpeed`
- `temperature`
- `pressure`
- `visibility`
- `precipitation`

**Constraints:**
- Must belong to a valid air control area.
- Must contain valid wind data.
- Imported records must pass validation.

**Relationships:**
- Registered by a `WeatherPerson`.
- Stored by `WeatherService`.
- Belongs to an [Air Control Area](#-air-control-area).
- Contains [Wind](#-wind).
- Has a `WeatherSource`.
- May be associated with a [Flight Plan](#-flight-plan).
- May be used by the [Environment Thread](#-environment-thread).

---

## 💨 Wind

**Entity Type:** Domain Model  
**Source:** Weather Module

**Definition:**  
Wind information used in weather records and environmental simulation.

**Attributes:**
- `direction`
- `speed`

**Relationships:**
- Contained in [Weather Data](#-weather-data).
- Used to create `WindConfiguration` for simulation.

---

## 🧪 Simulation

**Entity Type:** Domain Model  
**Source:** Simulation Module

**Definition:**  
Execution of one or more flight plans in a controlled simulation environment.

**Attributes:**
- `simulationId`
- `startTime`
- `endTime`
- `status`

**Constraints:**
- Must include at least one flight plan.
- Must use valid simulation configuration.
- Must run in a defined geographic area.
- Must use safety thresholds and performance settings.

**Relationships:**
- Started by a [Flight Control Operator](#-user).
- Runs in a `GeographicArea`.
- Includes one or more [Flight Plans](#-flight-plan).
- Uses `SimulationConfiguration`.
- Uses [Safety Threshold](#-safety-threshold).
- Uses `PerformanceSettings`.
- Controlled by a simulation process.
- May produce [Simulation Reports](#-simulation-report).

---

## 🧵 Hybrid Simulation Environment

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Simulation architecture that combines a parent process, child flight processes, shared memory, semaphores, mutexes, condition variables and signals.

**Attributes:**
- `status`

**Relationships:**
- Uses `SimulationConfiguration`.
- Initializes a [Parent Simulation Process](#-parent-simulation-process).
- Allocates a [Shared Memory Segment](#-shared-memory-segment).
- Initializes semaphores.
- Performs resource cleanup on failure.

---

## 🧠 Parent Simulation Process

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Main process responsible for coordinating the hybrid simulation.

**Attributes:**
- `processId`
- `status`
- `currentTimeStep`

**Responsibilities:**
- Forks [Flight Processes](#-flight-process).
- Creates dedicated threads.
- Maintains active flight process set.
- Coordinates time-step execution.
- Uses shared memory.
- Uses synchronization primitives.

**Relationships:**
- Controlled by `SimulationEngine`.
- Creates [Safety Violation Detection Thread](#-safety-violation-detection-thread).
- Creates [Report Generation Thread](#-report-generation-thread).
- Creates [Environment Thread](#-environment-thread).
- Uses [Shared Memory Segment](#-shared-memory-segment).
- Uses [Semaphore](#-semaphore), [Mutex](#-mutex) and [Condition Variable](#-condition-variable).

---

## 🛩️ Flight Process

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Independent child process responsible for executing a flight plan during simulation.

**Attributes:**
- `processId`
- `flightId`
- `aircraftId`
- `status`

**Responsibilities:**
- Executes a [Flight Plan](#-flight-plan).
- Sends movement messages.
- Writes aircraft state to shared memory.
- Waits for time-step permission.
- Signals step completion.
- Handles signals.
- Sends UDP flight progress updates to the [Flights Logging Server](#-flights-logging-server).

**Relationships:**
- Forked by the parent/main simulation process.
- Executes one [Flight Plan](#-flight-plan).
- Sends [Flight Progress Updates](#-flight-progress-update).
- Produces [Aircraft Positions](#-aircraft-position).

---

## 🧠 Shared Memory Segment

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Memory segment shared between the parent process, flight processes and simulation threads.

**Attributes:**
- `name`
- `size`
- `initialized`

**Contains:**
- `SharedSimulationData`
- `SharedAircraftState`
- `EnvironmentalData`
- Safety violation data
- Process statuses
- Warnings and errors

**Relationships:**
- Allocated by the hybrid simulation environment.
- Used by parent process.
- Attached to by flight processes.
- Written to by environment thread.
- Read by safety detection and report generation threads.

---

## ⏱️ Simulation Step / Time Step

**Entity Type:** Domain Model  
**Source:** Simulation Module

**Definition:**  
Discrete unit of simulation time used to synchronize flight execution.

**Attributes:**
- `index`
- `startTime`
- `endTime`
- `timestamp`

**Relationships:**
- Belongs to the simulation clock.
- Associated with position updates.
- Used by semaphores for step-by-step execution.
- Used in flight progress updates.

---

## 📍 Aircraft Position

**Entity Type:** Domain Model  
**Source:** Simulation Module

**Definition:**  
Position of an aircraft at a specific simulation time.

**Attributes:**
- `aircraftId`
- `timestamp`
- `latitude`
- `longitude`
- `altitude`

**Relationships:**
- Stored in `PositionHistory`.
- Stored in `CurrentPositionMap`.
- Used by safety violation detection.
- May be included in simulation reports.

---

## ➡️ Velocity Vector

**Entity Type:** Domain Model  
**Source:** Simulation Module

**Definition:**  
Vector representing aircraft movement direction and speed components.

**Attributes:**
- `x`
- `y`
- `z`

**Relationships:**
- May be included in a `MovementMessage`.
- May be included in a [Flight Progress Update](#-flight-progress-update).
- Required in detailed safety violation report information.

---

## 🚨 Safety Threshold

**Entity Type:** Domain Model  
**Source:** Simulation Module

**Definition:**  
Rules and limits used to determine whether aircraft are in unsafe proximity.

**Attributes:**
- `minimumSeparation`
- `minimumHorizontalSeparation`
- `minimumVerticalSeparation`
- `maximumViolationCount`

**Relationships:**
- Used by [Simulation](#-simulation).
- Applied by safety violation detection.
- Can trigger termination when violations exceed the maximum threshold.

---

## ⚠️ Safety Violation

**Entity Type:** Domain Model  
**Source:** Simulation Module

**Definition:**  
Detected event representing unsafe aircraft proximity or predicted conflict.

**Attributes:**
- `violationId`
- `timestamp`
- `severity`
- `description`

**Relationships:**
- Involves two or more [Flight Processes](#-flight-process) or aircraft.
- Based on [Aircraft Positions](#-aircraft-position).
- Stored in `ViolationEventLog`.
- May produce a `SafetyViolationEvent`.
- Included in [Simulation Reports](#-simulation-report).

---

## 🧵 Safety Violation Detection Thread

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Dedicated parent-process thread responsible for detecting safety violations.

**Attributes:**
- `threadId`
- `status`
- `scanInterval`

**Responsibilities:**
- Scans shared simulation data.
- Detects safety violation events.
- Updates shared violation state.
- Notifies the report generation thread through condition variables.

**Relationships:**
- Specialization of `DedicatedThread`.
- Reads shared simulation data.
- Produces `SafetyViolationEvent`.

---

## 📄 Report Generation Thread

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Dedicated parent-process thread responsible for compiling simulation report data.

**Attributes:**
- `threadId`
- `status`
- `reportStatus`

**Responsibilities:**
- Reads shared simulation data.
- Waits for safety violation notifications.
- Updates report data.
- Generates simulation and final reports.

**Relationships:**
- Specialization of `DedicatedThread`.
- Reads shared simulation data.
- Generates [Simulation Reports](#-simulation-report).
- Generates [Final Simulation Reports](#-final-simulation-report).

---

## 🌍 Environment Thread

**Entity Type:** Domain Model  
**Source:** C Simulation Component

**Definition:**  
Dedicated parent-process thread responsible for loading environmental configuration and writing environmental data into shared memory during simulation.

**Attributes:**
- `threadId`
- `status`

**Responsibilities:**
- Loads wind speed and wind direction from the weather service.
- Validates environmental data.
- Writes environmental data to shared memory at each simulation step.

**Relationships:**
- Specialization of `DedicatedThread`.
- Loads data from `WeatherService`.
- Writes to [Shared Memory Segment](#-shared-memory-segment).
- Provides data used by flight processes and environmental influence models.

---

## 🔁 Semaphore

**Entity Type:** Synchronization Concept  
**Source:** C Simulation Component

**Definition:**  
Process synchronization primitive used to enforce step-by-step simulation execution.

**Types:**
- `StartStepSemaphore`
- `CompletionSemaphore`
- `SemaphoreSet`

**Relationships:**
- Parent process releases flight processes with start-step semaphores.
- Flight processes wait on start-step semaphores.
- Flight processes signal completion semaphores.
- Parent process waits for completion semaphores.

---

## 🔒 Mutex

**Entity Type:** Synchronization Concept  
**Source:** C Simulation Component

**Definition:**  
Thread synchronization primitive used to protect shared data from concurrent access.

**Attributes:**
- `name`
- `locked`

**Relationships:**
- Used by parent process threads.
- Protects shared violation state.
- Used with condition variables.

---

## 📣 Condition Variable

**Entity Type:** Synchronization Concept  
**Source:** C Simulation Component

**Definition:**  
Thread synchronization primitive used to wait for and signal events between threads.

**Attributes:**
- `name`
- `waitingThreads`

**Relationships:**
- Used by dedicated threads.
- Used by safety violation detection thread to notify report generation thread.
- Used with mutexes.

---

## 📊 Simulation Report

**Entity Type:** Domain Model  
**Source:** Reporting Module

**Definition:**  
Report summarizing simulation results for operational decision-making.

**Attributes:**
- `reportId`
- `generatedAt`
- `status`
- `finalOutcome`

**Expected Content:**
- Total number of flights
- Flight execution statuses
- Safety violation timestamps
- Safety violation positions
- Pass/fail validation result
- Report file reference

**Relationships:**
- Requested by a [Flight Control Operator](#-user).
- Describes a [Simulation](#-simulation).
- Generated by [Report Generation Thread](#-report-generation-thread).
- Saved as a `ReportFile`.

---

## 🧾 Final Simulation Report

**Entity Type:** Domain Model  
**Source:** Reporting Module

**Definition:**  
Comprehensive report generated after the simulation concludes.

**Attributes:**
- `reportId`
- `generatedAt`
- `finalOutcome`

**Expected Content:**
- Total number of flights
- Individual execution statuses
- Detailed safety violation events
- Timestamps
- Positions
- Velocity vectors
- Overall validation result
- Pass/fail indication
- Warnings and errors
- Environmental influence summary, if applicable

**Constraints:**
- Must only be generated after simulation conclusion.
- Must be saved to a file.

**Relationships:**
- Requested by a [Flight Control Operator](#-user).
- Describes a [Simulation](#-simulation).
- Generated by [Report Generation Thread](#-report-generation-thread).

---

## 📅 Monthly Statistics Report

**Entity Type:** Domain Model  
**Source:** Reporting Module

**Definition:**  
Statistical report generated for a selected month and year.

**Attributes:**
- `reportId`
- `generatedAt`
- `filePath`

**Expected Content:**
- Reporting period
- Total simulations
- Total flights
- Total safety violations
- Passed validations
- Failed validations
- Report sections
- Report graphics

**Relationships:**
- Requested by a [Flight Control Operator](#-user).
- Covers a `ReportingPeriod`.
- Uses a `ReportTemplate`.
- Uses `ReportBranding`.
- Summarizes `MonthlyStatisticsData`.
- Saved as a `ReportFile`.

---

## 🛰️ Flights Logging Server

**Entity Type:** External Logging / Visualization Component  
**Source:** Remote Logging Module

**Definition:**  
Specific server application responsible for receiving flight progress updates through UDP and providing browser-based visualization through HTTP.

**Attributes:**
- `host`
- `udpPort`
- `httpPort`
- `status`

**Responsibilities:**
- Receives UDP datagrams from flight processes.
- Parses and validates flight progress updates.
- Stores each flight's progress in a separate file.
- Maintains latest progress data.
- Hosts an HTTP server.
- Provides a browser status page.

**Relationships:**
- Receives [UDP Datagrams](#-udp-datagram).
- Maintains `FlightProgressStore`.
- Contains `UDPReceiver`.
- Contains `HTTPServer`.
- Used by [Status Page](#-status-page).

---

## 📦 UDP Datagram

**Entity Type:** Network Message  
**Source:** Remote Logging Module

**Definition:**  
UDP network message sent by a flight process to the Flights Logging Server at each simulation step.

**Attributes:**
- `sourceAddress`
- `sourcePort`
- `payload`

**Constraints:**
- Must identify the flight.
- Must identify the simulation step.
- Must include flight progress data.
- May be lost, duplicated or received out of order because UDP is unreliable.

**Relationships:**
- Represents a serialized [Flight Progress Update](#-flight-progress-update).
- Received by [Flights Logging Server](#-flights-logging-server).

---

## 📡 Flight Progress Update

**Entity Type:** Domain Model / Network Payload  
**Source:** Remote Logging Module

**Definition:**  
Progress information sent by a running flight process at each simulation step.

**Attributes:**
- `flightId`
- `aircraftId`
- `simulationStep`
- `timestamp`
- `latitude`
- `longitude`
- `altitude`
- `velocityVector`
- `status`

**Relationships:**
- Sent by a [Flight Process](#-flight-process).
- Belongs to a [Simulation Step / Time Step](#-simulation-step--time-step).
- Serialized as a [UDP Datagram](#-udp-datagram).
- Stored as a `FlightLogEntry`.
- Stored in `FlightProgressStore`.
- Rendered in the [Status Page](#-status-page).

---

## 🌐 Status Page

**Entity Type:** Visualization Component  
**Source:** Remote Visualization Module

**Definition:**  
Browser-accessible page served by the Flights Logging Server to display simulated flight progress.

**Attributes:**
- `url`
- `autoRefreshEnabled`

**Responsibilities:**
- Displays current simulated flight progress.
- Sends AJAX requests for updated data.
- Updates the display without reloading the page.

**Relationships:**
- Served by `HTTPServer`.
- Displayed in a `WebBrowser`.
- Sends [AJAX Requests](#-ajax-request).
- Renders a `FlightProgressTable`.

---

## 🔄 AJAX Request

**Entity Type:** Web Interaction  
**Source:** Remote Visualization Module

**Definition:**  
Asynchronous browser request used to retrieve updated flight progress data without reloading the page.

**Attributes:**
- `endpoint`
- `interval`

**Relationships:**
- Sent by the [Status Page](#-status-page).
- Requests data from `HTTPServer`.
- Receives current progress data from `FlightProgressStore`.

---