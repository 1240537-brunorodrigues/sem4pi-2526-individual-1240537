# US032 - Disable/Enable Users

## 3. Design

### 3.1. Responsibility Assignment

The disable/enable user process is divided between the following components:

* **ManageUserStatusUI:** interacts with the Administrator and collects the target user and intended operation.
* **ManageUserStatusController:** receives the request from the UI.
* **ManageUserStatusService:** coordinates authorization, user lookup and status update.
* **AuthorizationService:** verifies if the current user has permission to manage user status.
* **UserRepository:** retrieves and saves the target user.
* **User:** domain entity responsible for changing its own status.

---

### 3.2. Class Diagram

![Class Diagram](svg/US032-CD.svg)

---

### 3.3. Sequence Diagram

![Sequence Diagram](svg/US032-SD.svg)

---

### 3.4. Applied Patterns

* **UI:** responsible for user interaction.
* **Controller:** receives the request and delegates application logic.
* **Service:** coordinates the use case.
* **Repository:** abstracts persistence operations.
* **Entity:** `User` owns its status-changing behavior.
* **Value Object:** `Email` identifies the user.
* **Authorization Guard:** access to this operation is checked before changing user state.

---

### 3.5. Design Remarks

* The UI must not change the user status directly.
* The Controller should not access persistence directly.
* The User entity should expose methods such as `enable()` and `disable()`.
* Disabling a user should not remove it from the repository.
* Authorization should be checked before modifying the target user.
* This functionality must be consistent with authentication rules from US030.