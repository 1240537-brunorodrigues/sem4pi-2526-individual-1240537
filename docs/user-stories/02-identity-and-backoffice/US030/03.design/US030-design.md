# US030 - Authentication and Authorization

## 3. Design

### 3.1. Responsibility Assignment

The authentication and authorization process is divided between the following components:

* **AuthenticationUI:** interacts with the user and collects authentication credentials.
* **AuthenticationController:** receives authentication requests from the UI and delegates them to the application service.
* **AuthenticationService:** validates user credentials, verifies user access conditions and stores the authenticated user in the current session.
* **AuthenticatedUserSession:** keeps the currently authenticated user during application execution.
* **AuthorizationService:** verifies whether the currently authenticated user has the required permission to execute a protected functionality.
* **UserRepository:** retrieves user information by email.
* **User:** domain entity that represents a system user and centralizes user-related access checks.
* **Credential:** domain object responsible for password validation.
* **Role:** represents a set of permissions assigned to a user.
* **Permission:** represents an action that may be executed by an authorized user.
* **SecurityClearance:** represents the security clearance validity condition.
* **SkillsAssessment:** represents the periodic skills assessment validity condition.

---

### 3.2. Class Diagram

![Class Diagram](svg/US030-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US030-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for user interaction.
* **Controller:** coordinates requests from the UI and delegates them to application services.
* **Service:** contains application logic for authentication and authorization.
* **Repository:** abstracts access to user persistence.
* **Entity:** represents domain objects with identity.
* **Value Object:** represents immutable domain values such as email, credential, permissions and access validity data.
* **Session:** stores the currently authenticated user during the application execution.
* **Policy/Guard:** authorization rules are centralized in `AuthorizationService` through permission checks.

---

### 3.5. Design Remarks

* The UI must not access repositories directly.
* The controller must not contain business rules.
* Authentication and authorization logic should be centralized in application services.
* Passwords should not be stored in plain text.
* The authorization model supports multiple roles per user through a set of roles.
* Authentication validates credentials, user status, security clearance and skills assessment.
* Authorization is based on permissions granted through the authenticated user's roles.
* This design allows the authentication and authorization mechanisms to be reused by future user interfaces or remote clients.