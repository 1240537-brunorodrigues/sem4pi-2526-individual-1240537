# US032 - Disable/Enable Users

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the ability to disable and enable backoffice users.

The implementation should include:

* searching users by email;
* checking if the Administrator has permission to manage user status;
* disabling an enabled user;
* enabling a disabled user;
* saving the updated user status;
* preventing disabled users from authenticating.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ManageUserStatusUI`
* `ManageUserStatusController`
* `ManageUserStatusService`
* `UserDTO`
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

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an enabled user can be disabled.
* Verify that a disabled user can be enabled.
* Verify that disabling a user does not delete the user.
* Verify that enabling a user does not change other user information.
* Verify that a disabled user cannot authenticate.
* Verify that a non-existing user cannot be disabled.
* Verify that a non-existing user cannot be enabled.
* Verify that a user without permission cannot disable another user.
* Verify that a user without permission cannot enable another user.

---

### 4.4. Acceptance Tests

**Test 1:** Disable an enabled user

* **Given** an authenticated Administrator
* **And** an enabled registered user
* **When** the Administrator disables the user
* **Then** the user status should become disabled

**Test 2:** Disabled user cannot authenticate

* **Given** a disabled registered user
* **When** the user tries to authenticate
* **Then** the system should reject authentication

**Test 3:** Enable a disabled user

* **Given** an authenticated Administrator
* **And** a disabled registered user
* **When** the Administrator enables the user
* **Then** the user status should become enabled

**Test 4:** Non-existing user

* **Given** an authenticated Administrator
* **When** the Administrator tries to disable or enable a non-existing user
* **Then** the system should display a user not found error

**Test 5:** Unauthorized status change

* **Given** an authenticated user without permission to manage users
* **When** the user tries to disable or enable another user
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.