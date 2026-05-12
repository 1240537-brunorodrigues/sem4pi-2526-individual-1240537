# US063 - Edit a Customer's Collaborator

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the edition of a customer's collaborator contact information.

The implementation should include:

* authorization validation for the Backoffice Operator;
* lookup of the selected customer;
* lookup of the selected collaborator;
* validation that the collaborator belongs to the selected customer;
* validation of the new email;
* uniqueness verification of the new email;
* validation of the new phone number;
* update of the collaborator's contact information;
* update of the corresponding system user information;
* persistence of the updated collaborator and user.

Only email and phone number can be changed.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `EditCustomerCollaboratorUI`
* `EditCustomerCollaboratorController`
* `EditCustomerCollaboratorService`
* `EditCustomerCollaboratorRequest`
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

* Verify that an authorized Backoffice Operator can edit a collaborator's email and phone number.
* Verify that an unauthorized user cannot edit a collaborator.
* Verify that a collaborator cannot be edited for a non-existing customer.
* Verify that a non-existing collaborator cannot be edited.
* Verify that a collaborator cannot be edited if they do not belong to the selected customer.
* Verify that an invalid new email is rejected.
* Verify that a duplicated new email is rejected.
* Verify that an invalid new phone number is rejected.
* Verify that the collaborator's name remains unchanged.
* Verify that the collaborator's position remains unchanged.
* Verify that the collaborator's customer association remains unchanged.
* Verify that the corresponding system user email and phone number are updated.
* Verify that failed update does not change collaborator or user data.

---

### 4.4. Acceptance Tests

**Test 1:** Successful collaborator contact update

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** an existing collaborator associated with that customer
* **When** the Backoffice Operator updates the collaborator's email and phone number
* **Then** the collaborator contact information should be updated
* **And** the corresponding system user should also be updated

**Test 2:** Attempt to change non-editable information

* **Given** an authenticated Backoffice Operator
* **And** an existing collaborator
* **When** the Backoffice Operator tries to change the collaborator's name, position or customer association
* **Then** the system should reject or ignore those changes
* **And** only email and phone number may be updated

**Test 3:** Duplicated email

* **Given** an authenticated Backoffice Operator
* **And** an existing system user with a given email
* **When** the Backoffice Operator tries to update a collaborator to use that email
* **Then** the system should reject the update

**Test 4:** Non-existing collaborator

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** a non-existing collaborator
* **When** the Backoffice Operator tries to edit that collaborator
* **Then** the system should display a collaborator not found message

**Test 5:** Collaborator does not belong to customer

* **Given** an authenticated Backoffice Operator
* **And** a collaborator associated with another customer
* **When** the Backoffice Operator tries to edit that collaborator for the selected customer
* **Then** the system should reject the operation

**Test 6:** Unauthorized edit attempt

* **Given** an authenticated user without permission to edit customer collaborators
* **When** the user tries to edit a collaborator
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.