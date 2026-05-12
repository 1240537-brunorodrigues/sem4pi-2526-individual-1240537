# US072b - List Fleet by Maker

## 3. Design

### 3.1. Responsibility Assignment

The fleet by maker listing process is divided between the following components:

* **ListFleetByMakerUI:** interacts with the Air Transport Company Collaborator and collects the selected company and maker criteria.
* **ListFleetByMakerController:** receives the list request from the UI.
* **ListFleetByMakerService:** coordinates authorization, company validation, collaborator validation, maker validation and fleet retrieval.
* **AuthorizationService:** verifies if the current user has permission to list the company fleet.
* **AirTransportCompanyRepository:** retrieves the selected company.
* **CustomerCollaboratorRepository:** verifies that the current user belongs to the selected company.
* **ManufacturerRepository:** retrieves the selected aircraft maker/manufacturer.
* **AircraftRepository:** retrieves the aircraft belonging to the company and matching the maker criteria.
* **AircraftDTO:** transports aircraft information to the UI.
* **Aircraft:** domain entity representing an aircraft in the fleet.

---

### 3.2. Class Diagram

![Class Diagram](svg/US072b-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US072b-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for collecting input and displaying fleet data.
* **Controller:** receives and delegates the request.
* **Service:** coordinates authorization and data retrieval.
* **Repository:** abstracts company, collaborator, maker and aircraft lookup.
* **DTO:** transfers aircraft data to the UI.
* **Read-only Query:** retrieves data without modifying domain state.
* **Query Criteria:** maker criteria defines which aircraft are returned.

---

### 3.5. Design Remarks

* The UI must not access repositories directly.
* The Controller should not contain business rules.
* The Service should coordinate authorization, validation and retrieval.
* The collaborator must belong to the company whose fleet is being listed.
* The maker filter should be validated before querying aircraft.
* This user story should reuse the same output structure as US072 when possible.
* The operation must be read-only.