## 1. Project Setup & Dependencies

- [ ] 1.1 Create Android Gradle project with MVVM architecture
- [ ] 1.2 Add JSch SSH/SFTP library dependency
- [ ] 1.3 Add AndroidX Lifecycle and ViewModel dependencies
- [ ] 1.4 Add Jetpack Compose UI framework (or evaluate traditional Views)
- [ ] 1.5 Configure Android Keystore for secure key storage
- [ ] 1.6 Add EncryptedSharedPreferences for storing metadata
- [ ] 1.7 Add WorkManager dependency for background tasks (optional future feature)
- [ ] 1.8 Configure build variants and proguard rules

## 2. SSH Connection Infrastructure

- [ ] 2.1 Create SSH connection manager class using JSch
- [ ] 2.2 Implement SSH session creation and lifecycle management
- [ ] 2.3 Add connection timeout and error handling
- [ ] 2.4 Implement automatic connection cleanup on app exit
- [ ] 2.5 Create ConnectionViewModel for managing connection state
- [ ] 2.6 Implement connection status live data (connecting, connected, disconnected)
- [ ] 2.7 Add SSH connection error handler for network/auth failures
- [ ] 2.8 Implement reconnection logic for dropped connections

## 3. SSH Key Management

- [ ] 3.1 Create KeyManager class for Android Keystore operations
- [ ] 3.2 Implement secure key import from device files
- [ ] 3.3 Add passphrase-protected key decryption support
- [ ] 3.4 Implement key validation and format checking
- [ ] 3.5 Create KeyViewModel for managing key operations
- [ ] 3.6 Build key import UI screen (file picker + validation)
- [ ] 3.7 Build key list screen showing imported keys with fingerprints
- [ ] 3.8 Implement key deletion with confirmation dialog
- [ ] 3.9 Add key rename functionality
- [ ] 3.10 Implement key detail view (type, algorithm, fingerprint, import date)
- [ ] 3.11 Add streaming key access to avoid loading full key in memory

## 4. Local File Browser

- [ ] 4.1 Create LocalFileManager for device file system access
- [ ] 4.2 Implement scoped storage support for Android 11+
- [ ] 4.3 Add storage permission request and handling
- [ ] 4.4 Build LocalFileViewModel for state management
- [ ] 4.5 Create LocalFileBrowserScreen UI component
- [ ] 4.6 Implement directory navigation (enter, back, breadcrumb)
- [ ] 4.7 Add file list display with icons and metadata
- [ ] 4.8 Implement file selection (single and multi-select)
- [ ] 4.9 Add file sorting (name, size, date)
- [ ] 4.10 Implement file search/filtering
- [ ] 4.11 Add upload button to initiate file transfer

## 5. Remote File Browser

- [ ] 5.1 Create SFTPFileManager for remote file operations
- [ ] 5.2 Implement remote directory listing via SFTP
- [ ] 5.3 Build RemoteFileViewModel for state management
- [ ] 5.4 Create RemoteFileBrowserScreen UI component
- [ ] 5.5 Implement directory navigation on remote server
- [ ] 5.6 Add file list display with permissions and metadata
- [ ] 5.7 Implement file selection (single and multi-select)
- [ ] 5.8 Add file sorting (name, size, date, permissions)
- [ ] 5.9 Implement file search/filtering
- [ ] 5.10 Add remote path display with breadcrumb
- [ ] 5.11 Implement refresh button to re-query server
- [ ] 5.12 Add error handling for permission denied and missing directories

## 6. File Transfer Operations

- [ ] 6.1 Implement SFTP upload using JSch
- [ ] 6.2 Implement SFTP download using JSch
- [ ] 6.3 Add chunked/streaming transfer for large files
- [ ] 6.4 Create progress tracking (bytes, percentage, speed)
- [ ] 6.5 Build file transfer progress UI
- [ ] 6.6 Implement transfer cancellation
- [ ] 6.7 Add error handling for transfer failures
- [ ] 6.8 Implement partial file cleanup on failed transfers
- [ ] 6.9 Add overwrite/rename/skip dialog for duplicate downloads
- [ ] 6.10 Implement remote file deletion
- [ ] 6.11 Implement remote file rename
- [ ] 6.12 Add auto-refresh of file browsers after transfer completes

