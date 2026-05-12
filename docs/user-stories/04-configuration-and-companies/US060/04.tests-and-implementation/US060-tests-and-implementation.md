# US060 - Register an Air Transport Company

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the registration of air transport companies.

The implementation should include:

* authorization validation for the Backoffice Operator;
* validation of company name;
* validation of IATA code;
* validation of ICAO code;
* uniqueness verification of IATA and ICAO codes;
* creation of an air transport company;
* persistence of the registered air transport company;
* support for registering air transport companies through bootstrap.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `RegisterAirTransportCompanyUI`
* `RegisterAirTransportCompanyController`
* `RegisterAirTransportCompanyService`
* `RegisterAirTransportCompanyRequest`
* `AirTransportCompanyDTO`
* `BootstrapAirTransportCompanyLoader`
* `AirTransportCompany`
* `CompanyName`
* `AirlineIATACode`
* `AirlineICAOCode`
* `User`
* `Permission`
* `AuthorizationService`
* `AirTransportCompanyRepository`
* `InMemoryAirTransportCompanyRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an air transport company can be registered with valid data.
* Verify that an unauthorized user cannot register an air transport company.
* Verify that an air transport company cannot be registered without a company name.
* Verify that an air transport company cannot be registered without an IATA code.
* Verify that an air transport company cannot be registered without an ICAO code.
* Verify that an invalid IATA code is rejected.
* Verify that an invalid ICAO code is rejected.
* Verify that a duplicated IATA code is rejected.
* Verify that a duplicated ICAO code is rejected.
* Verify that a successfully registered air transport company is stored.
* Verify that failed registration does not change the repository.
* Verify that bootstrap registration follows the same validation rules as manual registration.

---

### 4.4. Acceptance Tests

**Test 1:** Successful air transport company registration

* **Given** an authenticated Backoffice Operator
* **And** valid air transport company data
* **When** the Backoffice Operator registers the company
* **Then** the system should store the air transport company

**Test 2:** Duplicated IATA code

* **Given** an authenticated Backoffice Operator
* **And** an existing air transport company with a given IATA code
* **When** the Backoffice Operator registers another company with the same IATA code
* **Then** the system should reject the registration

**Test 3:** Duplicated ICAO code

* **Given** an authenticated Backoffice Operator
* **And** an existing air transport company with a given ICAO code
* **When** the Backoffice Operator registers another company with the same ICAO code
* **Then** the system should reject the registration

**Test 4:** Invalid IATA code

* **Given** an authenticated Backoffice Operator
* **And** company data with an invalid IATA code
* **When** the Backoffice Operator registers the company
* **Then** the system should reject the registration

**Test 5:** Invalid ICAO code

* **Given** an authenticated Backoffice Operator
* **And** company data with an invalid ICAO code
* **When** the Backoffice Operator registers the company
* **Then** the system should reject the registration

**Test 6:** Unauthorized registration attempt

* **Given** an authenticated user without permission to register air transport companies
* **When** the user tries to register an air transport company
* **Then** the system should deny access

**Test 7:** Bootstrap registration

* **Given** valid bootstrap air transport company data
* **When** the bootstrap process runs
* **Then** the default air transport companies should be registered

---

### 4.5. Implementation Status

Not implemented yet.