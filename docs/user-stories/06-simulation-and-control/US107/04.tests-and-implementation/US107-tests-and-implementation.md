# US107 - Notify Report Thread on Safety Violation

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement notification from the safety violation detection thread to the report generation thread.

The implementation should include:

* shared violation state initialization;
* pending violation queue initialization;
* violation mutex initialization;
* violation condition variable initialization;
* safety thread update of shared violation data under mutex protection;
* condition variable signal after a violation event is queued;
* report thread waiting on the violation condition variable;
* report thread waking when violation data becomes available;
* report thread reading pending violation events under mutex protection;
* report thread incorporating violation data into report data;
* support for multiple pending violation events;
* safe shutdown of waiting report thread;
* cleanup of mutex and condition variable.

This functionality must be implemented in C using mutexes and condition variables.

---

### 4.2. Main C Components to Implement

Possible C-side components:

* `safety_violation_detection_thread`
* `report_generation_thread`
* `safety_violation_event`
* `shared_violation_state`
* `pending_violation_queue`
* `violation_mutex`
* `violation_condition_variable`
* `mutex_manager`
* `condition_variable_manager`
* `report_data_builder`
* `report_data`
* `simulation_logger`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that a safety violation event can be queued.
* Verify that pending violation count increases when an event is queued.
* Verify that pending violation count decreases when an event is consumed.
* Verify that the safety thread updates violation data under mutex protection.
* Verify that the condition variable is signalled after the violation event is queued.
* Verify that the report thread waits when there are no pending violations.
* Verify that the report thread wakes when the condition variable is signalled.
* Verify that the report thread reads violation data under mutex protection.
* Verify that a violation event is added to report data.
* Verify that multiple violation events are not overwritten.
* Verify that shutdown wakes a waiting report thread.
* Verify that mutex and condition variable cleanup occurs.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that `pthread_cond_wait` is used with the violation mutex.
* Verify that `pthread_cond_signal` or equivalent is used after queuing a violation.
* Verify that the report thread waits in a loop while there are no pending violations.
* Verify that spurious wakeups do not cause invalid report processing.
* Verify that concurrent violation events are processed safely.
* Verify that no deadlock occurs between safety and report threads.
* Verify that report thread shutdown does not leave the thread blocked forever.
* Verify that condition variable and mutex are destroyed during cleanup.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify that a detected safety violation wakes the report generation thread.
* Verify that the report generation thread includes detected violations in report data.
* Verify that multiple detected violations are all included in report data.
* Verify that the safety detection thread and report generation thread run concurrently without race conditions.
* Verify that the report generation thread remains waiting when no violations occur.
* Verify that simulation shutdown wakes and terminates the report thread safely.

---

### 4.6. Acceptance Tests

**Test 1:** Safety violation wakes report thread

* **Given** the safety violation detection thread and report generation thread are running
* **And** the report generation thread is waiting for violation events
* **When** the safety violation detection thread detects a safety violation
* **Then** the safety thread should update shared violation data
* **And** signal the violation condition variable
* **And** the report generation thread should wake up

**Test 2:** Report thread processes violation event

* **Given** a pending safety violation event
* **When** the report generation thread wakes up
* **Then** it should read the violation event under mutex protection
* **And** include it in the report data

**Test 3:** Multiple safety violations

* **Given** multiple safety violations are detected before the report thread processes them
* **When** the report generation thread wakes up
* **Then** all pending violations should remain available for processing
* **And** no violation event should be overwritten

**Test 4:** No violation event

* **Given** the report generation thread is running
* **And** there are no pending violation events
* **When** the report generation thread checks the shared violation state
* **Then** it should wait on the condition variable

**Test 5:** Shutdown while report thread is waiting

* **Given** the report generation thread is waiting on the condition variable
* **When** simulation shutdown is requested
* **Then** the report thread should be awakened
* **And** terminate safely

**Test 6:** Race condition prevention

* **Given** the safety thread and report thread access shared violation data concurrently
* **When** violation data is updated or read
* **Then** the violation mutex should protect the shared data

**Test 7:** Cleanup

* **Given** the simulation is ending
* **When** synchronization resources are cleaned up
* **Then** the violation mutex and condition variable should be destroyed safely

---

### 4.7. Implementation Status

Not implemented yet.