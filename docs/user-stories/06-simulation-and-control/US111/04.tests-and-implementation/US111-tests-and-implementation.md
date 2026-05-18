# US111 - Generate Final Simulation Report

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement final simulation report generation.

The implementation should include:

* authorization validation for the Flight Control Operator;
* lookup or validation of the selected simulation;
* validation that the simulation is in a final state;
* rejection of final report generation for running simulations;
* safe reading of complete simulation data;
* compilation of final report data;
* inclusion of simulation metadata and configuration;
* inclusion of final flight process statuses;
* inclusion of complete aircraft position history or movement summaries;
* inclusion of safety violation events and totals;
* inclusion of environmental influence summaries;
* inclusion of warnings and errors;
* inclusion of final outcome;
* formatting or parsing of final report output;
* meaningful error handling.

This user story should reuse the general report generation structure from US109, but enforce that only final simulation states are accepted.

---

### 4.2. Main Java/Application-side Classes to Implement

Possible Java/application-side classes:

* `GenerateFinalSimulationReportUI`
* `GenerateFinalSimulationReportController`
* `GenerateFinalSimulationReportService`
* `GenerateFinalSimulationReportRequest`
* `FinalSimulationReportDTO`
* `FinalFlightProcessStatusDTO`
* `SafetyViolationDTO`
* `FinalSimulationStatePolicy`
* `CFinalReportGenerationRunner`
* `CFinalReportOutput`
* `FinalSimulationReportOutputParser`
* `SimulationRepository`
* `AuthorizationService`

---

### 4.3. Main C Components to Implement

Possible C-side components:

* `report_generation_thread`
* `shared_memory_accessor`
* `final_report_data_builder`
* `final_simulation_report`
* `final_simulation_report_formatter`
* `shared_simulation_data`
* `final_flight_process_status`
* `complete_position_history`
* `safety_violation`
* `environmental_influence_summary`
* `simulation_logger`

---

### 4.4. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Flight Control Operator can request a final simulation report.
* Verify that an unauthorized user cannot request a final simulation report.
* Verify that a final report cannot be generated for a non-existing simulation.
* Verify that a final report cannot be generated for a running simulation.
* Verify that a final report can be generated for a completed simulation.
* Verify that a final report can be generated for a failed simulation.
* Verify that a final report can be generated for a terminated simulation.
* Verify that simulation metadata is included in the final report.
* Verify that simulation configuration is included in the final report.
* Verify that final flight process statuses are included in the final report.
* Verify that position history summary is included in the final report.
* Verify that safety violation events are included in the final report.
* Verify that safety violation count is included in the final report.
* Verify that environmental influence summary is included when applicable.
* Verify that warnings and errors are included in the final report.
* Verify that final outcome is included in the final report.

---

### 4.5. C Component Tests

The following C-level tests should be implemented:

* Verify that the report generation thread can generate a final report after simulation completion.
* Verify that the final report builder reads complete simulation data.
* Verify that complete position history is included.
* Verify that final flight process statuses are included.
* Verify that all safety violations are included.
* Verify that environmental influence data is included when available.
* Verify that warnings and errors are included.
* Verify that final outcome is included.
* Verify that shared data is read safely during final report generation.
* Verify that final report generation does not use incomplete time-step data.
* Verify that final report generation handles early-terminated simulations.

---

### 4.6. Integration Tests

The following integration tests should be implemented:

* Verify that a completed simulation produces a final report end-to-end.
* Verify that an early-terminated simulation produces a final report indicating early termination.
* Verify that a failed simulation produces a final report indicating failure.
* Verify that a running simulation is rejected for final report generation.
* Verify that safety violations detected during simulation appear in the final report.
* Verify that environmental influences applied during simulation appear in the final report.
* Verify that Java/application layer can request and parse the C-generated final report.
* Verify that final report data is consistent with simulation execution data.

---

### 4.7. Acceptance Tests

**Test 1:** Generate final report for completed simulation

* **Given** an authenticated Flight Control Operator
* **And** a completed simulation exists
* **When** the operator requests the final simulation report
* **Then** the system should generate the final report
* **And** the report should include the complete simulation outcome

**Test 2:** Reject final report for running simulation

* **Given** an authenticated Flight Control Operator
* **And** a simulation is still running
* **When** the operator requests the final simulation report
* **Then** the system should reject the request
* **And** inform that the final report is only available after the simulation ends

**Test 3:** Final report includes final process statuses

* **Given** a final simulation report is generated
* **When** the report is displayed
* **Then** it should include the final status of each flight process

**Test 4:** Final report includes all safety violations

* **Given** a simulation where safety violations occurred
* **When** the final report is generated
* **Then** all safety violation events should be included
* **And** the total number of violations should be displayed

**Test 5:** Final report includes environmental influence summary

* **Given** environmental influences were applied during simulation
* **When** the final report is generated
* **Then** the report should include the environmental influence summary

**Test 6:** Early terminated simulation

* **Given** a simulation was terminated early because the safety violation threshold was exceeded
* **When** the final report is generated
* **Then** the report should indicate that the simulation was terminated early
* **And** include the reason for termination

**Test 7:** Failed simulation

* **Given** a simulation failed due to an execution error
* **When** the final report is generated
* **Then** the report should indicate failure
* **And** include the failure reason when available

**Test 8:** Unauthorized operation

* **Given** an authenticated user without permission to generate final simulation reports
* **When** the user requests a final report
* **Then** the system should deny access

---

### 4.8. Implementation Status

Not implemented yet.