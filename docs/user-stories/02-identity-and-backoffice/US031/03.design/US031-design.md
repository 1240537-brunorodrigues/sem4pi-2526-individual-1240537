# US031 - Register Users

## 3. Design

### 3.1. Responsibility Assignment

The user registration process is divided between the following components:

* **RegisterUserUI:** interacts with the Administrator and collects the new user's data.
* **RegisterUserController:** receives the registration request from the UI.
* **RegisterUserService:** coordinates the user registration process.
* **AuthorizationService:** verifies if the current user has permission to register users.
* **UserRepository:** checks if the email already exists and stores the new user.
* **RoleRepository:** retrieves and validates selected roles.
* **User:** domain entity representing a system user.
* **Email:** value object responsible for email validation.
* **SecurityClearance:** value object/entity responsible for clearance validity.
* **SkillsAssessment:** value object/entity responsible for assessment validity.

---

### 3.2. Class Diagram

![Class Diagram](svg/US031-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US031-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for interacting with the Administrator.
* **Controller:** receives and delegates the registration request.
* **Service:** coordinates authorization, validation and persistence.
* **Repository:** abstracts persistence operations.
* **Entity:** represents users with identity.
* **Value Object:** represents immutable domain values such as email and phone number.
* **Factory:** may be used to create valid User instances.
* **Bootstrap:** supports automatic registration of initial users.

---

### 3.5. Design Remarks

* The UI must not validate business rules directly.
* The Controller must not access repositories directly if an application service exists.
* User creation should be centralized to guarantee that all invariants are enforced.
* Manual registration and bootstrap registration should reuse the same domain rules.
* Password storage should avoid plain text.
* The model should support the possibility of multiple roles per user.