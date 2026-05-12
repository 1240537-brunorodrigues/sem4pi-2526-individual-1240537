# US072 - List an Air Transport Company's Fleet

## 3. Design

### 3.1. Responsibility Assignment

The company fleet listing process is divided between the following components:

* **ListCompanyFleetUI:** interacts with the Air Transport Company Collaborator and collects the selected company.
* **ListCompanyFleetController:** receives the list request from the UI.
* **ListCompanyFleetService:** coordinates authorization, company validation, collaborator validation and fleet retrieval.
* **AuthorizationService:** verifies if the current user has permission to list the company fleet.
* **AirTransportCompanyRepository:** retrieves the selected company.
* **CustomerCollaboratorRepository:** verifies that the current user belongs to the selected company.
* **AircraftRepository:** retrieves the aircraft belonging to the company fleet.
* **AircraftDTO:** transports aircraft information to the UI.
* **Aircraft:** domain entity representing an aircraft in the fleet.

---

### 3.2. Class Diagram

![Class Diagram](svg/US072-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US072-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for collecting input and displaying fleet data.
* **Controller:** receives and delegates the request.
* **Service:** coordinates authorization and data retrieval.
* **Repository:** abstracts company, collaborator and aircraft lookup.
* **DTO:** transfers aircraft data to the UI.
* **Read-only Query:** retrieves data without modifying domain state.

---

### 3.5. Design Remarks

* The UI must not access repositories directly.
* The Controller should not contain business rules.
* The Service should coordinate authorization and retrieval.
* The collaborator must belong to the company whose fleet is being listed.
* The listing should include decommissioned aircraft, because they remain in the fleet.
* Specialized fleet listings should reuse the same base DTO or query model where possible.