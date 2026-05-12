# US064 - Disable a Customer's Collaborator

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the disabling of a customer's collaborator.

The implementation should include:

* authorization validation for the Backoffice Operator;
* lookup of the selected customer;
* lookup of the selected collaborator;
* validation that the collaborator belongs to the selected customer;
* validation that the collaborator is active;
* disabling of the collaborator;
* disabling of the corresponding system user or prevention of future authentication;
* persistence of the updated collaborator and user.

This operation must not delete the collaborator or the corresponding system user.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `DisableCustomerCollaboratorUI`
* `DisableCustomerCollaboratorController`
* `DisableCustomerCollaboratorService`
* `DisableCustomerCollaboratorRequest`
* `CustomerCollaboratorDTO`
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
* `CustomerCollaboratorRepository`
* `UserRepository`
* `InMemoryCustomerRepository`
* `InMemoryCustomerCollaboratorRepository`
* `InMemoryUserRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Backoffice Operator can disable an active customer collaborator.
* Verify that an unauthorized user cannot disable a customer collaborator.
* Verify that a collaborator cannot be disabled for a non-existing customer.
* Verify that a non-existing collaborator cannot be disabled.
* Verify that a collaborator cannot be disabled if they do not belong to the selected customer.
* Verify that an already disabled collaborator cannot be disabled again or returns an appropriate result.
* Verify that disabling a collaborator does not delete the collaborator.
* Verify that disabling a collaborator disables or blocks the corresponding system user.
* Verify that a disabled collaborator is not returned by active collaborator listings.
* Verify that failed disable operation does not change collaborator or user data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful collaborator disabling

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** an active collaborator associated with that customer
* **When** the Backoffice Operator disables the collaborator
* **Then** the collaborator status should become disabled
* **And** the corresponding system user should no longer be able to use the system

**Test 2:** Disabled collaborator is not listed

* **Given** an authenticated Backoffice Operator
* **And** a disabled collaborator
* **When** the Backoffice Operator lists the customer's collaborators
* **Then** the disabled collaborator should not appear in the active collaborators list

**Test 3:** Non-existing customer

* **Given** an authenticated Backoffice Operator
* **And** a non-existing customer
* **When** the Backoffice Operator tries to disable a collaborator for that customer
* **Then** the system should display a customer not found message

**Test 4:** Non-existing collaborator

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** a non-existing collaborator
* **When** the Backoffice Operator tries to disable that collaborator
* **Then** the system should display a collaborator not found message

**Test 5:** Collaborator does not belong to customer

* **Given** an authenticated Backoffice Operator
* **And** a collaborator associated with another customer
* **When** the Backoffice Operator tries to disable that collaborator for the selected customer
* **Then** the system should reject the operation

**Test 6:** Already disabled collaborator

* **Given** an authenticated Backoffice Operator
* **And** a collaborator that is already disabled
* **When** the Backoffice Operator tries to disable that collaborator again
* **Then** the system should display an appropriate message

**Test 7:** Unauthorized disable attempt

* **Given** an authenticated user without permission to disable customer collaborators
* **When** the user tries to disable a collaborator
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.