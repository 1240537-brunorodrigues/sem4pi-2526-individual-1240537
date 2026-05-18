# US105 - Initialize Hybrid Simulation Environment with Shared Memory

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the initialization of the hybrid simulation environment.

The implementation should include:

* validation of simulation configuration;
* execution of the C hybrid simulation component;
* initialization of the parent simulation process;
* creation of dedicated parent process threads;
* allocation of a shared memory segment;
* initialization of shared simulation data;
* initialization of semaphores for flight process synchronization;
* initialization of mutexes for parent thread synchronization;
* initialization of condition variables for parent thread coordination;
* configuration of parent process signal handlers;
* launching of each flight as an independent child process;
* configuration of child flight processes to access shared memory;
* configuration of child flight processes to use semaphores;
* configuration of child flight process signal handlers;
* cleanup of resources in case of initialization failure.

This component must be implemented in C and must utilize threads, mutexes, condition variables and signals.

---

### 4.2. Main Java/Application-side Classes to Implement

Possible Java/application-side classes:

* `StartHybridSimulationUI`
* `StartHybridSimulationController`
* `StartHybridSimulationService`
* `StartHybridSimulationRequest`
* `SimulationConfigurationDTO`
* `HybridSimulationDTO`
* `HybridSimulationInputMapper`
* `CHybridSimulationRunner`
* `HybridSimulationInput`
* `HybridSimulationOutput`

---

### 4.3. Main C Components to Implement

Possible C-side components:

* `hybrid_simulation_environment`
* `simulation_config`
* `parent_simulation_process`
* `parent_thread_manager`
* `dedicated_thread`
* `shared_memory_manager`
* `shared_simulation_data`
* `semaphore_manager`
* `synchronization_primitive_manager`
* `signal_manager`
* `flight_process_launcher`
* `flight_process`
* `resource_cleanup_manager`

---

### 4.4. Unit Tests

The following unit tests should be implemented:

* Verify that valid simulation configuration is accepted.
* Verify that invalid number of flights is rejected.
* Verify that invalid time-step interval is rejected.
* Verify that invalid shared memory capacity is rejected.
* Verify that application input is mapped to C simulation input.
* Verify that C simulation output is mapped to an application DTO.
* Verify that initialization failure produces an error DTO.

---

### 4.5. C Component Tests

The following C-level tests should be implemented:

* Verify that the parent simulation process initializes correctly.
* Verify that parent process signal handlers are configured.
* Verify that mutexes are initialized.
* Verify that condition variables are initialized.
* Verify that dedicated parent threads are created.
* Verify that the shared memory segment is allocated.
* Verify that shared memory is attached successfully.
* Verify that shared simulation data is initialized.
* Verify that semaphores are initialized for flight processes.
* Verify that one child process is forked for each flight.
* Verify that each child flight process attaches to shared memory.
* Verify that each child flight process is configured to use semaphores.
* Verify that each child flight process configures signal handlers.
* Verify that shared memory allocation failure is handled safely.
* Verify that thread creation failure is handled safely.
* Verify that child process creation failure is handled safely.
* Verify that resource cleanup runs after initialization failure.
* Verify that shared memory, semaphores, mutexes and condition variables are destroyed during cleanup.

---

### 4.6. Integration Tests

The following integration tests should be implemented:

* Verify that the application layer can start the C hybrid simulation component.
* Verify that a hybrid simulation with one flight initializes one child flight process.
* Verify that a hybrid simulation with multiple flights initializes one child process per flight.
* Verify that the parent process creates dedicated threads.
* Verify that parent and child processes can access the initialized shared memory.
* Verify that flight processes are blocked or coordinated by semaphores.
* Verify that parent process threads can use mutexes and condition variables safely.
* Verify that all resources are cleaned when the simulation ends.

---

### 4.7. Acceptance Tests

**Test 1:** Successful hybrid simulation initialization

* **Given** a valid simulation configuration
* **When** the Flight Control Operator starts the hybrid simulation
* **Then** the parent simulation process should be initialized
* **And** a shared memory segment should be allocated and initialized
* **And** dedicated parent process threads should be created
* **And** each flight should be launched as an independent process
* **And** flight processes should be configured to use semaphores

**Test 2:** Shared memory initialized before flight processes use it

* **Given** a valid simulation configuration
* **When** the hybrid simulation environment is initialized
* **Then** shared memory should be allocated and initialized before child flight processes attach to it

**Test 3:** Parent process creates dedicated threads

* **Given** a valid simulation configuration
* **When** the parent simulation process is initialized
* **Then** it should spawn dedicated threads for its functionalities

**Test 4:** One child process per flight

* **Given** a simulation configuration with multiple flights
* **When** the hybrid simulation is started
* **Then** each flight should be launched as an independent child process

**Test 5:** Semaphore configuration

* **Given** initialized child flight processes
* **When** they prepare for simulation execution
* **Then** they should be configured to use semaphores for synchronization

**Test 6:** Initialization failure cleanup

* **Given** a failure occurs during shared memory, thread or process initialization
* **When** the initialization fails
* **Then** allocated resources should be cleaned up
* **And** the system should report the initialization error

**Test 7:** Required C mechanisms are used

* **Given** the hybrid simulation component implementation
* **When** the component is reviewed or tested
* **Then** it should use threads, mutexes, condition variables and signals

---

### 4.8. Implementation Status

Not implemented yet.