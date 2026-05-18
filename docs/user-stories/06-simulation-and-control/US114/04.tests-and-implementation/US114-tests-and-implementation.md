# US114 - Simulated Flights Visualization

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement browser-based visualization of simulated flight progress.

The implementation should include:

* an HTTP server inside the Flights Logging Server application;
* a browser-accessible status page;
* an endpoint for current flight progress data;
* an internal progress store updated by UDP flight progress updates;
* AJAX polling from the status page;
* dynamic table updates without full page reload;
* safe handling of no running flights;
* safe handling of missing or delayed updates;
* safe handling of malformed progress data;
* coexistence of UDP receiving and HTTP serving in the same application.

The status page may initially use a simple table. Map visualization is optional and not required by the user story.

---

### 4.2. Main Components to Implement

Possible Flights Logging Server components:

* `flights_logging_server`
* `udp_receiver`
* `flight_progress_store`
* `http_server`
* `status_page_controller`
* `status_page_renderer`
* `flight_progress_api_controller`
* `flight_progress_dto_mapper`
* `flights_visualization_logger`

Possible browser-side components:

* `status_page`
* `ajax_client_script`
* `flight_progress_table_renderer`

Possible data classes:

* `flight_progress_update`
* `flight_progress_dto`
* `http_request`
* `http_response`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that the HTTP server can be started.
* Verify that the status page controller returns an HTML page.
* Verify that the status page includes AJAX update logic.
* Verify that the flight progress API returns current progress data.
* Verify that flight progress updates can be mapped to DTOs.
* Verify that the API returns an empty response when no flights are running.
* Verify that delayed or missing progress updates are handled safely.
* Verify that malformed progress data does not crash the API.
* Verify that table rows can be rendered from flight progress DTOs.
* Verify that the empty state can be displayed.

---

### 4.4. HTTP / AJAX Tests

The following HTTP/AJAX-level tests should be implemented:

* Verify that a browser can request the status page through HTTP.
* Verify that `/status` returns HTML.
* Verify that `/api/flights/progress` returns structured flight progress data.
* Verify that AJAX requests can fetch updated progress without reloading the page.
* Verify that repeated AJAX polling returns updated simulation steps.
* Verify that the page updates displayed data after receiving JSON progress data.
* Verify that the page remains loaded while progress data changes.
* Verify that no full page reload is required to display updated progress.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify that the Flights Logging Server can run both UDP receiver and HTTP server.
* Verify that a UDP flight update changes the data returned by the HTTP progress endpoint.
* Verify that a running simulation appears on the status page.
* Verify that progress updates appear step by step in the browser.
* Verify that multiple simulated flights appear in the status table.
* Verify that the visualization handles no running flights.
* Verify that malformed UDP updates do not break the HTTP visualization.
* Verify that the HTTP server remains available while UDP updates are being received.

---

### 4.6. Acceptance Tests

**Test 1:** Status page available in browser

* **Given** the Flights Logging Server is running
* **When** the Flight Control Operator opens the status page URL in a standard web browser
* **Then** the HTTP server should return the status page
* **And** the browser should display the simulated flights visualization page

**Test 2:** Running flights displayed

* **Given** simulated flights are running
* **And** the Flights Logging Server has received progress updates
* **When** the status page requests current progress data
* **Then** the page should display the running simulated flights

**Test 3:** Step-by-step progress update

* **Given** a running simulation
* **And** flight progress changes at each simulation step
* **When** the status page performs automatic updates
* **Then** the displayed data should update to show the current step-by-step progress

**Test 4:** AJAX without page reload

* **Given** the status page is open
* **When** flight progress data changes
* **Then** AJAX should be used to fetch updated data
* **And** the page should update without full reload

**Test 5:** Table visualization

* **Given** current simulated flight data is available
* **When** the status page renders the data
* **Then** it may present the data as a table
* **And** each row should represent the progress of a simulated flight

**Test 6:** No running flights

* **Given** the Flights Logging Server is running
* **And** there are no running simulated flights
* **When** the browser requests current flight progress
* **Then** the page should display a no-running-flights state

**Test 7:** Delayed or missing updates

* **Given** the status page is open
* **And** a flight progress update is delayed or missing
* **When** the page refreshes its data through AJAX
* **Then** the page should remain usable
* **And** it should not crash or require manual reload

**Test 8:** UDP and HTTP coexistence

* **Given** the Flights Logging Server is receiving UDP flight updates
* **When** a browser requests the status page or progress endpoint
* **Then** the HTTP server should respond correctly
* **And** UDP logging should continue to work

---

### 4.7. Implementation Status

Not implemented yet.