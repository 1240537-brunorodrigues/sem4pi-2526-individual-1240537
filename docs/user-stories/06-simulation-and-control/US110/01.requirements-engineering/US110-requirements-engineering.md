# US110 - Incorporate Environmental Influences into the Simulation

## 1. Requirements Engineering

### 1.1. User Story Description

As a PO, I want the simulation to incorporate environmental factors such as wind into the simulation, so that the flight paths become more realistic and adapt to dynamic conditions.

This functionality adds an environment thread to the parent simulation process. The parent process must spawn this additional thread at simulation start.

The environment thread is responsible for loading environmental configuration from a weather service, particularly wind speed and wind direction. During simulation execution, the environment thread writes environmental data into the shared memory segment at each time step, so that flight processes and other simulation components can use it consistently.

---

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

* The simulation must consider environmental influences.
* Weather data may be associated with flight plans.
* Weather data can affect simulation results.
* The hybrid simulation uses shared memory for inter-process communication.
* The simulation progresses step by step.
* Simulation data is used for safety violation detection and reporting.

**From the client clarifications:**

No additional client clarifications are currently available.

---

### 1.3. Acceptance Criteria

* **AC1:** The parent process must spawn an additional environment thread at simulation start.
* **AC2:** The environment thread must load environmental configuration from a weather service.
* **AC3:** The environmental configuration must include wind speed.
* **AC4:** The environmental configuration must include wind direction.
* **AC5:** Environmental data must be validated before being written to shared memory.
* **AC6:** The environment thread must write environment data into the shared memory segment at each simulation time step.
* **AC7:** Flight processes must be able to read the current environmental data from shared memory.
* **AC8:** The environmental data must be associated with the corresponding simulation time step.
* **AC9:** Environmental data updates must be synchronized with the step-by-step simulation progression.
* **AC10:** Missing or invalid environmental data must be handled safely.
* **AC11:** Environmental data access must not corrupt shared simulation state.
* **AC12:** Environmental influence information should be available for report generation when relevant.
* **AC13:** This functionality must be implemented consistently with the C simulation component.

### 1.4. Found out Dependencies

* This user story depends on US041, US042 and US043, because environmental configuration is loaded from the weather service.
* This user story depends on US105, because the hybrid simulation environment and shared memory must exist.
* This user story depends on US106, because the parent process supports function-specific threads and this US adds an environment thread.
* This user story depends on US108, because environmental data must be written at each simulation time step.
* This user story is related to US109 and US111, because reports may include environmental influence information.

---

### 1.5. Input and Output Data

**Input Data:**

* Environmental data:
    * Wind speed
    * Wind direction
    * Temperature
    * Pressure
    * Visibility
    * Precipitation
    * Severe weather indicators, if applicable

* Context data:
    * Simulation area
    * Air control area
    * Time step
    * Flight plan
    * Aircraft state

**Output Data:**

* In case of successful application:
    * Adjusted aircraft movement data
    * Adjusted fuel consumption data, if applicable
    * Environmental influence entries in simulation state or report

* In case of missing or invalid environmental data:
    * Warning or error entry
    * Default environmental conditions, if allowed
    * Simulation continuation or failure according to policy

---

### 1.6. System Sequence Diagram

![System Sequence Diagram](svg/US110-SSD.svg)

**_Other alternatives might exist._**

---

### 1.7. Other Relevant Remarks

* This user story should not duplicate weather registration or import logic.
* Weather data should be validated before use.
* The environmental influence model may start simple and become more detailed later.
* The same environmental data should be applied consistently across all relevant flight processes for a given time step.
* Reports should mention environmental conditions when they affect simulation results.