# US078 - Air Transport Company Collaborator Remote Access

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement remote access for Air Transport Company Collaborators through the Air Transport Company App.

The implementation should include:

* a TCP-based client application;
* an embedded TCP server application in the system;
* a request/response protocol for remote operations;
* authentication through the TCP connection;
* authorization for each remote operation;
* routing of remote requests to the corresponding application services;
* meaningful success and error responses;
* safe handling of malformed or unsupported requests;
* safe TCP connection closure;
* no direct client access to repositories or the database.

All Air Transport Company Collaborator user stories must be remotely available through the client application.

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
* `Role`
* `Permission`
* `UserRepository`
* Application services for all Air Transport Company Collaborator user stories:
    * `AddAircraftToCompanyFleetService`
    * `DecommissionAircraftService`
    * `ListCompanyFleetService`
    * `ListFleetByModelService`
    * `ListFleetByMakerService`
    * `ListFleetByCapacityService`
    * `ListFleetByAgeService`
    * `CreateFlightRouteService`
    * `DeleteFlightRouteService`
    * `AddPilotService`
    * `ListPilotRosterService`
    * `RemovePilotService`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that the TCP client can establish a connection with the embedded server.
* Verify that the TCP client can send a remote request.
* Verify that the embedded server can receive and decode a remote request.
* Verify that the embedded server can return a remote response.
* Verify that valid credentials produce a successful authentication response.
* Verify that invalid credentials produce an authentication error response.
* Verify that authorization is checked before executing a remote operation.
* Verify that unauthorized remote operations are rejected.
* Verify that supported operation identifiers are routed to the correct application service.
* Verify that unsupported operation identifiers return a meaningful error response.
* Verify that malformed requests return a meaningful error response.
* Verify that remote operations do not bypass application services.
* Verify that the TCP client has no direct access to repositories or the database.
* Verify that the TCP connection can be closed safely.

---

### 4.4. Integration Tests

The following integration tests should be implemented:

* Verify that an Air Transport Company Collaborator can authenticate remotely.
* Verify that an Air Transport Company Collaborator can remotely add an aircraft to their company's fleet.
* Verify that an Air Transport Company Collaborator can remotely decommission an aircraft.
* Verify that an Air Transport Company Collaborator can remotely list their company's fleet.
* Verify that an Air Transport Company Collaborator can remotely create a flight route.
* Verify that an Air Transport Company Collaborator can remotely deactivate a flight route.
* Verify that an Air Transport Company Collaborator can remotely add a pilot.
* Verify that an Air Transport Company Collaborator can remotely list the pilot roster.
* Verify that an Air Transport Company Collaborator can remotely remove a pilot.
* Verify that remote access enforces the same business rules as local access.

---

### 4.5. Acceptance Tests

**Test 1:** Successful remote authentication

* **Given** the embedded TCP server application is running
* **And** an Air Transport Company Collaborator has valid credentials
* **When** the collaborator connects using the Air Transport Company App
* **And** submits valid authentication data
* **Then** the system should authenticate the collaborator successfully

**Test 2:** Invalid remote authentication

* **Given** the embedded TCP server application is running
* **And** invalid credentials are provided
* **When** the collaborator tries to authenticate through the Air Transport Company App
* **Then** the system should reject the authentication attempt

**Test 3:** Remote operation through TCP only

* **Given** the Air Transport Company App is connected to the system
* **When** the collaborator executes a remote operation
* **Then** the request should be sent through the TCP connection
* **And** the client application should not directly access the database

**Test 4:** Remote authorization enforcement

* **Given** an authenticated user without permission for a specific Air Transport Company Collaborator operation
* **When** the user tries to execute that operation remotely
* **Then** the system should reject the request

**Test 5:** Remote access to Air Transport Company Collaborator user stories

* **Given** an authenticated and authorized Air Transport Company Collaborator
* **When** the collaborator uses the Air Transport Company App
* **Then** all Air Transport Company Collaborator user stories should be available remotely

**Test 6:** Unsupported remote operation

* **Given** an authenticated Air Transport Company Collaborator
* **When** the client sends an unsupported operation identifier
* **Then** the server should return a meaningful error response

**Test 7:** Malformed remote request

* **Given** the embedded TCP server application is running
* **When** the client sends a malformed request
* **Then** the server should return a meaningful error response
* **And** the server should remain available

**Test 8:** Safe connection closure

* **Given** the Air Transport Company App is connected to the embedded TCP server
* **When** the client exits
* **Then** the TCP connection should be closed safely

---

### 4.6. Implementation Status

Not implemented yet.