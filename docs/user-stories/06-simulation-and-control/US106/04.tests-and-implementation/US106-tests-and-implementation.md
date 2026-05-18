# US106 - Implement Function-Specific Threads in the Parent Process

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement function-specific threads inside the parent simulation process.

The implementation should include:

* initialization of mutexes required by parent process threads;
* initialization of condition variables required by parent process threads;
* creation of a safety violation detection thread;
* creation of a report generation thread;
* optional creation of additional function-specific threads;
* registration of each thread and its responsibility;
* scanning of shared memory by the safety violation detection thread;
* preparation of simulation result compilation by the report generation thread;
* safe thread creation failure handling;
* safe thread shutdown and cleanup;
* destruction of mutexes and condition variables during cleanup.

This functionality must be implemented in C using threads, mutexes and condition variables.

---

### 4.2. Main C Components to Implement

Possible C-side components:

* `parent_simulation_process`
* `parent_thread_manager`
* `thread_registry`
* `dedicated_thread`
* `safety_violation_detection_thread`
* `report_generation_thread`
* `additional_function_thread`
* `thread_lifecycle_manager`
* `shared_memory_accessor`
* `shared_simulation_data`
* `parent_synchronization_context`
* `mutex_manager`
* `condition_variable_manager`
* `simulation_logger`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that mutexes are initialized before parent threads start.
* Verify that condition variables are initialized before parent threads start.
* Verify that the safety violation detection thread is created.
* Verify that the report generation thread is created.
* Verify that each created thread is registered with a responsibility.
* Verify that the safety violation detection thread has the correct responsibility.
* Verify that the report generation thread has the correct responsibility.
* Verify that optional additional threads can be registered.
* Verify that a thread creation failure is reported.
* Verify that if report thread creation fails after safety thread creation, the safety thread is stopped safely.
* Verify that thread shutdown requests are propagated.
* Verify that mutexes and condition variables are destroyed during cleanup.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that the parent process creates at least two dedicated threads.
* Verify that the safety violation detection thread can access shared memory under mutex protection.
* Verify that the report generation thread can access report data under mutex protection.
* Verify that condition variables can be used for thread waiting and notification.
* Verify that no shared parent process data is accessed without the intended mutex.
* Verify that the parent process can join created threads during shutdown.
* Verify that thread creation errors do not leave orphaned threads.
* Verify that thread cleanup runs when simulation initialization fails.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify that the hybrid simulation environment initializes the required parent threads.
* Verify that the safety violation detection thread runs concurrently with the report generation thread.
* Verify that the safety violation detection thread scans shared memory while the simulation is running.
* Verify that the report generation thread remains available to compile results.
* Verify that mutexes prevent inconsistent shared data reads.
* Verify that condition variables can be used for event-based coordination between threads.
* Verify that all parent threads terminate cleanly when the simulation ends.

---

### 4.6. Acceptance Tests

**Test 1:** Required parent threads are created

* **Given** the hybrid simulation environment is initialized
* **When** the parent simulation process starts its function-specific threads
* **Then** a safety violation detection thread should be created
* **And** a report generation thread should be created

**Test 2:** Safety violation detection thread scans shared memory

* **Given** the safety violation detection thread is running
* **And** shared memory contains aircraft position data
* **When** the thread executes its detection loop
* **Then** it should scan shared memory for aircraft flight conflicts

**Test 3:** Report generation thread compiles results

* **Given** the report generation thread is running
* **And** simulation result data is available
* **When** the thread compiles results
* **Then** it should prepare simulation report data

**Test 4:** Threads use mutexes for shared data

* **Given** parent process threads access shared data
* **When** shared data is read or updated
* **Then** mutexes should be used to protect the critical section

**Test 5:** Threads use condition variables for synchronization

* **Given** a parent thread needs to wait for a simulation event
* **When** the event occurs
* **Then** a condition variable should be used to notify the waiting thread

**Test 6:** Additional thread is supported

* **Given** the parent process requires another dedicated functionality
* **When** an additional function-specific thread is configured
* **Then** the parent process should be able to create and register it

**Test 7:** Thread creation failure

* **Given** thread creation fails
* **When** the parent process detects the failure
* **Then** the failure should be logged
* **And** already created threads should be stopped or cleaned up safely

**Test 8:** Thread cleanup on simulation end

* **Given** the simulation is ending
* **When** the parent process shuts down
* **Then** all dedicated threads should terminate safely
* **And** mutexes and condition variables should be destroyed

---

### 4.7. Implementation Status

Not implemented yet.