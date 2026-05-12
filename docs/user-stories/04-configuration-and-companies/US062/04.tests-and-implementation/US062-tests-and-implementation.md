# US062 - List Customer's Collaborators

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the listing of active collaborators for a selected customer.

The implementation should include:

* authorization validation for the Backoffice Operator;
* lookup of the selected customer;
* retrieval of active collaborators associated with that customer;
* filtering out disabled collaborators;
* mapping collaborators to DTOs;
* presentation of an empty list message when no active collaborators exist.

This operation must be read-only.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `ListCustomerCollaboratorsUI`
* `ListCustomerCollaboratorsController`
* `ListCustomerCollaboratorsService`
* `ListCustomerCollaboratorsRequest`
* `CustomerCollaboratorDTO`
* `Customer`
* `AirTransportCompany`
* `AirControlArea`
* `CustomerCollaborator`
* `User`
* `Email`
* `Role`
* `Permission`
* `AuthorizationService`
* `CustomerRepository`
* `CustomerCollaboratorRepository`
* `InMemoryCustomerRepository`
* `InMemoryCustomerCollaboratorRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Backoffice Operator can list a customer's active collaborators.
* Verify that an unauthorized user cannot list customer collaborators.
* Verify that collaborators cannot be listed for a non-existing customer.
* Verify that only active collaborators are returned.
* Verify that disabled collaborators are not returned.
* Verify that an empty list is returned when the customer has no active collaborators.
* Verify that the returned collaborator data includes email, name and position.
* Verify that the listing operation does not modify collaborator data.
* Verify that the listing operation works for an air transport company customer.
* Verify that the listing operation works for an air control area customer.

---

### 4.4. Acceptance Tests

**Test 1:** List active collaborators successfully

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** the customer has active collaborators
* **When** the Backoffice Operator lists the customer's collaborators
* **Then** the system should display the active collaborators

**Test 2:** Disabled collaborators are not listed

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** the customer has active and disabled collaborators
* **When** the Backoffice Operator lists the customer's collaborators
* **Then** only active collaborators should be displayed

**Test 3:** Customer with no active collaborators

* **Given** an authenticated Backoffice Operator
* **And** an existing customer
* **And** the customer has no active collaborators
* **When** the Backoffice Operator lists the customer's collaborators
* **Then** the system should display an empty list message

**Test 4:** Non-existing customer

* **Given** an authenticated Backoffice Operator
* **And** a non-existing customer
* **When** the Backoffice Operator tries to list collaborators
* **Then** the system should display a customer not found message

**Test 5:** Unauthorized listing attempt

* **Given** an authenticated user without permission to list customer collaborators
* **When** the user tries to list a customer's collaborators
* **Then** the system should deny access

**Test 6:** Read-only operation

* **Given** an existing customer with collaborators
* **When** the Backoffice Operator lists the collaborators
* **Then** no collaborator data should be changed

---

### 4.5. Implementation Status

Not implemented yet.