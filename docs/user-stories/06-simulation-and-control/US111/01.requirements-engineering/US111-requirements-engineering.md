# US111 - Generate Final Simulation Report

## 1. Requirements Engineering

### 1.1. User Story Description

As a Flight Control Operator, I want to generate the final simulation report so that I can analyze the complete outcome of a finished simulation.

This functionality allows a Flight Control Operator to obtain the final report of a completed simulation. Unlike a partial simulation report or snapshot, the final simulation report must only be generated after the simulation has ended, failed or been terminated.

The final report must consolidate all relevant simulation data, including the simulation configuration, included flights, aircraft position history, safety violations, environmental influences, process statuses, warnings, errors and the final simulation outcome.

---

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

* The simulation system must produce simulation results.
* The parent process includes a report generation thread.
* The report generation thread is responsible for compiling simulation results.
* The report generation thread responds to safety violation events.
* Safety violation events must be logged and reflected in simulation results.
* Environmental influences may affect simulation results.
* Simulation progress is synchronized step by step.
* Simulation data is stored or coordinated through shared memory.

**From the client clarifications:**

No additional client clarifications are currently available.

---

### 1.3. Acceptance Criteria

* **AC1:** A Flight Control Operator must be able to generate the final simulation report.
* **AC2:** The Flight Control Operator must be authenticated and authorized, if the report is requested through the application layer.
* **AC3:** The selected simulation must exist.
* **AC4:** The selected simulation must have reached a final state.
* **AC5:** The final report must not be generated from an unfinished simulation unless the simulation has been explicitly terminated.
* **AC6:** The final report must include simulation metadata.
* **AC7:** The final report must include the simulation configuration.
* **AC8:** The final report must include all included flights or flight plans.
* **AC9:** The final report must include final flight process statuses.
* **AC10:** The final report must include aircraft position history or movement summaries.
* **AC11:** The final report must include all detected safety violation events.
* **AC12:** The final report must include the total number of safety violations.
* **AC13:** The final report must include environmental data or environmental influence summaries, when applicable.
* **AC14:** The final report must include warnings generated during simulation.
* **AC15:** The final report must include errors generated during simulation.
* **AC16:** The final report must include the final simulation outcome.
* **AC17:** The final report must indicate whether the simulation completed normally, failed or was terminated early.
* **AC18:** The report generation thread must compile the final report using complete and consistent simulation data.
* **AC19:** Final report generation must avoid partially completed time-step data.
* **AC20:** If final report generation fails, the system must display or log a meaningful error message.

---

### 1.4. Found out Dependencies

* This user story depends on US105, because the hybrid simulation environment and shared memory must exist.
* This user story depends on US106, because the report generation thread must exist.
* This user story depends on US107, because safety violation events must be communicated to the report generation thread.
* This user story depends on US108, because final report data must be based on consistent step-by-step simulation data.
* This user story depends on US109, because US109 defines the general simulation report generation structure.
* This user story depends on US110, because environmental influences may be part of the final report.
* This user story is related to US101, because movement and position history are included in the report.
* This user story is related to US102, because safety violation events are included in the report.
* This user story is related to US113 and US114, because final simulation data may later be logged or visualized remotely.

---

### 1.5. Input and Output Data

**Input Data:**

* Selected data:
    * Simulation identifier

* Final simulation data:
    * Simulation metadata
    * Simulation configuration
    * Included flight plans
    * Final flight process statuses
    * Aircraft position history
    * Safety violation events
    * Environmental influence data
    * Warnings
    * Errors
    * Final simulation status

**Output Data:**

* In case of success:
    * Final simulation report, including:
        * simulation metadata;
        * configuration summary;
        * included flight summary;
        * final process status summary;
        * aircraft movement/position summary;
        * safety violation summary;
        * environmental influence summary;
        * warning/error summary;
        * final simulation outcome.

* In case of failure:
    * Error message explaining why the final report could not be generated.

---

### 1.6. System Sequence Diagram

![System Sequence Diagram](svg/US111-SSD.svg)

**_Other alternatives might exist._**

---

### 1.7. Other Relevant Remarks

* US109 may be used for partial or snapshot reports.
* US111 should represent the final, consolidated report after simulation execution ends.
* If the simulation was terminated early due to safety violations, the final report must state that.
* The final report should be generated from complete and synchronized simulation data.