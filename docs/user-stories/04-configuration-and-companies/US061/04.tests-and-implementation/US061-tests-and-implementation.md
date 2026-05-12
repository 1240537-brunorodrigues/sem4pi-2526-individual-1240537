# US061 - Add a Customer's Collaborator

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of a customer's collaborator.

The implementation should include:

* authorization validation for the Backoffice Operator;
* lookup of the selected customer;
* validation of collaborator required data;
* validation of collaborator email;
* verification that the collaborator email is unique among system users;
* creation of a system user for the collaborator;
* creation of the customer collaborator association;
* persistence of the user and collaborator;
* support for registering customer collaborators through bootstrap.

The system must not validate whether the collaborator email belongs to the customer's domain.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `AddCustomerCollaboratorUI`
* `AddCustomerCollaboratorController`
* `AddCustomerCollaboratorService`
* `AddCustomerCollaboratorRequest`
* `CustomerCollaboratorDTO`
* `BootstrapCustomerCollaboratorLoader`
* `Customer`
* `AirTransportCompany`
* `AirControlArea`
* `CustomerCollaborator`
* `User`
* `Email`
* `PhoneNumber`
* `Role`
* `Permission`
* `AuthorizationService`
* `CustomerRepository`
* `UserRepository`
* `CustomerCollaboratorRepository`
* `InMemoryCustomerRepository`
* `InMemoryUserRepository`
* `InMemoryCustomerCollaboratorRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that a customer collaborator can be registered with valid data.
* Verify that an unauthorized user cannot register a customer collaborator.
* Verify that a collaborator cannot be registered for a non-existing customer.
* Verify that a collaborator cannot be registered without an email.
* Verify that a collaborator cannot be registered with an invalid email.
* Verify that a collaborator cannot be registered with an email already used by another system user.
* Verify that a collaborator cannot be registered without a name.
* Verify that a collaborator cannot be registered without a phone number.
* Verify that a collaborator cannot be registered without a position.
* Verify that the collaborator is created as a system user.
* Verify that the collaborator is associated with the selected customer.
* Verify that the collaborator email is not required to belong to the customer's domain.
* Verify that bootstrap registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful customer collaborator registration

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** valid collaborator data
* **When** the Backoffice Operator registers the collaborator
* **Then** the system should create a system user for the collaborator
* **And** associate the collaborator with the selected customer

**Test 2:** Non-existing customer

* **Given** an authenticated Backoffice Operator
* **And** collaborator data referencing a non-existing customer
* **When** the Backoffice Operator registers the collaborator
* **Then** the system should reject the registration

**Test 3:** Duplicated email

* **Given** an authenticated Backoffice Operator
* **And** an existing system user with a given email
* **When** the Backoffice Operator registers a collaborator with the same email
* **Then** the system should reject the registration

**Test 4:** Email outside customer domain

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** collaborator data with an email outside the customer's main domain
* **When** the Backoffice Operator registers the collaborator
* **Then** the system should accept the email if it is otherwise valid and unique

**Test 5:** Missing position

* **Given** an authenticated Backoffice Operator
* **And** collaborator data without a position
* **When** the Backoffice Operator registers the collaborator
* **Then** the system should reject the registration

**Test 6:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register customer collaborators
* **When** the user tries to register a customer collaborator
* **Then** the system should deny access

**Test 7:** Bootstrap registration

* **Given** valid bootstrap customer collaborator data
* **When** the bootstrap process runs
* **Then** the default customer collaborators should be registered

---

### 4.5. Implementation Status

Not implemented yet.