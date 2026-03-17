## ADDED Requirements

### Requirement: Create connection profile
The system SHALL allow users to save SSH connection configurations as reusable profiles.

#### Scenario: Save new connection profile
- **WHEN** user fills in hostname, port, username, and selects an SSH key, then clicks "Save Profile"
- **THEN** system prompts for profile name and saves the connection configuration

#### Scenario: Profile name validation
- **WHEN** user attempts to save a profile with empty or duplicate name
- **THEN** system displays validation error and prevents save

#### Scenario: Set default profile
- **WHEN** user saves a connection profile
- **THEN** system allows user to mark it as the default profile to auto-connect on app launch

### Requirement: List saved connection profiles
The system SHALL display all saved connection profiles.

#### Scenario: Display profile list
- **WHEN** user opens the app home screen or navigates to "Profiles"
- **THEN** system displays list of saved connection profiles with profile names

#### Scenario: Show profile details in list
- **WHEN** viewing the profile list
- **THEN** each profile shows: profile name, hostname, username, and port

#### Scenario: Quick connect from profile list
- **WHEN** user taps on a profile in the list
- **THEN** system initiates SSH connection using that profile's credentials and key

### Requirement: Edit connection profile
The system SHALL allow users to modify saved profiles.

#### Scenario: Edit profile settings
- **WHEN** user selects "Edit" on a saved profile
- **THEN** system displays editable form with current hostname, port, username, and key selection

#### Scenario: Update profile connection details
- **WHEN** user modifies profile settings and clicks "Save"
- **THEN** system updates the stored profile with new values

#### Scenario: Change SSH key for profile
- **WHEN** user is editing a profile and selects a different SSH key
- **THEN** system updates the key association for that profile

### Requirement: Delete connection profile
The system SHALL allow users to remove saved profiles.

#### Scenario: Delete profile
- **WHEN** user selects "Delete" on a saved profile
- **THEN** system prompts for confirmation and removes the profile from the list

#### Scenario: Delete active profile
- **WHEN** user attempts to delete a profile that is currently in use (active connection)
- **THEN** system displays warning and offers to disconnect before deleting

### Requirement: Quick access to recent profiles
The system SHALL highlight frequently-used profiles.

#### Scenario: Display recent profiles on home screen
- **WHEN** user opens the app
- **THEN** system displays most recently used connection profiles for quick access

#### Scenario: Reorder profile list
- **WHEN** user drags or long-presses a profile
- **THEN** system allows reordering profiles by moving them up or down in the list

### Requirement: Export and import profiles
The system SHALL allow users to backup and restore connection profiles.

#### Scenario: Export profiles
- **WHEN** user clicks "Export Profiles"
- **THEN** system exports all profiles (without private keys) to a JSON file on device

#### Scenario: Import profiles from backup
- **WHEN** user clicks "Import Profiles" and selects a profile backup file
- **THEN** system imports the profiles and displays success message

#### Scenario: Import does not include keys
- **WHEN** user imports profiles from a backup
- **THEN** system imports connection settings but user must re-select SSH keys for each profile

### Requirement: Rename connection profile
The system SHALL allow users to change profile names.

#### Scenario: Rename profile
- **WHEN** user clicks "Rename" on a profile and enters new name
- **THEN** system updates the profile name
