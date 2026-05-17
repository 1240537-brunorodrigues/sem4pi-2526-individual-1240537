# US101 - Capture and Process Flight Movements

## 4. Tests and Implementation

### 4.1. Implementation Notes

This user story should implement the capture and processing of movement commands from flight processes.

The implementation should include:

* creation of movement messages by flight processes;
* encoding of movement messages;
* sending position updates through pipes;
* reading movement messages from pipes in the main process;
* decoding raw pipe messages;
* validation of movement messages;
* updating current aircraft positions;
* storing past aircraft positions;
* logging invalid or malformed messages;
* safe handling of pipe read/write failures.

This functionality is implemented as part of the C simulation component.

---

### 4.2. Main C Components to Implement

Possible C-side components:

* `main_simulation_process`
* `flight_process`
* `movement_message`
* `movement_message_encoder`
* `movement_message_decoder`
* `movement_pipe_writer`
* `movement_pipe_reader`
* `movement_message_validator`
* `aircraft_position`
* `velocity_vector`
* `aircraft_position_tracker`
* `current_position_map`
* `position_history`
* `simulation_logger`

---

### 4.3. Unit Tests

The following unit tests should be implemented:

* Verify that a flight process can create a valid movement message.
* Verify that a valid movement message can be encoded.
* Verify that an encoded movement message can be decoded.
* Verify that encoding and decoding preserve aircraft identifier.
* Verify that encoding and decoding preserve timestamp.
* Verify that encoding and decoding preserve position data.
* Verify that a movement message without aircraft identifier is rejected.
* Verify that a movement message without timestamp is rejected.
* Verify that a movement message without position data is rejected.
* Verify that invalid coordinates are rejected.
* Verify that invalid altitude is rejected when outside accepted simulation bounds.
* Verify that valid position updates update the current position map.
* Verify that valid position updates are stored in position history.
* Verify that invalid movement messages do not update current position.
* Verify that invalid movement messages are not stored in position history.

---

### 4.4. C Component Tests

The following C-level tests should be implemented:

* Verify that a flight process writes position updates to its pipe.
* Verify that the main process reads position updates from a pipe.
* Verify that multiple flight processes can send movement messages through different pipes.
* Verify that the main process can process messages from multiple pipes.
* Verify that pipe read failure is handled safely.
* Verify that pipe write failure is handled safely.
* Verify that malformed raw messages are rejected.
* Verify that position history grows when valid messages are received.
* Verify that current position is overwritten only by newer valid updates.
* Verify that past positions remain available after current position is updated.

---

### 4.5. Integration Tests

The following integration tests should be implemented:

* Verify that a running simulation with one flight receives position updates.
* Verify that a running simulation with multiple flights receives position updates from all flight processes.
* Verify that each received update modifies the correct aircraft current position.
* Verify that position history is stored separately per aircraft.
* Verify that position history can be queried by aircraft.
* Verify that position history can be queried by timestamp or simulation step.
* Verify that invalid updates from one flight process do not crash the main simulation process.
* Verify that movement data is available for later safety violation detection.

---

### 4.6. Acceptance Tests

**Test 1:** Receive valid position update

* **Given** a running simulation
* **And** a flight process executing a flight plan
* **When** the flight process sends a valid position update through a pipe
* **Then** the main process should receive the update
* **And** update the aircraft's current position
* **And** store the position in history

**Test 2:** Multiple flight processes send updates

* **Given** a running simulation with multiple flight processes
* **When** each flight process sends position updates through its pipe
* **Then** the main process should process updates from all flight processes
* **And** track each aircraft separately

**Test 3:** Invalid movement message

* **Given** a running simulation
* **When** a flight process sends an invalid movement message
* **Then** the main process should reject the message
* **And** the current aircraft position should not be updated from invalid data
* **And** the invalid message should be logged

**Test 4:** Store past positions

* **Given** a running simulation
* **And** an aircraft sends several valid position updates
* **When** the main process processes those updates
* **Then** all valid past positions should be stored in position history

**Test 5:** Position history supports safety detection

* **Given** stored past aircraft positions
* **When** the safety violation detection component requires historical movement data
* **Then** the position history should be available for later analysis

**Test 6:** Pipe communication failure

* **Given** a running simulation
* **And** a pipe communication failure occurs
* **When** the main process tries to read or process the update
* **Then** the failure should be handled safely
* **And** the simulation should not crash unexpectedly

---

### 4.7. Implementation Status

Not implemented yet.