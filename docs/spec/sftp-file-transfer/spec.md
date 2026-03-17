## ADDED Requirements

### Requirement: Upload file from device to server
The system SHALL support uploading files from the Android device to a remote SFTP server.

#### Scenario: Successful file upload
- **WHEN** user selects a local file and clicks "Upload"
- **THEN** system transfers the file to the remote server and displays transfer progress

#### Scenario: Upload progress display
- **WHEN** file upload is in progress
- **THEN** system displays percentage completed, transferred bytes, and estimated time remaining

#### Scenario: Upload completion notification
- **WHEN** file upload completes successfully
- **THEN** system displays success message with filename and location on remote server

#### Scenario: Upload failure
- **WHEN** upload fails due to network error, permission denied, or disk full
- **THEN** system displays error message and allows user to retry

### Requirement: Download file from server to device
The system SHALL support downloading files from a remote SFTP server to the Android device.

#### Scenario: Successful file download
- **WHEN** user selects a remote file and clicks "Download"
- **THEN** system transfers the file to the device and displays transfer progress

#### Scenario: Download progress display
- **WHEN** file download is in progress
- **THEN** system displays percentage completed, transferred bytes, and estimated time remaining

#### Scenario: Download completion notification
- **WHEN** file download completes successfully
- **THEN** system displays success message with filename and local storage location

#### Scenario: Handle download to existing file
- **WHEN** user attempts to download a file that already exists locally
- **THEN** system prompts user to confirm overwrite, rename, or cancel

### Requirement: Cancel active transfer
The system SHALL allow users to cancel ongoing file transfers.

#### Scenario: Cancel upload in progress
- **WHEN** user clicks "Cancel" during an active upload
- **THEN** system stops the transfer, removes partial file from server, and returns to file browser

#### Scenario: Cancel download in progress
- **WHEN** user clicks "Cancel" during an active download
- **THEN** system stops the transfer, removes partial file from device, and returns to file browser

### Requirement: Delete remote file
The system SHALL support deleting files from the remote SFTP server.

#### Scenario: Successful file deletion
- **WHEN** user selects a remote file and clicks "Delete"
- **THEN** system prompts for confirmation and deletes the file after user confirms

#### Scenario: Deletion permission denied
- **WHEN** user attempts to delete a file they don't have permission to delete
- **THEN** system displays permission error message

### Requirement: Rename remote file
The system SHALL support renaming files on the remote SFTP server.

#### Scenario: Successful file rename
- **WHEN** user selects a remote file, clicks "Rename", and enters new filename
- **THEN** system renames the file on the server

#### Scenario: Rename with invalid characters
- **WHEN** user enters filename with invalid characters for remote filesystem
- **THEN** system displays validation error and prevents rename

### Requirement: Handle large file transfers
The system SHALL efficiently handle file transfers of various sizes.

#### Scenario: Transfer large file without memory exhaustion
- **WHEN** user uploads or downloads a file larger than 500MB
- **THEN** system uses streaming/chunked transfer without loading entire file into memory
