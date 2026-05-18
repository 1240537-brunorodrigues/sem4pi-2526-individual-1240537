# US108 - Enforce Step-by-Step Simulation Using Semaphores

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement semaphore-based step synchronization.

The implementation should include:

* initialization of start-step semaphores;
* initialization of completion semaphores;
* release of active flight processes for each time step;
* flight process waiting before executing a step;
* movement calculation by each flight process;
* writing of updated aircraft state to shared memory;
* completion signalling by each flight process;
* parent process waiting for all expected completion signals;
* step completion barrier verification;
* simulation clock advancement only after step completion;
* removal of completed or terminated flight processes from the expected set;
* safe handling of missing, delayed or failed completion signals;
* semaphore cleanup during simulation shutdown.

This functionality must be implemented in C using semaphores.

---

### 4.2. Main C Components to Implement

Possible C-side components:

* `parent_simulation_process`
* `flight_process`
* `simulation_clock`
* `time_step`
* `step_semaphore_coordinator`
* `start_step_semaphore`
* `completion_semaphore`
* `active_flight_process_registry`
* `step_completion_barrier`
* `synchronization_result`
* `synchronization_failure_handler`
* `shared_memory_manager`
* `shared_aircraft_state_writer`
* `shared_aircraft_state`
* `simulation_logger`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that start-step semaphores are initialized.
* Verify that completion semaphores are initialized.
* Verify that active flight processes are released for a time step.
* Verify that a flight process waits for step permission before executing.
* Verify that a flight process signals completion after writing shared state.
* Verify that the parent process waits for all expected completions.
* Verify that the parent process does not advance before all expected completions are received.
* Verify that the parent process advances after all active flight processes complete the step.
* Verify that completed flight processes are removed from the active expected set.
* Verify that terminated flight processes are removed from the active expected set.
* Verify that synchronization failure is reported.
* Verify that semaphore cleanup occurs during shutdown.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that `sem_wait` blocks a flight process until the parent releases the step.
* Verify that `sem_post` releases a flight process for the current step.
* Verify that each flight process posts completion after writing to shared memory.
* Verify that the parent receives one completion per active flight process.
* Verify that the parent process can synchronize multiple flight processes.
* Verify that semaphore failure is handled safely.
* Verify that no deadlock occurs during normal step progression.
* Verify that cleanup destroys or unlinks semaphores as required.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify one flight process progresses step by step under parent control.
* Verify multiple flight processes progress in lockstep.
* Verify shared memory is updated before completion is signalled.
* Verify safety detection runs only after all flight processes complete the current step.
* Verify report data receives consistent step-based simulation data.
* Verify a completed flight no longer blocks future time steps.
* Verify a failed flight process is handled without permanent deadlock.

---

### 4.6. Acceptance Tests

**Test 1:** Flight process waits for parent permission

* **Given** a running hybrid simulation
* **And** a flight process is active
* **When** the flight process reaches a new time step
* **Then** it should wait on the start-step semaphore until the parent process releases it

**Test 2:** Parent releases all active flight processes

* **Given** a running simulation with multiple active flight processes
* **When** the parent process starts a time step
* **Then** it should release all active flight processes using semaphores

**Test 3:** Flight process signals completion

* **Given** a flight process has been released for a time step
* **When** it calculates movement and writes its updated state to shared memory
* **Then** it should signal step completion using a completion semaphore

**Test 4:** Parent waits for all completions

* **Given** multiple active flight processes are executing a time step
* **When** only some processes have signalled completion
* **Then** the parent process should not advance the simulation clock

**Test 5:** Parent advances after complete step

* **Given** all active flight processes have signalled completion for the current time step
* **When** the parent process verifies the step barrier
* **Then** the parent process should advance to the next time step

**Test 6:** Completed flight process does not block future steps

* **Given** a flight process has completed its flight
* **When** the parent process prepares the next time step
* **Then** that flight process should no longer be expected to signal completion

**Test 7:** Synchronization failure

* **Given** an active flight process fails to signal completion
* **When** the parent process detects the synchronization failure
* **Then** the failure should be handled safely
* **And** the simulation should not continue with inconsistent step data

**Test 8:** Semaphore cleanup

* **Given** the simulation is ending
* **When** cleanup is performed
* **Then** all semaphores used for step synchronization should be destroyed or unlinked safely

---

### 4.7. Implementation Status

Not implemented yet.