# US109 - Generate Simulation Report

## 1. Requirements Engineering

### 1.1. User Story Description

As a Flight Control Operator, I want a comprehensive report that details the simulation outcomes including flight execution statuses, safety violation events with timestamps, positions and velocity vectors, and overall validation results, so that I can assess the safety and performance of the flights post-simulation.

This functionality allows the system to generate and store a complete final simulation report after the simulation concludes. The report generation thread aggregates all relevant simulation data once execution has finished.

The report must include the total number of flights, individual execution statuses, detailed safety violation events, timestamps, aircraft positions, velocity vectors and the final validation result. The complete report must be saved to a file for future reference.

---

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

* The parent process must include a report generation thread.
* The report generation thread is responsible for compiling simulation results.
* The report generation thread responds to safety violation events.
* Simulation results depend on flight processes, shared memory, safety violation detection and step synchronization.
* Threads must be managed using mutexes and condition variables for internal synchronization.

**From the client clarifications:**

No additional client clarifications are currently available.

---

### 1.3. Acceptance Criteria

### 1.3. Acceptance Criteria

* **AC1:** A Flight Control Operator must be able to obtain a comprehensive final simulation report.
* **AC2:** The Flight Control Operator must be authenticated and authorized, if the report is requested through the application layer.
* **AC3:** The selected simulation must exist.
* **AC4:** The report generation thread must aggregate data once the simulation concludes.
* **AC5:** The report must include the total number of flights.
* **AC6:** The report must include the individual execution status of each flight.
* **AC7:** The report must include detailed safety violation events.
* **AC8:** Each safety violation event must include a timestamp.
* **AC9:** Each safety violation event must include the involved aircraft positions.
* **AC10:** Each safety violation event must include the involved aircraft velocity vectors.
* **AC11:** The report must include the overall validation result.
* **AC12:** The final validation result must be clearly indicated as pass or fail.
* **AC13:** The complete report must be saved to a file for future reference.
* **AC14:** The system must not generate the final stored report before the simulation has concluded.
* **AC15:** If report generation fails, the system must provide a meaningful error message.
* **AC16:** If report file storage fails, the system must provide a meaningful error message.

---

### 1.4. Found out Dependencies

* This user story depends on US105, because the hybrid simulation environment and shared memory must exist.
* This user story depends on US106, because the report generation thread must exist.
* This user story depends on US107, because safety violation events notify the report generation thread.
* This user story depends on US108, because the report should use consistent step-by-step simulation data.
* This user story is related to US101, because aircraft movement data is part of the report.
* This user story is related to US102, because safety violation events are included in the report.
* This user story is related to US111, because later reports may become more complete or exported in specific formats.
* This user story is related to US113 and US114, because report data may later be logged or visualized remotely.

---

### 1.5. Input and Output Data

**Input Data:**

* Selected data:
    * Simulation identifier

* Simulation data sources:
    * Simulation configuration
    * Included flight plans
    * Shared aircraft states
    * Position history
    * Safety violation events
    * Flight process statuses
    * Warnings and errors
    * Final simulation status

**Output Data:**

* In case of success:
    * Final simulation report file
    * Report generation confirmation
    * File path or report identifier
    * Final validation result

* The report file must include:
    * Total number of flights
    * Individual flight execution statuses
    * Safety violation events
    * Timestamps of safety violations
    * Positions involved in safety violations
    * Velocity vectors involved in safety violations
    * Overall validation result
    * Pass/fail indication

* In case of failure:
    * Error message explaining why the report could not be generated or stored

---

### 1.6. System Sequence Diagram

![System Sequence Diagram](svg/US109-SSD.svg)

**_Other alternatives might exist._**

---

### 1.7. Other Relevant Remarks

* This report is primarily a simulation execution report.
* The detailed format may later be refined.
* The report generation thread should not directly perform safety detection.
* The report should only use complete and consistent simulation data.
* If the simulation is still running, the report may represent a partial/current snapshot.