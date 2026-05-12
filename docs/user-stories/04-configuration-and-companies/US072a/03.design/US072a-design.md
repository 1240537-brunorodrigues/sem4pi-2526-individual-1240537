# US072a - List Fleet by Model

## 3. Design

### 3.1. Responsibility Assignment

The fleet by model listing process is divided between the following components:

* **ListFleetByModelUI:** interacts with the Air Transport Company Collaborator and collects the selected company and model criteria.
* **ListFleetByModelController:** receives the list request from the UI.
* **ListFleetByModelService:** coordinates authorization, company validation, collaborator validation, model validation and fleet retrieval.
* **AuthorizationService:** verifies if the current user has permission to list the company fleet.
* **AirTransportCompanyRepository:** retrieves the selected company.
* **CustomerCollaboratorRepository:** verifies that the current user belongs to the selected company.
* **AircraftModelRepository:** retrieves the selected aircraft model.
* **AircraftRepository:** retrieves the aircraft belonging to the company and matching the model criteria.
* **AircraftDTO:** transports aircraft information to the UI.
* **Aircraft:** domain entity representing an aircraft in the fleet.

---

### 3.2. Class Diagram

![Class Diagram](svg/US072a-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US072a-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for collecting input and displaying fleet data.
* **Controller:** receives and delegates the request.
* **Service:** coordinates authorization and data retrieval.
* **Repository:** abstracts company, collaborator, model and aircraft lookup.
* **DTO:** transfers aircraft data to the UI.
* **Read-only Query:** retrieves data without modifying domain state.
* **Query Criteria:** model criteria defines which aircraft are returned.

---

### 3.5. Design Remarks

* The UI must not access repositories directly.
* The Controller should not contain business rules.
* The Service should coordinate authorization, validation and retrieval.
* The collaborator must belong to the company whose fleet is being listed.
* The aircraft model filter should be validated before querying aircraft.
* This user story should reuse the same output structure as US072 when possible.
* The operation must be read-only.