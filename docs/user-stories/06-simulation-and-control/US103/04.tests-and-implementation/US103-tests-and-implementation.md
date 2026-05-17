# US103 - Synchronize Flight Execution with a Time Step

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement time-step synchronization for flight execution.

The implementation should include:

* simulation clock initialization;
* definition of a time step interval;
* step-by-step simulation progression;
* execution of each active flight process for the current step;
* sending of position updates at defined intervals;
* collection of expected updates for the current time step;
* validation that updates belong to the current time step;
* buffering of current step updates;
* barrier check before advancing to the next step;
* safe handling of missing, invalid or out-of-order updates;
* exclusion of completed flight processes from future expected updates.

This functionality is implemented as part of the C simulation component.

---

### 4.2. Main C Components to Implement

Possible C-side components:

* `main_simulation_process`
* `flight_process`
* `simulation_clock`
* `time_step`
* `step_coordinator`
* `expected_update_set`
* `step_update_buffer`
* `step_barrier`
* `movement_pipe_reader`
* `movement_message_decoder`
* `position_update`
* `position_update_validator`
* `synchronization_validation_result`
* `synchronization_issue`
* `synchronization_issue_handler`
* `simulation_logger`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that the simulation clock starts at the configured initial time step.
* Verify that the simulation clock advances to the next time step.
* Verify that a valid time step interval is required.
* Verify that each active flight process is included in the expected update set.
* Verify that completed flight processes are excluded from the expected update set.
* Verify that a position update with the current time step is accepted.
* Verify that a position update with a past time step is rejected.
* Verify that a position update with a future time step is rejected or buffered according to the implementation decision.
* Verify that an update from an unexpected flight process is rejected.
* Verify that the step barrier does not allow progression before all expected updates are received.
* Verify that the step barrier allows progression after all expected updates are received.
* Verify that invalid updates do not complete the barrier.
* Verify that synchronization issues are logged.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that flight processes send position updates at defined intervals.
* Verify that the main process waits for all expected updates for a time step.
* Verify that the main process processes all updates before advancing.
* Verify that the main process does not advance when an expected update is missing.
* Verify that the main process handles a completed flight process without blocking future steps.
* Verify that the main process handles pipe read issues safely.
* Verify that synchronization failure is reported.
* Verify that step-by-step progression is deterministic for a fixed input set.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify a simulation with one flight progresses step by step.
* Verify a simulation with multiple flights progresses only after all flight updates are received.
* Verify safety violation detection receives complete data for each time step.
* Verify position history stores updates grouped by time step.
* Verify reports can later use consistent time-step data.
* Verify an out-of-order update does not corrupt current time-step state.
* Verify a failed flight process is handled without deadlocking the simulation.

---

### 4.6. Acceptance Tests

**Test 1:** Step-by-step simulation progression

* **Given** a running simulation
* **And** a defined time step interval
* **When** the simulation starts
* **Then** it should progress step by step

**Test 2:** Flight process sends update at defined interval

* **Given** a running simulation
* **And** an active flight process
* **When** a time step is executed
* **Then** the flight process should send a position update for that time step

**Test 3:** Main process waits for all updates

* **Given** a running simulation with multiple active flight processes
* **When** only some flight processes have sent updates for the current time step
* **Then** the main process should not advance to the next time step

**Test 4:** Main process advances after all updates

* **Given** a running simulation with multiple active flight processes
* **And** all expected updates for the current time step have been received and processed
* **When** the step barrier is checked
* **Then** the main process should advance to the next time step

**Test 5:** Future update not processed early

* **Given** a running simulation at time step N
* **When** a flight process sends an update for time step N+1
* **Then** the update should not be processed as part of time step N

**Test 6:** Completed flight process no longer blocks simulation

* **Given** a flight process has completed its flight
* **When** the next time step starts
* **Then** the main process should not wait for an update from that completed process

**Test 7:** Synchronization issue

* **Given** a running simulation
* **And** an expected update is missing or invalid
* **When** the main process detects the synchronization issue
* **Then** it should handle the issue safely
* **And** log or report the issue

---

### 4.7. Implementation Status

Not implemented yet.