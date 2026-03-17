# Changelog - APK-SFTP

All notable changes to this project will be documented in this file.

## [0.1.0] - 2026-03-17 (Initial Release)

### Added
- **SSH Connection Management**
  - Connect to remote SSH servers with key-based authentication
  - Support for RSA and ED25519 keys
  - Connection timeout handling
  - Automatic reconnection on connection drop
  - Connection status display

- **SSH Key Management**
  - Import SSH private keys from device
  - Secure key storage using Android Keystore
  - Support for passphrase-protected keys
  - Key deletion with confirmation
  - Key information display (fingerprint, type, size)

- **Local File Browser**
  - Browse device file system
  - View file metadata (size, date modified)
  - File selection (single and multiple)
  - Sorting options (name, size, date)
  - File search/filtering

- **Remote File Browser**
  - Browse remote SFTP directories
  - View remote file permissions
  - Directory navigation
  - Refresh remote file list
  - Error handling for permission denied

- **File Transfer Operations**
  - Upload files from device to server
  - Download files from server to device
  - Transfer progress display
  - Transfer cancellation
  - Auto-refresh after transfer
  - Conflict handling (overwrite/skip)
  - Remote file operations (delete, rename)

- **Connection Profiles**
  - Save connection configurations
  - Quick connect from saved profiles
  - Profile editing and deletion
  - Default profile selection
  - Profile export/import

- **User Interface**
  - Jetpack Compose-based modern UI
  - Material Design 3 components
  - Dark mode support
  - Intuitive navigation
  - Real-time connection status
  - Error messages and notifications

- **Security & Reliability**
  - Session timeout after inactivity
  - Automatic logout on app background
  - ProGuard obfuscation in release builds
  - Comprehensive error handling
  - Logging for debugging

### Technical Details
- **Minimum SDK**: Android 8.0 (API 26)
- **Target SDK**: Android 14 (API 34)
- **Language**: Kotlin 1.9.21
- **Architecture**: MVVM + Clean Architecture
- **UI Framework**: Jetpack Compose 1.5.4
- **SSH/SFTP**: JSch 0.1.55

### Known Limitations
- Password-based SSH authentication not supported (key-based only)
- Concurrent transfers not yet supported
- Background transfer persistence not implemented
- Advanced SSH features (SSH agent forwarding) not available

### Future Plans (v0.2.0+)
- Concurrent file transfers
- Background transfer persistence
- SSH terminal access
- Session key forwarding
- Batch operations
- File synchronization
- Cloud storage integration

---

## Format

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).
