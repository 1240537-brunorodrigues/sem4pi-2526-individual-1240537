# US102 - Detect Aircraft Safety Violations in Real Time

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement real-time safety violation detection during simulation.

The implementation should include:

* continuous monitoring of aircraft positions;
* comparison of current aircraft positions;
* use of position history to anticipate potential future violations;
* detection of unsafe proximity or overlap between aircraft;
* creation of safety violation events;
* logging of safety violation events;
* resolution of involved aircraft to flight process IDs;
* notification of involved flight processes using `SIGUSR1`;
* configuration of `SIGUSR1` handlers in flight processes;
* blocking of other signals while handling `SIGUSR1`;
* user notification messages from flight processes;
* tracking of safety violation count;
* early termination when violation count exceeds a predefined threshold;
* sending of termination signals to flight processes;
* cleanup by flight processes when termination signals are handled.

This functionality is implemented as part of the C simulation component.

---

### 4.2. Main C Components to Implement

Possible C-side components:

* `main_simulation_process`
* `flight_process`
* `aircraft_position_tracker`
* `safety_violation_detector`
* `safety_threshold`
* `safety_violation`
* `violation_event_log`
* `flight_process_registry`
* `signal_notifier`
* `violation_signal_handler`
* `termination_signal_handler`
* `simulation_termination_policy`
* `simulation_logger`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that no safety violation is detected when aircraft are safely separated.
* Verify that a safety violation is detected when two aircraft are below minimum horizontal separation.
* Verify that a safety violation is detected when two aircraft are below minimum vertical separation.
* Verify that a potential future violation can be detected using recent position history.
* Verify that a detected violation includes involved aircraft identifiers.
* Verify that a detected violation includes a timestamp or simulation step.
* Verify that a detected violation includes involved aircraft positions.
* Verify that a detected violation is added to the violation event log.
* Verify that violation count is incremented when violations are detected.
* Verify that `SIGUSR1` is selected for violation notifications.
* Verify that involved process IDs are resolved from aircraft identifiers.
* Verify that failed signal delivery is logged.
* Verify that early termination is not triggered below the violation threshold.
* Verify that early termination is triggered when the violation threshold is exceeded.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that a flight process registers a `SIGUSR1` handler.
* Verify that a flight process handles `SIGUSR1`.
* Verify that other signals are blocked while handling `SIGUSR1`.
* Verify that the flight process notifies the system user after receiving `SIGUSR1`.
* Verify that the main process sends `SIGUSR1` to involved flight processes.
* Verify that the main process sends termination signals when violation threshold is exceeded.
* Verify that flight processes handle termination signals.
* Verify that flight processes perform cleanup before exiting.
* Verify that a signal delivery failure does not crash the main process.
* Verify that repeated violations are counted correctly.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify that two aircraft in unsafe proximity trigger a safety violation during simulation.
* Verify that involved flight processes receive `SIGUSR1`.
* Verify that involved flight processes notify the user after receiving the violation signal.
* Verify that violation events are logged during simulation.
* Verify that safety violation detection uses position updates produced by US101.
* Verify that the simulation continues when violation count remains below threshold.
* Verify that the simulation terminates early when violation count exceeds threshold.
* Verify that flight processes clean up after termination.

---

### 4.6. Acceptance Tests

**Test 1:** No safety violation

* **Given** a running simulation
* **And** aircraft positions are safely separated
* **When** the simulation system checks safety violations
* **Then** no safety violation should be created
* **And** no violation signal should be sent

**Test 2:** Safety violation detected

* **Given** a running simulation
* **And** two aircraft may violate safety rules
* **When** the simulation system checks aircraft positions
* **Then** a safety violation event should be created
* **And** the event should be logged
* **And** involved flight processes should be notified with `SIGUSR1`

**Test 3:** Flight process handles violation signal

* **Given** a flight process involved in a safety violation
* **When** it receives `SIGUSR1`
* **Then** it should block other signals while handling it
* **And** notify the system user with a message

**Test 4:** Potential future violation

* **Given** a running simulation
* **And** recent aircraft position history indicates converging trajectories
* **When** the system checks for safety violations
* **Then** it should detect that the aircraft may eventually violate safety rules

**Test 5:** Violation threshold exceeded

* **Given** a running simulation
* **And** the number of safety violations exceeds the predefined threshold
* **When** the threshold is checked
* **Then** the system should allow early termination
* **And** send termination signals to aircraft flight processes

**Test 6:** Flight process termination cleanup

* **Given** a flight process receives a termination signal
* **When** the signal is handled
* **Then** the flight process should perform any necessary cleanup before terminating

**Test 7:** Signal delivery failure

* **Given** a detected safety violation
* **And** an involved flight process cannot receive the signal
* **When** the main process tries to send `SIGUSR1`
* **Then** the failure should be logged
* **And** the main process should continue safely

---

### 4.7. Implementation Status

Not implemented yet.