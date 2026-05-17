# US085 - Test/Validate Flight Plan

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the test/validation of a flight plan made by a Pilot.

The implementation should include:

* authorization validation for the Pilot;
* lookup of the selected flight plan;
* validation that the flight plan belongs to the authenticated Pilot;
* validation of the flight plan DSL description/internal representation;
* preparation of test input for the C component;
* execution of the C test component;
* parsing of the C test result;
* validation of fuel sufficiency;
* validation of minimum altitude requirements;
* validation that final fuel quantity satisfies the destination air control area's minimum final fuel requirement;
* persistence of the flight plan test result;
* update of the flight plan test status.

The test component must be implemented in C. The Java application should interact with it through a clear adapter/runner.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `TestFlightPlanUI`
* `TestFlightPlanController`
* `TestFlightPlanService`
* `TestFlightPlanRequest`
* `FlightPlanTestDTO`
* `FlightPlan`
* `FlightPlanTest`
* `FlightPlanStatus`
* `TestStatus`
* `ValidationResult`
* `FuelValidationResult`
* `AltitudeValidationResult`
* `FlightPlanValidationPolicy`
* `CoreFlightDslValidatorService`
* `FlightTestInputMapper`
* `CFlightTestRunner`
* `FlightTestInput`
* `CFlightTestOutput`
* `FlightTestResultParser`
* `FlightTestResult`
* `Pilot`
* `User`
* `Role`
* `Permission`
* `AuthorizationService`
* `FlightPlanRepository`
* `InMemoryFlightPlanRepository`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Pilot can test/validate one of their flight plans.
* Verify that an unauthorized user cannot test/validate a flight plan.
* Verify that a Pilot cannot test another Pilot's flight plan.
* Verify that a non-existing flight plan cannot be tested.
* Verify that a flight plan without a valid DSL description/internal representation is rejected.
* Verify that DSL validation is executed before the C test component.
* Verify that the C test component is called when DSL validation succeeds.
* Verify that the C test component is not called when DSL validation fails.
* Verify that a passing C test result marks the flight plan as validated.
* Verify that a failing C test result marks the flight plan as tested but failed.
* Verify that a C component execution error does not mark the flight plan as validated.
* Verify that the test result includes fuel validation details.
* Verify that the test result includes altitude validation details.
* Verify that final fuel below the destination area's minimum requirement fails validation.
* Verify that altitude below minimum requirements fails validation.
* Verify that previous voided tests remain stored.
* Verify that the current test result is updated after a new test.
* Verify that failed technical execution does not overwrite the current valid test result.

---

### 4.4. Integration Tests

The following integration tests should be implemented:

* Verify Java can execute the C test component with valid input.
* Verify Java can parse a passing C test output.
* Verify Java can parse a failing C test output.
* Verify Java handles C component non-zero exit codes.
* Verify Java handles invalid or malformed C component output.
* Verify a valid flight plan can be tested end-to-end.
* Verify an invalid flight plan receives a failed validation result end-to-end.

---

### 4.5. Acceptance Tests

**Test 1:** Successful flight plan validation

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to that Pilot
* **And** the flight plan has a valid DSL description/internal representation
* **And** the C test component returns a passing result
* **When** the Pilot tests the flight plan
* **Then** the flight plan should be marked as validated
* **And** the test result should be stored

**Test 2:** Flight plan fails fuel validation

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to that Pilot
* **And** the aircraft does not carry enough fuel according to the test
* **When** the Pilot tests the flight plan
* **Then** the flight plan should not be marked as valid
* **And** the test result should explain the fuel validation failure

**Test 3:** Flight plan fails altitude validation

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to that Pilot
* **And** the planned flight does not meet minimum altitude requirements
* **When** the Pilot tests the flight plan
* **Then** the flight plan should not be marked as valid
* **And** the test result should explain the altitude validation failure

**Test 4:** Invalid DSL description

* **Given** an authenticated Pilot
* **And** an existing flight plan with invalid DSL description/internal representation
* **When** the Pilot tries to test the flight plan
* **Then** the system should reject the operation
* **And** the C test component should not be executed

**Test 5:** Flight plan belongs to another Pilot

* **Given** an authenticated Pilot
* **And** an existing flight plan belonging to another Pilot
* **When** the Pilot tries to test the flight plan
* **Then** the system should reject the operation

**Test 6:** C test component execution failure

* **Given** an authenticated Pilot
* **And** an existing valid flight plan
* **And** the C test component cannot be executed
* **When** the Pilot tries to test the flight plan
* **Then** the system should display an execution error
* **And** the flight plan should not be marked as validated

**Test 7:** Weather data considered in validation

* **Given** an authenticated Pilot
* **And** an existing flight plan with weather data
* **When** the Pilot tests the flight plan
* **Then** the C test input should include the current weather data associated with the flight plan

**Test 8:** Unauthorized operation

* **Given** an authenticated user without permission to test flight plans
* **When** the user tries to test a flight plan
* **Then** the system should deny access

---

### 4.6. Implementation Status

Not implemented yet.