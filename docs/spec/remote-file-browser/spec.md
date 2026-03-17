## ADDED Requirements

### Requirement: Browse remote server file system
The system SHALL allow users to browse files and directories on the remote SFTP server.

#### Scenario: Display remote directory contents
- **WHEN** SSH connection is established and user opens remote file browser
- **THEN** system displays contents of remote home directory or user's default location

#### Scenario: Navigate remote directories
- **WHEN** user taps on a remote folder
- **THEN** system navigates into that directory and displays its contents from server

#### Scenario: Go back to parent directory
- **WHEN** user clicks "Back" or up arrow button while browsing remote
- **THEN** system navigates to the parent directory on the remote server

#### Scenario: Display remote path
- **WHEN** browsing remote directories
- **THEN** system displays the current remote path (e.g., "/home/user/documents") at top of screen

### Requirement: List remote file properties
The system SHALL display file information from remote server.

#### Scenario: Display remote file details
- **WHEN** browsing a remote directory
- **THEN** each file entry shows: filename, file size, modification date, permissions, and file type icon

#### Scenario: Show file permissions
- **WHEN** viewing remote file list
- **THEN** system displays POSIX file permissions (e.g., -rw-r--r--) for each file

#### Scenario: Sort remote file list
- **WHEN** user taps column header or sort menu
- **THEN** system sorts remote files by name, size, date, or permissions

### Requirement: Select remote files for download
The system SHALL allow users to select files from remote server.

#### Scenario: Select single remote file
- **WHEN** user taps on a remote file
- **THEN** system highlights the selected file

#### Scenario: Select multiple remote files
- **WHEN** user selects multiple remote files using checkboxes or long-press
- **THEN** system highlights all selected files and displays selection count

#### Scenario: Deselect remote files
- **WHEN** user taps selected file again or uses "Clear selection"
- **THEN** system deselects the file(s)

### Requirement: Download button action
The system SHALL provide download action for selected remote files.

#### Scenario: Download selected files
- **WHEN** user has selected one or more remote files and clicks "Download"
- **THEN** system initiates download of selected files to the device

#### Scenario: Download without selection
- **WHEN** user clicks "Download" without selecting any files
- **THEN** system displays message "Please select files to download"

### Requirement: Handle server errors
The system SHALL gracefully handle remote server errors.

#### Scenario: Connection lost during browsing
- **WHEN** SSH connection is lost while browsing remote files
- **THEN** system displays error message and returns to connection screen

#### Scenario: Permission denied accessing directory
- **WHEN** user attempts to navigate to a directory they lack read permissions
- **THEN** system displays "Permission denied" error and remains in current directory

#### Scenario: Directory no longer exists
- **WHEN** attempting to refresh or navigate to a remote directory that has been deleted
- **THEN** system displays error and navigates to parent directory

### Requirement: Search remote files
The system SHALL allow users to search for files on remote server.

#### Scenario: Search remote files by name
- **WHEN** user enters search term in remote search field
- **THEN** system filters remote file list to show matching files

### Requirement: Refresh remote file list
The system SHALL allow users to update the remote file listing.

#### Scenario: Refresh current directory
- **WHEN** user clicks "Refresh" button
- **THEN** system re-queries remote server for current directory contents and updates display

#### Scenario: Auto-refresh after transfer
- **WHEN** a file upload or download completes
- **THEN** system automatically refreshes the appropriate file browser (remote or local)