## 7. Connection Profiles

- [ ] 7.1 Create ConnectionProfile data model
- [ ] 7.2 Implement profile persistence using EncryptedSharedPreferences
- [ ] 7.3 Build ProfileViewModel for managing profiles
- [ ] 7.4 Create ProfileListScreen UI
- [ ] 7.5 Build create profile dialog/screen
- [ ] 7.6 Implement profile editing
- [ ] 7.7 Implement profile deletion with confirmation
- [ ] 7.8 Add profile quick connect functionality
- [ ] 7.9 Implement profile reordering/favorite marking
- [ ] 7.10 Add profile rename functionality
- [ ] 7.11 Implement profile export (JSON format, no keys)
- [ ] 7.12 Implement profile import from backup file
- [ ] 7.13 Add default profile selection for auto-connect

## 8. Main Application UI & Navigation

- [ ] 8.1 Build home screen with recent profiles and connection status
- [ ] 8.2 Create navigation structure (Compose Navigation or fragment navigation)
- [ ] 8.3 Build main activity with navigation and app bar
- [ ] 8.4 Implement tab or drawer navigation between screens
- [ ] 8.5 Create connection screen with hostname/username/key input
- [ ] 8.6 Build dual-pane layout for file transfer (local + remote browsers)
- [ ] 8.7 Add connection status display in app bar
- [ ] 8.8 Implement disconnect button and connection menu
- [ ] 8.9 Add app settings/preferences screen
- [ ] 8.10 Implement app theming (light/dark mode support)

## 9. Error Handling & User Feedback

- [ ] 9.1 Create comprehensive error handling for SSH errors
- [ ] 9.2 Implement user-friendly error messages
- [ ] 9.3 Add retry dialogs for transient errors
- [ ] 9.4 Implement toast notifications for transfer events
- [ ] 9.5 Add logging for debugging connection issues
- [ ] 9.6 Implement timeout handling for all network operations
- [ ] 9.7 Add recovery UI for connection drops
- [ ] 9.8 Create troubleshooting guide in app help

## 10. Testing

- [ ] 10.1 Write unit tests for SFTPFileManager
- [ ] 10.2 Write unit tests for SSHConnectionManager
- [ ] 10.3 Write unit tests for KeyManager
- [ ] 10.4 Write unit tests for ConnectionProfile persistence
- [ ] 10.5 Write ViewModel unit tests with LiveData testing
- [ ] 10.6 Create integration test for SSH connection flow
- [ ] 10.7 Create integration test for file transfer operations
- [ ] 10.8 Test on multiple Android devices/API levels
- [ ] 10.9 Test SSH key import and permission handling
- [ ] 10.10 Test connection profile export/import

## 11. Security & Optimization

- [ ] 11.1 Add ProGuard/R8 rules for code obfuscation
- [ ] 11.2 Implement certificate pinning for known servers (optional)
- [ ] 11.3 Add session timeout functionality
- [ ] 11.4 Implement automatic logout on app background
- [ ] 11.5 Add app signing configuration for release builds
- [ ] 11.6 Profile memory usage during large file transfers
- [ ] 11.7 Optimize transfer buffer sizes for device capabilities
- [ ] 11.8 Add connection pooling (optional for multiple concurrent transfers)

## 12. Release Preparation

- [ ] 12.1 Add app icon and branding assets
- [ ] 12.2 Write app description and feature list for Play Store
- [ ] 12.3 Create privacy policy for data handling
- [ ] 12.4 Add changelog/release notes
- [ ] 12.5 Configure Play Store app metadata
- [ ] 12.6 Build and sign release APK
- [ ] 12.7 Test signed APK on physical device
- [ ] 12.8 Submit to Play Store for review
- [ ] 12.9 Monitor Play Store reviews and ratings
- [ ] 12.10 Plan for v2 features (background transfers, concurrent connections, etc.)
