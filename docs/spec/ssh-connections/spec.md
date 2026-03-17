## ADDED Requirements

### Requirement: Establish SSH connection
The system SHALL establish SSH connections to remote machines using SSH protocol with key-based authentication.

#### Scenario: Successful connection with valid key
- **WHEN** user provides valid host, port, username, and SSH private key
- **THEN** system establishes an SSH connection and displays connection status as "connected"

#### Scenario: Connection failed with invalid credentials
- **WHEN** user provides invalid username or corrupted key
- **THEN** system displays error message and remains in "disconnected" state

#### Scenario: Connection timeout
- **WHEN** SSH connection attempt exceeds timeout threshold (default 30 seconds)
- **THEN** system terminates the connection attempt and displays timeout error

### Requirement: Manage connection lifecycle
The system SHALL maintain SSH connection state and handle disconnection gracefully.

#### Scenario: Disconnect from server
- **WHEN** user clicks "Disconnect" button
- **THEN** system closes SSH connection and returns to disconnected state

#### Scenario: Automatic reconnect on connection drop
- **WHEN** SSH connection is unexpectedly lost
- **THEN** system displays warning and allows user to manually reconnect

#### Scenario: Cleanup on app exit
- **WHEN** user closes the app while SSH connection is active
- **THEN** system properly closes SSH connection and releases resources

### Requirement: Display connection status
The system SHALL display real-time connection status to the user.

#### Scenario: Display connected status
- **WHEN** SSH connection is successfully established
- **THEN** system displays "Connected to [host]:[port]" with green indicator

#### Scenario: Display disconnected status
- **WHEN** no SSH connection exists or connection is lost
- **THEN** system displays "Disconnected" with grey indicator

#### Scenario: Display connecting status
- **WHEN** SSH connection is being established
- **THEN** system displays "Connecting..." with spinner animation

### Requirement: Configure SSH connection parameters
The system SHALL allow users to specify SSH connection details.

#### Scenario: Enter connection parameters
- **WHEN** user provides hostname, port (default 22), and username
- **THEN** system accepts and stores these parameters for use with a private key

#### Scenario: Validate hostname format
- **WHEN** user enters hostname
- **THEN** system validates it is a valid IP address or domain name
