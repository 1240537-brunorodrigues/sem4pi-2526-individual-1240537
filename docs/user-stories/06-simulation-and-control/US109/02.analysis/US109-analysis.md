# US109 - Generate Simulation Report

## 2. Analysis

### 2.1. Relevant Domain Concepts

The relevant domain concepts for this user story are:

* **Flight Control Operator:** user who requests or consults the simulation report.
* **Simulation:** execution instance whose results are being reported.
* **Report Generation Thread:** dedicated parent process thread responsible for compiling simulation results.
* **Simulation Report:** structured summary of simulation execution.
* **Shared Memory:** source of simulation state and data.
* **Simulation Configuration:** parameters used to run the simulation.
* **Included Flight:** flight or flight plan included in the simulation.
* **Aircraft Position History:** recorded positions over simulation time.
* **Safety Violation Event:** detected violation included in the report.
* **Flight Process Status:** execution status of each flight process.
* **Simulation Warning/Error:** relevant issue detected during simulation.
* **Final Simulation Outcome:** final status of the simulation.

---

### 2.2. Business Rules

* Only an authorized Flight Control Operator can request a simulation report through the application layer.
* The selected simulation must exist.
* The report must include simulation metadata and configuration.
* The report must include included flights or flight plans.
* The report must include aircraft movement or position information.
* The report must include detected safety violation events.
* The report must include safety violation count.
* The report must include flight process statuses.
* The report must include warnings and errors.
* The report must include the final simulation outcome.
* Report data must be read safely from shared simulation data.
* Report data must not be inconsistent with partially completed time steps.
* If the simulation is still running, the report must clearly represent a current/partial snapshot.

---

### 2.3. Preconditions

* The simulation must exist.
* Shared simulation data must be available.
* The report generation thread must be initialized.
* Relevant simulation data must have been collected or be available for snapshot generation.
* Required synchronization primitives must be available for safe reads.

---

### 2.4. Postconditions

**Successful report generation:**

* Simulation data is read safely.
* A simulation report is compiled.
* The report includes relevant simulation results.
* The report is returned or displayed to the Flight Control Operator.

**Partial report generation:**

* If the simulation is still running, a snapshot report is produced.
* The report indicates that the simulation is not yet complete.

**Failed report generation:**

* No invalid report is returned.
* A meaningful error message is displayed.
* The failure is logged or reported.

---

### 2.5. Domain Model

![Domain Model](svg/US109-DM.svg)