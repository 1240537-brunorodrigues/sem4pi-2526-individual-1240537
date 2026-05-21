# US033 - List Users

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the ability to list backoffice users, including their current status.

The implementation should include:

* checking if the current user has permission to list users;
* retrieving all backoffice users;
* including each user's status in the result;
* presenting enabled and disabled users;
* returning an empty list message if no users exist;
* ensuring that the operation does not modify user data.

---

### 4.2. Main Classes to Implement

Main classes:

* `ListUsersUI`
* `ListUsersController`
* `ListUsersService`
* `User`
* `Email`
* `PhoneNumber`
* `Role`
* `Permission`
* `SecurityClearance`
* `SkillsAssessment`
* `AuthorizationService`
* `UserRepository`
* `InMemoryUserRepository`

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Administrator can list users.
* Verify that an unauthorized user cannot list users.
* Verify that the returned list includes enabled users.
* Verify that the returned list includes disabled users.
* Verify that each listed user includes email, name, phone number and status.
* Verify that an empty list is returned when no users exist.
* Verify that listing users does not modify user data.
* Verify that roles are included in the returned user data, if required by the UI.

---

### 4.4. Acceptance Tests

**Test 1:** List users successfully

* **Given** an authenticated Administrator
* **And** registered backoffice users exist
* **When** the Administrator requests the user list
* **Then** the system should display the users with their status

**Test 2:** List users with disabled users

* **Given** an authenticated Administrator
* **And** at least one user is disabled
* **When** the Administrator requests the user list
* **Then** the disabled user should appear in the list with disabled status

**Test 3:** Empty user list

* **Given** an authenticated Administrator
* **And** no backoffice users exist
* **When** the Administrator requests the user list
* **Then** the system should display an empty list message

**Test 4:** Unauthorized list attempt

* **Given** an authenticated user without permission to list users
* **When** the user requests the user list
* **Then** the system should deny access

**Test 5:** Read-only operation

* **Given** registered users exist
* **When** the Administrator lists users
* **Then** no user data should be changed

---

### 4.5. Implementation Status

Implemented.

Current implementation includes:

* listing users through `ListUsersService`;
* controller support through `ListUsersController`;
* console display through `ListUsersUI`;
* retrieval of all users from `UserRepository`;
* sorting users by email;
* display of email, name, phone number, status and roles;
* authorization check before listing users;
* read-only behavior, since listing users does not modify system state.

Current implementation returns `List<User>` directly and does not use DTOs.