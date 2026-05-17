# US086 - Pilot Remote Access

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement remote access for Pilots through the Air Transport Company App.

The implementation should include:

* a TCP-based client application;
* an embedded TCP server application in the system;
* a request/response protocol for remote Pilot operations;
* authentication through the TCP connection;
* verification that the authenticated user is a Pilot;
* authorization for each remote Pilot operation;
* routing of remote requests to the corresponding Pilot application services;
* meaningful success and error responses;
* safe handling of malformed or unsupported requests;
* safe TCP connection closure;
* no direct client access to repositories or the database.

All Pilot user stories must be remotely available through the client application.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `AirTransportCompanyApp`
* `RemoteAccessClientUI`
* `TcpClient`
* `EmbeddedTcpServer`
* `RemoteClientSession`
* `RemoteRequest`
* `RemoteResponse`
* `RemoteProtocolCodec`
* `RemoteRequestHandler`
* `RemoteOperationRouter`
* `AuthenticationService`
* `AuthorizationService`
* `Credentials`
* `AuthenticationToken`
* `User`
* `Pilot`
* `Role`
* `Permission`
* `UserRepository`
* `PilotRepository`
* Application services for all Pilot user stories:
    * `CreateFlightPlanService`
    * `CreateFlightPlanFromFileService`
    * `InsertWeatherDataInFlightService`
    * `TestFlightPlanService`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that the TCP client can establish a connection with the embedded server.
* Verify that the TCP client can send a remote request.
* Verify that the embedded server can receive and decode a remote request.
* Verify that the embedded server can return a remote response.
* Verify that valid Pilot credentials produce a successful authentication response.
* Verify that invalid credentials produce an authentication error response.
* Verify that an authenticated non-Pilot user cannot access Pilot remote operations.
* Verify that authorization is checked before executing a remote Pilot operation.
* Verify that unauthorized remote Pilot operations are rejected.
* Verify that supported Pilot operation identifiers are routed to the correct application service.
* Verify that unsupported operation identifiers return a meaningful error response.
* Verify that malformed requests return a meaningful error response.
* Verify that remote Pilot operations do not bypass application services.
* Verify that the TCP client has no direct access to repositories or the database.
* Verify that the TCP connection can be closed safely.

---

### 4.4. Integration Tests

The following integration tests should be implemented:

* Verify that a Pilot can authenticate remotely.
* Verify that a Pilot can remotely create a flight plan.
* Verify that a Pilot can remotely create a flight plan from a file.
* Verify that a Pilot can remotely insert weather data in a flight.
* Verify that a Pilot can remotely test/validate a flight plan.
* Verify that remote Pilot access enforces the same business rules as local access.
* Verify that a non-Pilot user cannot execute Pilot operations remotely.
* Verify that the client cannot access the database directly.

---

### 4.5. Acceptance Tests

**Test 1:** Successful Pilot remote authentication

* **Given** the embedded TCP server application is running
* **And** a Pilot has valid credentials
* **When** the Pilot connects using the Air Transport Company App
* **And** submits valid authentication data
* **Then** the system should authenticate the Pilot successfully
* **And** display the available Pilot operations

**Test 2:** Authenticated user is not a Pilot

* **Given** the embedded TCP server application is running
* **And** a non-Pilot user has valid credentials
* **When** the user connects using the Air Transport Company App
* **And** tries to access Pilot operations
* **Then** the system should reject the request

**Test 3:** Remote operation through TCP only

* **Given** the Air Transport Company App is connected to the system
* **When** the Pilot executes a remote operation
* **Then** the request should be sent through the TCP connection
* **And** the client application should not directly access the database

**Test 4:** Remote authorization enforcement

* **Given** an authenticated Pilot without permission for a specific Pilot operation
* **When** the Pilot tries to execute that operation remotely
* **Then** the system should reject the request

**Test 5:** Remote access to Pilot user stories

* **Given** an authenticated and authorized Pilot
* **When** the Pilot uses the Air Transport Company App
* **Then** all Pilot user stories should be available remotely

**Test 6:** Remote create flight plan

* **Given** an authenticated and authorized Pilot
* **And** valid flight plan data
* **When** the Pilot creates a flight plan remotely
* **Then** the system should execute the same business rules as the local create flight plan operation

**Test 7:** Remote test/validate flight plan

* **Given** an authenticated and authorized Pilot
* **And** an existing flight plan belonging to that Pilot
* **When** the Pilot tests the flight plan remotely
* **Then** the system should execute the flight plan validation workflow

**Test 8:** Unsupported remote operation

* **Given** an authenticated Pilot
* **When** the client sends an unsupported operation identifier
* **Then** the server should return a meaningful error response

**Test 9:** Malformed remote request

* **Given** the embedded TCP server application is running
* **When** the client sends a malformed request
* **Then** the server should return a meaningful error response
* **And** the server should remain available

**Test 10:** Safe connection closure

* **Given** the Air Transport Company App is connected to the embedded TCP server
* **When** the client exits
* **Then** the TCP connection should be closed safely

---

### 4.6. Implementation Status

Not implemented yet.