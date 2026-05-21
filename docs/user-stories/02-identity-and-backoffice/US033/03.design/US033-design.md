# US033 - List Users

## 3. Design

### 3.1. Responsibility Assignment

The list users process is divided between the following components:

* **ListUsersUI:** interacts with the Administrator and displays the list of users.
* **ListUsersController:** receives the request from the UI and delegates it to the application service.
* **ListUsersService:** checks authorization, retrieves all users and returns them sorted by email.
* **AuthorizationService:** verifies whether the currently authenticated user has permission to list users.
* **UserRepository:** retrieves the registered users.
* **User:** domain entity representing a system user.
* **Role:** represents the roles assigned to each user.

---

### 3.2. Class Diagram

![Class Diagram](svg/US033-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US033-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for interacting with the Administrator and displaying results.
* **Controller:** receives the list request and delegates application logic.
* **Service:** coordinates authorization and retrieval of users.
* **Repository:** abstracts access to stored users.
* **Entity:** represents users in the domain model.
* **Authorization Guard:** access to this operation is checked before retrieving user data.

---

### 3.5. Design Remarks

* The UI must not access the repository directly.
* The listing operation must not modify users.
* Disabled users must not be filtered out by default, because their status must be visible to the Administrator.
* Users are returned sorted by email.
* Filters may be introduced later without changing the main domain model.
* The current implementation does not use DTOs; it returns `List<User>` directly.