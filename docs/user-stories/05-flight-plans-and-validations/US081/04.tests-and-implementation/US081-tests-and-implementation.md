# US081 - Create a Flight Plan from a File

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the import of a flight plan from a file.

The implementation should include:

* authorization validation for the Pilot;
* validation that the file exists;
* validation that the file is readable;
* validation or detection of the file format;
* validation that the format is supported;
* lexical analysis of the Core Flight DSL file;
* syntactic analysis of the Core Flight DSL file;
* mapping to an internal representation;
* semantic analysis of the internal representation;
* conversion into a `CreateFlightPlanRequest`;
* reuse of the `CreateFlightPlanService` from US080;
* meaningful error reporting;
* prevention of persistence when validation fails.

Only valid flight plans may be imported and used by the system.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `CreateFlightPlanFromFileUI`
* `CreateFlightPlanFromFileController`
* `CreateFlightPlanFromFileService`
* `FlightPlanFileImportRequest`
* `FlightPlanImportDTO`
* `ValidationErrorDTO`
* `FlightPlanFileReader`
* `FlightPlanFormatDetector`
* `CoreFlightDslLexer`
* `CoreFlightDslParser`
* `CoreFlightDslSemanticValidator`
* `FlightPlanAstMapper`
* `FlightPlanDslModel`
* `ValidationError`
* `ValidationErrorType`
* `CreateFlightPlanService`
* `CreateFlightPlanRequest`
* `FlightPlanDTO`
* `AuthorizationService`
* `User`
* `Role`
* `Permission`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Pilot can import a valid flight plan file.
* Verify that an unauthorized user cannot import a flight plan file.
* Verify that a missing file is rejected.
* Verify that an unreadable file is rejected.
* Verify that an unsupported file format is rejected.
* Verify that a file not conforming to the Core Flight DSL is rejected.
* Verify that lexical errors are detected.
* Verify that syntactic errors are detected.
* Verify that semantic errors are detected.
* Verify that lexical errors produce meaningful error messages.
* Verify that syntactic errors produce meaningful error messages.
* Verify that semantic errors produce meaningful error messages.
* Verify that a valid DSL file is mapped to an internal representation.
* Verify that a valid internal representation is converted into a `CreateFlightPlanRequest`.
* Verify that valid imported flight plans reuse the same rules as US080.
* Verify that a valid imported flight plan is stored with status `draft`.
* Verify that invalid files do not create flight plans.
* Verify that failed imports do not change repositories.

---

### 4.4. Acceptance Tests

**Test 1:** Successful flight plan import

* **Given** an authenticated Pilot
* **And** a readable file conforming to the Core Flight DSL
* **And** the file describes a semantically valid flight plan
* **When** the Pilot imports the file
* **Then** the system should create the flight plan
* **And** the flight plan should be stored
* **And** the flight plan status should be `draft`

**Test 2:** Lexical error

* **Given** an authenticated Pilot
* **And** a file containing invalid DSL tokens
* **When** the Pilot imports the file
* **Then** the system should reject the file
* **And** display a meaningful lexical error message

**Test 3:** Syntactic error

* **Given** an authenticated Pilot
* **And** a file with invalid DSL structure
* **When** the Pilot imports the file
* **Then** the system should reject the file
* **And** display a meaningful syntactic error message

**Test 4:** Semantic error

* **Given** an authenticated Pilot
* **And** a syntactically valid file containing invalid domain references or business rule violations
* **When** the Pilot imports the file
* **Then** the system should reject the file
* **And** display a meaningful semantic error message

**Test 5:** Unsupported file format

* **Given** an authenticated Pilot
* **And** a file format not supported by the system
* **When** the Pilot imports the file
* **Then** the system should reject the file

**Test 6:** Invalid file does not persist data

* **Given** an authenticated Pilot
* **And** an invalid flight plan file
* **When** the Pilot imports the file
* **Then** no flight plan should be created
* **And** no flight plan should be stored

**Test 7:** Imported flight plan follows US080 rules

* **Given** an authenticated Pilot
* **And** a readable file conforming to the Core Flight DSL
* **And** the file describes a flight plan that violates US080 business rules
* **When** the Pilot imports the file
* **Then** the system should reject the import

**Test 8:** Unauthorized operation

* **Given** an authenticated user without permission to import flight plans
* **When** the user tries to import a flight plan file
* **Then** the system should deny access

---

### 4.5. Implementation Status

Not implemented yet.