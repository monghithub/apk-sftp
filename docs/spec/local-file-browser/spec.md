## ADDED Requirements

### Requirement: Browse device file system
The system SHALL allow users to browse files and directories on the Android device.

#### Scenario: Navigate directory tree
- **WHEN** user opens the local file browser
- **THEN** system displays current directory contents with files and subdirectories

#### Scenario: Enter subdirectory
- **WHEN** user taps on a folder in the file list
- **THEN** system navigates into that directory and displays its contents

#### Scenario: Go back to parent directory
- **WHEN** user clicks "Back" or up arrow button
- **THEN** system navigates to the parent directory

#### Scenario: Display path breadcrumb
- **WHEN** user is browsing directories
- **THEN** system displays the current path (e.g., "/storage/emulated/0/Documents") at top of screen

### Requirement: List file properties
The system SHALL display file information in the browser view.

#### Scenario: Display file details
- **WHEN** browsing a directory
- **THEN** each file entry shows: filename, file size, modification date, and file type icon

#### Scenario: Sort file list
- **WHEN** user taps column header or sort menu
- **THEN** system sorts files by name, size, or date (ascending/descending)

### Requirement: Select files for upload
The system SHALL allow users to select individual or multiple files.

#### Scenario: Select single file
- **WHEN** user taps on a file in the local browser
- **THEN** system highlights the selected file

#### Scenario: Select multiple files
- **WHEN** user taps checkbox or uses long-press to select multiple files
- **THEN** system highlights all selected files and displays selection count

#### Scenario: Deselect files
- **WHEN** user taps selected file again or uses "Clear selection" button
- **THEN** system deselects the file(s)

### Requirement: Upload button action
The system SHALL provide upload action for selected files.

#### Scenario: Upload selected files
- **WHEN** user has selected one or more files and clicks "Upload"
- **THEN** system initiates upload of selected files to the connected remote server

#### Scenario: Upload without selection
- **WHEN** user clicks "Upload" without selecting any files
- **THEN** system displays message "Please select files to upload"

### Requirement: Handle access permissions
The system SHALL respect Android file access permissions.

#### Scenario: Request storage permission
- **WHEN** app first accesses device storage on Android 6+
- **THEN** system requests READ_EXTERNAL_STORAGE or scoped storage permissions from user

#### Scenario: Handle denied permissions
- **WHEN** user denies storage access permission
- **THEN** system displays message and prevents access to file browser

#### Scenario: Scoped storage on Android 11+
- **WHEN** app runs on Android 11+
- **THEN** system uses MediaStore API and DocumentsProvider for file access, respecting scoped storage restrictions

### Requirement: Display file type indicators
The system SHALL help users identify file types.

#### Scenario: Show file type icons
- **WHEN** displaying file list
- **THEN** system shows appropriate icons for file types (document, image, video, audio, folder, etc.)

### Requirement: Search files
The system SHALL allow users to search for files locally.

#### Scenario: Search by filename
- **WHEN** user enters search term in search field
- **THEN** system filters file list to show only files matching the search term
