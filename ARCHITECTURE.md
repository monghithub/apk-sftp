# APK-SFTP Architecture

## Project Overview
Android SFTP client application for secure file transfer over SSH/SFTP protocol using JSch library.

## Architecture Pattern
**MVVM (Model-View-ViewModel) + Clean Architecture**

### Layers
```
Presentation (UI/Screens)
    ↓
ViewModel (State Management)
    ↓
Domain (Models/Use Cases)
    ↓
Data (Local/Remote Storage)
```

## Directory Structure
```
app/src/main/java/com/monghithub/apk_sftp/
├── presentation/
│   ├── screens/           # Jetpack Compose UI screens
│   ├── viewmodels/        # State management (ConnectionVM, FileBrowserVM, etc.)
│   └── navigation/        # AppNavGraph, routes
├── domain/
│   └── models/            # Data classes (FileInfo, SSHKey, ConnectionProfile, etc.)
├── data/
│   ├── local/             # Android Keystore, EncryptedSharedPreferences
│   └── remote/            # SSH/SFTP operations via JSch
└── utils/
    ├── Logger.kt          # Logging system
    ├── ErrorHandler.kt    # Error classification & handling
    └── NotificationHelper.kt  # Notification utilities
```

## Core Components

### 1. SSH/SFTP Layer (data/remote/)
- **SSHConnectionManager**: Manages SSH sessions with JSch
  - Connection/disconnection
  - Reconnection logic with exponential backoff
  - Session lifecycle management

- **SFTPFileManager**: SFTP operations
  - Upload/download files
  - Remote directory listing
  - File rename/delete
  - Progress tracking

### 2. Local Storage Layer (data/local/)
- **KeyManager**: SSH key management
  - Secure import from device
  - Storage in Android Keystore (AES256_GCM)
  - Key validation & fingerprinting

- **ProfileManager**: Connection profile persistence
  - CRUD operations
  - EncryptedSharedPreferences storage
  - Profile export/import (without keys)
  - Favorite marking & reordering

- **LocalFileManager**: Device file system access
  - File listing with scoped storage support
  - Directory navigation
  - Metadata retrieval

### 3. UI Layer (presentation/)
- **Screens** (Jetpack Compose):
  - HomeScreen: Dashboard with connection status
  - ConnectionScreen: SSH connection form
  - FileBrowserScreen: Dual-pane file browser (local/remote)
  - KeyManagementScreen: SSH key management
  - ProfileManagementScreen: Connection profiles
  - SettingsScreen: App configuration

- **ViewModels**: State management with StateFlow
  - ConnectionViewModel: SSH connection state
  - FileBrowserViewModel: File operations & progress
  - KeyViewModel: Key management operations
  - ProfileViewModel: Profile CRUD operations

## Key Features

### Security
- ✅ Android Keystore (AES256_GCM) for key encryption
- ✅ EncryptedSharedPreferences for metadata
- ✅ Passphrase-protected SSH keys support
- ✅ Host key verification option

### Functionality
- ✅ SSH key import/management
- ✅ SFTP upload/download with progress tracking
- ✅ Connection profiles with quick connect
- ✅ Local & remote file browsing
- ✅ File rename/delete operations
- ✅ Transfer cancellation
- ✅ Auto-reconnection with backoff

### User Experience
- ✅ Material Design 3 UI
- ✅ Real-time progress indicators
- ✅ Error handling with user-friendly messages
- ✅ Settings screen for configuration
- ✅ Logging system for debugging

## Data Flow

### SSH Connection Flow
```
ConnectionScreen
    ↓
ConnectionViewModel.connect()
    ↓
SSHConnectionManager.connect()
    ↓ (JSch Session)
Successful → Update state → Enable file operations
Failed → Error callback → Show error message
```

### File Transfer Flow
```
FileBrowserViewModel.downloadFile()
    ↓
SFTPFileManager.downloadFile()
    ↓ (progress callback)
Update progress UI in real-time
    ↓
Success → Refresh file list
Error → Show error + retry option
```

## Dependencies
- **androidx.compose** (UI framework)
- **androidx.navigation** (Navigation Compose)
- **androidx.security** (Android Keystore)
- **com.jcraft:jsch** (SSH/SFTP protocol)
- **org.jetbrains.kotlinx:kotlinx-serialization** (JSON)
- **androidx.lifecycle** (ViewModel, LiveData)

## Build Configuration
- **compileSdk**: 34
- **minSdk**: 26
- **targetSdk**: 34
- **Kotlin**: 1.9.22
- **Gradle**: 8.1.0

## Testing (Phase 10)
- Unit tests for managers
- ViewModel tests
- Integration tests for SSH flow

## Security Optimizations (Phase 11)
- Certificate pinning (optional)
- Session timeout functionality
- Automatic logout on background
- Memory profiling for large transfers

## Release Process (Phase 12)
- Play Store metadata
- Privacy policy
- Release notes & changelog
- Signed APK generation
