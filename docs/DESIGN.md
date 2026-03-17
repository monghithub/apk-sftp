## Context

The Android SFTP client will enable users to securely transfer files between their mobile device and remote SSH-enabled machines. This is a new standalone Android application that must handle SSH authentication, SFTP protocol implementation, local/remote file browsing, and secure key storage on a mobile device with limited resources and strict permission requirements.

**Constraints:**
- Android API compatibility (target modern Android versions, min API 26+)
- Secure storage of SSH private keys without exposing them to other applications
- Limited device storage and processing power compared to desktop clients
- User permission model for file access (READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, or scoped storage)

## Goals / Non-Goals

**Goals:**
- Enable users to connect to remote SFTP servers via SSH key authentication
- Provide intuitive file browsing for both local device and remote server
- Support basic file operations: upload, download, delete, rename (remote only)
- Securely store and manage SSH private keys on the device
- Support saving and recalling connection profiles
- Provide visual feedback on transfer progress and connection status

**Non-Goals:**
- SSH terminal/shell access (SFTP file transfer only)
- Password-based SSH authentication (key-based only for security and simplicity)
- File compression or advanced transfer options
- Sync or continuous monitoring of directories
- Support for SSH agents or key forwarding

## Decisions

### 1. SSH/SFTP Library: JSch
**Decision:** Use JSch (Java Secure Channel) for SSH and SFTP protocol handling.

**Rationale:**
- Pure Java implementation, no native dependencies required
- Well-tested, widely used in Android apps
- Supports both SSH connections and SFTP file operations
- Actively maintained

**Alternative considered:** Bouncy Castle (crypto only, would require implementing SFTP from scratch) - rejected as too low-level and error-prone.

### 2. Architecture: MVVM with Repository Pattern
**Decision:** Use Model-View-ViewModel (MVVM) with a Repository layer for separation of concerns.

**Structure:**
- **ViewModels**: Manage UI state and lifecycle-aware operations
- **Repository**: Abstracts SSH/SFTP operations and connection management
- **Models**: Connection profiles, file listings, transfer progress
- **UI Layer**: Compose or traditional View framework (scope TBD in specs)

**Rationale:** Enables testability, lifecycle management, and clean separation between business logic and UI.

### 3. Key Storage: Android Keystore
**Decision:** Store SSH private keys in Android Keystore with hardware-backed encryption when available.

**Approach:**
- Keys imported by user are stored with device-specific encryption
- Keys never exist unencrypted in application memory (use streaming where possible)
- EncryptedSharedPreferences for connection profile metadata

**Rationale:**
- Hardware-backed encryption on supported devices provides strong security
- Prevents key extraction even if device is compromised
- Complies with Android security best practices

**Alternative considered:** Simple Base64 encoding in SharedPreferences - rejected due to security implications.

### 4. Async Operations: Coroutines + Flow
**Decision:** Use Kotlin Coroutines for SSH/SFTP operations with Flow for reactive progress updates.

**Rationale:**
- Non-blocking operations prevent UI freezing during transfers
- Built-in error handling and cancellation support
- Natural integration with MVVM ViewModels

### 5. File System Access: Scoped Storage
**Decision:** Support Android 11+ scoped storage requirements using MediaStore and DocumentsProvider.

**Approach:**
- Use MediaStore for accessing photos/documents
- Fallback to DocumentsProvider for compatibility
- Request necessary runtime permissions

**Rationale:** Aligns with modern Android permissions model and app store requirements.

## Risks / Trade-offs

**Risk: Key Exposure on Keystroke**
→ Mitigation: Use streaming/buffering to minimize time keys are in memory. Implement automatic session timeouts.

**Risk: Transfer Interrupted by Network/App Lifecycle**
→ Mitigation: Implement resume capability for large files. Use WorkManager for background transfers if scope allows.

**Risk: User Error in Key Management**
→ Mitigation: Provide clear UI guidance for key import. Pre-validate key format and permissions before storing.

**Risk: Performance with Large File Transfers**
→ Mitigation: Implement chunked uploads/downloads. Use configurable buffer sizes. Monitor memory usage during transfers.

**Risk: Android Version Fragmentation**
→ Mitigation: Target API 26+ initially. Use AndroidX compat libraries. Test on multiple emulator/device configurations.

## Migration Plan

As a new application, no migration needed. Deployment via Google Play Store following standard Android app release process.

## Open Questions

1. **UI Framework**: Jetpack Compose vs. traditional View framework? (Impacts implementation timeline and library choices)
2. **Background Transfers**: Should large transfers continue if app is backgrounded? Requires WorkManager integration.
3. **Concurrent Transfers**: Support multiple simultaneous file transfers? Impacts memory and architectural complexity.
4. **Additional Auth Methods**: Should we support password auth or SSH agent in future versions?
