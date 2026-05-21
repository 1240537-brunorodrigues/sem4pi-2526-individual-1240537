# US031 - Register Users

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of backoffice users by an Administrator.

The implementation should include:

* validation of required user data;
* validation of email format and accepted domain;
* verification of email uniqueness;
* assignment of at least one role;
* registration of security clearance information;
* registration of skills assessment information;
* persistence of the new user;
* support for registration through bootstrap.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterUserUI`
* `RegisterUserController`
* `RegisterUserService`
* `RegisterUserRequest`
* `UserDTO`
* `BootstrapUserLoader`
* `User`
* `Email`
* `PhoneNumber`
* `Credential`
* `Role`
* `Permission`
* `SecurityClearance`
* `SkillsAssessment`
* `UserRepository`
* `RoleRepository`
* `InMemoryUserRepository`
* `InMemoryRoleRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that a user can be registered with valid data.
* Verify that a user cannot be registered with an invalid email.
* Verify that a user cannot be registered with an email from an invalid domain.
* Verify that a user cannot be registered with a duplicated email.
* Verify that a user cannot be registered without a name.
* Verify that a user cannot be registered without a phone number.
* Verify that a user cannot be registered without a role.
* Verify that a user cannot be registered with an unknown role.
* Verify that a user cannot be registered without security clearance information.
* Verify that a user cannot be registered without skills assessment information.
* Verify that bootstrap user registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful user registration

* **Given** an authenticated Administrator
* **And** valid user data
* **When** the Administrator registers a new user
* **Then** the system should create and store the user

**Test 2:** Duplicate email

* **Given** an authenticated Administrator
* **And** an existing user with a given email
* **When** the Administrator registers another user with the same email
* **Then** the system should reject the registration

**Test 3:** Invalid email

* **Given** an authenticated Administrator
* **And** user data with an invalid email
* **When** the Administrator registers the user
* **Then** the system should reject the registration

**Test 4:** Missing role

* **Given** an authenticated Administrator
* **And** user data without a role
* **When** the Administrator registers the user
* **Then** the system should reject the registration

**Test 5:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register users
* **When** the user tries to register a new backoffice user
* **Then** the system should deny access

**Test 6:** Bootstrap registration

* **Given** valid bootstrap user data
* **When** the system bootstrap process runs
* **Then** the default users should be registered

### 4.5. Implementation Status

Implemented.

Current implementation includes:

* user registration through `RegisterUserService`;
* registration request representation through `RegisterUserRequest`;
* controller support through `RegisterUserController`;
* console interaction through `RegisterUserUI`;
* duplicate email validation;
* role resolution through `RoleRepository`;
* user persistence through `UserRepository`;
* credential creation from the provided password;
* security clearance assignment;
* skills assessment assignment with a configurable validity period;
* authorization check before registration;
* bootstrap support for default users, roles and permissions.

Current implementation intentionally does not use `UserDTO`. The registered `User` is returned directly by the application service and controller.