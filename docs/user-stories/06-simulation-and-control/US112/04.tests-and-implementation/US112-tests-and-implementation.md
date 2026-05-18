# US112 - Monthly Report Generation

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement monthly statistics report generation and establish the foundation for future report types.

The implementation should include:

* authorization validation for the Flight Control Operator;
* validation of report month and year;
* reusable report generation infrastructure;
* report generator registry;
* monthly statistics report generator;
* monthly statistics data collector;
* common report template;
* common report branding;
* report section generation;
* report graphic generation;
* report file generation and storage;
* no-data report support;
* meaningful error handling.

This user story should be designed so that future report types, such as compliance reports or incident reports, can reuse the same structure.

---

### 4.2. Main Classes to Implement

Possible main classes:

* `GenerateMonthlyReportUI`
* `GenerateMonthlyReportController`
* `GenerateMonthlyReportService`
* `GenerateMonthlyReportRequest`
* `ReportGenerationResultDTO`
* `AuthorizationService`
* `ReportingPeriodValidator`
* `ValidationResult`
* `ReportGeneratorRegistry`
* `ReportGenerator`
* `ReportType`
* `ReportingPeriod`
* `GeneratedReport`
* `ReportTemplateProvider`
* `ReportTemplate`
* `ReportBrandingProvider`
* `ReportBranding`
* `ReportSectionBuilder`
* `ReportGraphicBuilder`
* `ReportSection`
* `ReportGraphic`
* `ReportFileWriter`
* `ReportFileReference`
* `MonthlyStatisticsReportGenerator`
* `MonthlyStatisticsDataCollector`
* `MonthlyStatisticsData`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that an authorized Flight Control Operator can request a monthly statistics report.
* Verify that an unauthorized user cannot generate a monthly statistics report.
* Verify that a valid month and year are accepted.
* Verify that an invalid month is rejected.
* Verify that an invalid year is rejected.
* Verify that the monthly statistics report generator is resolved by report type.
* Verify that monthly statistics data is collected for the selected reporting period.
* Verify that the report template is applied.
* Verify that report branding is applied.
* Verify that report sections are generated.
* Verify that report graphics can be generated.
* Verify that a populated monthly report is generated when data exists.
* Verify that a no-data monthly report is generated when no data exists.
* Verify that the generated report is saved to a file.
* Verify that report file generation failure returns an error.
* Verify that the reporting infrastructure can support additional report types.

---

### 4.4. Integration Tests

The following integration tests should be implemented:

* Verify that a monthly report is generated end-to-end for a month with data.
* Verify that a monthly report is generated end-to-end for a month without data.
* Verify that the generated report follows the common branding and structure.
* Verify that the generated report includes the selected reporting period.
* Verify that the generated report includes monthly statistics.
* Verify that the report file is created in the expected location.
* Verify that future report generators can be registered without changing the monthly report flow.

---

### 4.5. Acceptance Tests

**Test 1:** Generate monthly report with data

* **Given** an authenticated and authorized Flight Control Operator
* **And** simulation data exists for a selected month
* **When** the operator requests the monthly statistics report
* **Then** the system should generate the report
* **And** the report should include statistics for the selected month
* **And** the report should be saved to a file

**Test 2:** Generate no-data monthly report

* **Given** an authenticated and authorized Flight Control Operator
* **And** no data exists for the selected month
* **When** the operator requests the monthly statistics report
* **Then** the system should generate a report
* **And** the report should explicitly state that no data was available
* **And** the report should be saved to a file

**Test 3:** Invalid reporting period

* **Given** an authenticated and authorized Flight Control Operator
* **When** the operator provides an invalid month or year
* **Then** the system should reject the request
* **And** display a meaningful validation error

**Test 4:** Consistent report branding

* **Given** a monthly report is generated
* **When** the report is opened
* **Then** it should follow the common report branding
* **And** use the common report structure

**Test 5:** Report-specific data collection

* **Given** the monthly statistics report type is selected
* **When** report generation starts
* **Then** the system should use the monthly statistics data collection strategy

**Test 6:** Future report extensibility

* **Given** the system supports report generation
* **When** a future report type is added
* **Then** it should be possible to define its own data collector, sections and graphics
* **And** still reuse the common branding and structure

**Test 7:** Unauthorized operation

* **Given** an authenticated user without permission to generate monthly reports
* **When** the user requests a monthly report
* **Then** the system should deny access

**Test 8:** File generation failure

* **Given** the report content is generated
* **And** the system cannot save the file
* **When** report generation finishes
* **Then** the system should display a meaningful file generation error

---

### 4.6. Implementation Status

Not implemented yet.