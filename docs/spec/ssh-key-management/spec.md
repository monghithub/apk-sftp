## ADDED Requirements

### Requirement: Import SSH private key
The system SHALL allow users to import SSH private keys from device storage.

#### Scenario: Import key from file
- **WHEN** user clicks "Import Key" and selects a PEM-format SSH private key file
- **THEN** system validates the key format, stores it securely, and displays success message

#### Scenario: Import key with passphrase
- **WHEN** user imports a passphrase-protected SSH key
- **THEN** system prompts user to enter passphrase, validates it, and stores the key securely

#### Scenario: Import invalid key format
- **WHEN** user attempts to import a file that is not a valid SSH private key
- **THEN** system displays error message indicating invalid key format

#### Scenario: Import duplicate key
- **WHEN** user attempts to import a key that is already stored in the app
- **THEN** system displays message indicating key already exists and offers to replace it

### Requirement: List imported SSH keys
The system SHALL display all stored SSH private keys to the user.

#### Scenario: Display key list
- **WHEN** user navigates to "SSH Keys" screen
- **THEN** system displays list of imported keys with key names and fingerprints

#### Scenario: Key metadata display
- **WHEN** user views the key list
- **THEN** each key entry shows: key name/label, key type (RSA/ED25519), fingerprint, and import date

### Requirement: Delete SSH private key
The system SHALL allow users to delete stored SSH keys.

#### Scenario: Successful key deletion
- **WHEN** user selects a stored key and clicks "Delete"
- **THEN** system prompts for confirmation, permanently removes the key from secure storage, and removes it from the key list

#### Scenario: Cannot delete key in use
- **WHEN** user attempts to delete a key that is currently being used for an active SSH connection
- **THEN** system displays warning and prevents deletion until connection is closed

### Requirement: Securely store SSH private keys
The system SHALL protect SSH private keys from unauthorized access.

#### Scenario: Keys stored in Android Keystore
- **WHEN** an SSH private key is imported
- **THEN** the key is encrypted and stored in Android Keystore, not readable by other apps or unencrypted on disk

#### Scenario: Key never exposed in memory
- **WHEN** SSH connection is using a stored key
- **THEN** key material is streamed directly from secure storage without being fully loaded into application memory

#### Scenario: Key access requires app permission
- **WHEN** system accesses a stored SSH key
- **THEN** only the SFTP app can read it, other apps cannot access keys even with file access permissions

### Requirement: Rename SSH key
The system SHALL allow users to rename stored SSH keys for organizational purposes.

#### Scenario: Successful key rename
- **WHEN** user selects a key and clicks "Rename", then enters new name
- **THEN** system updates the key label and displays the new name in the key list

### Requirement: Display key information
The system SHALL show detailed information about stored SSH keys.

#### Scenario: View key details
- **WHEN** user taps on a key in the list
- **THEN** system displays: key type, fingerprint, algorithm, key size, and import date
