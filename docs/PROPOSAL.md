## Why

File transfer between machines is currently cumbersome on mobile devices. This APK enables Android users to directly transfer files between their device and remote machines using SFTP over SSH, providing a secure, lightweight alternative to cloud storage or email for file exchanges.

## What Changes

- New Android application with built-in SFTP client functionality
- SSH key-based authentication support for secure remote connections
- Local file browser and remote file browser for selecting files
- Drag-and-drop or button-driven file transfer between device and remote server
- Connection profiles to save and reuse frequent SSH/SFTP connections

## Capabilities

### New Capabilities
- `ssh-connections`: Establish and manage SSH connections to remote machines with key-based authentication
- `sftp-file-transfer`: Upload and download files between Android device and remote SFTP servers
- `ssh-key-management`: Store, import, and manage SSH private keys securely on device
- `local-file-browser`: Browse and select files from Android device storage
- `remote-file-browser`: Browse directory structure on remote SFTP server
- `connection-profiles`: Save connection configurations for quick access to frequently-used servers

### Modified Capabilities
<!-- No existing capabilities are being modified -->

## Impact

- **Code**: New Android application module (Kotlin/Java)
- **Dependencies**: SSH/SFTP libraries (e.g., JSch or Bouncy Castle for key handling)
- **User Impact**: Users gain ability to transfer files securely from Android device to/from remote machines without internet intermediary
- **Security**: Requires secure SSH key storage and proper permission handling for device file access
