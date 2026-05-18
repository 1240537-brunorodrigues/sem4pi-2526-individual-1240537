# US111 - Generate Final Simulation Report

## 2. Analysis

### 2.1. Relevant Domain Concepts

The relevant domain concepts for this user story are:

* **Flight Control Operator:** user who requests the final simulation report.
* **Simulation:** execution instance whose final result is reported.
* **Final Simulation State:** state indicating that the simulation finished, failed or was terminated.
* **Report Generation Thread:** dedicated thread that compiles simulation results.
* **Final Simulation Report:** complete report generated after simulation execution ends.
* **Simulation Configuration:** parameters used to run the simulation.
* **Flight Process Status:** final execution status of each flight process.
* **Aircraft Position History:** complete position history collected during simulation.
* **Safety Violation Event:** violation detected during simulation.
* **Environmental Influence Data:** environmental data and effects applied during simulation.
* **Simulation Warning:** warning generated during simulation.
* **Simulation Error:** error generated during simulation.
* **Final Simulation Outcome:** final classification of simulation result.

---

### 2.2. Business Rules

* Only an authorized Flight Control Operator can request the final report through the application layer.
* The selected simulation must exist.
* The selected simulation must be in a final state.
* A running simulation should not produce a final report.
* The report must summarize simulation results for the Flight Control Operator.
* The report must help determine whether the programmed flights are safe to run.
* The report must include the total number of flights.
* The report must include flight execution statuses.
* The report must include safety violation timestamps and positions when violations occur.
* The report must indicate whether the scheduled flights plan passed or failed validation.
* The report must be stored in a file.
* The report may reuse data collected for the comprehensive final report.

---

### 2.3. Preconditions

* The simulation must exist.
* The simulation must have reached a final state.
* The report generation thread must be available.
* Shared simulation data must contain complete simulation results.
* Required synchronization mechanisms must allow safe access to final data.

---

### 2.4. Postconditions

**Successful final report generation:**

* Complete simulation data is read safely.
* A final report is compiled.
* The final report includes the final outcome and all required summaries.
* The final report is returned or displayed to the Flight Control Operator.

**Simulation still running:**

* No final report is generated.
* The system informs the user that the final report is only available after simulation completion.

**Failed final report generation:**

* No misleading final report is returned.
* The failure is logged or reported.
* A meaningful error message is displayed.

---

### 2.5. Domain Model

![Domain Model](svg/US111-DM.svg)