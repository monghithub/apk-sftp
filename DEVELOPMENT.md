# APK-SFTP Development Guide

## Setup

### Prerequisites
- Android Studio Arctic Fox or later
- JDK 11+
- Android SDK 34+
- Git

### Initial Setup
```bash
git clone https://github.com/monghithub/apk-sftp.git
cd apk-sftp
./gradlew build
```

### Android SDK
Ensure `local.properties` contains:
```properties
sdk.dir=/path/to/Android/Sdk
```

## Project Structure

### Key Files
- `app/build.gradle.kts` - Build configuration, dependencies
- `app/src/main/AndroidManifest.xml` - App manifest, permissions
- `app/src/main/java/` - Source code organized by layer
- `gradle/wrapper/` - Gradle wrapper configuration

### Important Classes
```
Presentation Layer:
- MainActivity.kt - App entry point
- AppNavGraph.kt - Navigation setup
- *Screen.kt - UI screens (Compose)
- *ViewModel.kt - State management

Domain Layer:
- FileInfo.kt - File metadata model
- SSHKey.kt - SSH key model
- ConnectionProfile.kt - Connection profile model
- SSHConnection.kt - SSH connection parameters

Data Layer:
- SSHConnectionManager.kt - SSH session management
- SFTPFileManager.kt - SFTP operations
- KeyManager.kt - Key storage & retrieval
- ProfileManager.kt - Profile persistence
- LocalFileManager.kt - Local file operations

Utils:
- Logger.kt - Logging
- ErrorHandler.kt - Error processing
- NotificationHelper.kt - Notifications
```

## Build & Run

### Debug Build
```bash
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

### Run on Emulator
```bash
./gradlew installDebug
./gradlew :app:connectedAndroidTest
```

### Build Release APK
```bash
./gradlew assembleRelease
# APK: app/build/outputs/apk/release/app-release.apk
```

## Code Organization

### ViewModels (State Management)
```kotlin
class MyViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>()
    val state: StateFlow<State> = _state.asStateFlow()

    fun doAction() {
        viewModelScope.launch {
            _state.value = State.Loading
            val result = repository.perform()
            _state.value = State.Success(result)
        }
    }
}
```

### Compose Screens
```kotlin
@Composable
fun MyScreen(navController: NavHostController, viewModel: MyViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(topBar = { ... }) { padding ->
        // UI implementation
    }
}
```

## Testing

### Unit Tests
Location: `app/src/test/java/`
```bash
./gradlew test
```

### Integration Tests
Location: `app/src/androidTest/java/`
```bash
./gradlew connectedAndroidTest
```

### Test Managers
- SSHConnectionManagerTest.kt
- SFTPFileManagerTest.kt
- KeyManagerTest.kt
- ProfileManagerTest.kt

## Adding Features

### Adding New Screen
1. Create `NewScreen.kt` in `presentation/screens/`
2. Create `NewViewModel.kt` in `presentation/viewmodels/`
3. Add route to `AppNavGraph.kt`
4. Add navigation action to HomeScreen or relevant screen

### Adding New Manager
1. Create manager in `data/local/` or `data/remote/`
2. Implement suspend functions with Result<T>
3. Use proper error handling
4. Integrate with existing ViewModels

### Adding New Model
1. Create data class in `domain/models/`
2. Add @Serializable if needed for storage
3. Use in appropriate manager/ViewModel
4. Add mapping functions if multiple representations

## Dependencies

### Current Dependencies (in build.gradle.kts)
- **Kotlin**: 1.9.22
- **Compose**: 1.5.10
- **Android X**: Navigation 2.7.5, Security Crypto, Lifecycle
- **JSch**: 0.1.55 (SSH/SFTP)
- **Serialization**: kotlinx.serialization 1.6.0

### Adding New Dependency
```kotlin
// In build.gradle.kts
dependencies {
    implementation "group:artifact:version"
}
```

## Common Issues

### Build Issues
1. **Gradle sync fails**: Clean and rebuild
   ```bash
   ./gradlew clean build
   ```

2. **Kotlin version mismatch**: Update Kotlin plugin in Android Studio

3. **Missing SDK**: Run SDK Manager to download required APIs

### Runtime Issues
1. **JSch connection fails**: Check host key, port, credentials
2. **File permissions**: Ensure scoped storage compatibility (Android 11+)
3. **Keystore errors**: Verify Android Keystore access

## Performance Optimization

### Memory Management
- Use `viewModelScope.launch` for coroutines (auto-cancel)
- Avoid holding strong references to Activities
- Use `WeakReference` for context if needed
- Monitor memory usage in large file transfers

### Network Optimization
- Implement progress callbacks for UI updates
- Use chunked transfers for large files
- Implement connection pooling if needed
- Cache file listings when appropriate

### UI Performance
- Use `LazyColumn` for large lists
- Avoid recomposition with `remember`
- Use proper state management with `StateFlow`
- Profile with Jetpack Compose tools

## Debugging

### Logging
Use Logger utility:
```kotlin
Logger.d("TAG", "Debug message")
Logger.e("TAG", "Error message", throwable)
Logger.logConnectionAttempt(host, port, user)
```

### Android Studio Tools
- Logcat: View system logs
- Debugger: Step through code
- Profiler: Monitor memory, CPU, network
- Layout Inspector: Debug UI hierarchy

### Testing Tools
- Espresso: UI testing
- Mockito: Mocking
- JUnit: Unit testing

## Contributing

### Code Style
- Follow Kotlin conventions
- Use meaningful variable names
- Add comments for complex logic
- Keep functions small and focused

### Commit Messages
```
[FEAT] Add new feature description
[FIX] Fix specific issue
[REFACTOR] Refactor code section
[DOCS] Documentation update
[TEST] Add/update tests
```

### Pull Requests
1. Create feature branch: `git checkout -b feature/description`
2. Implement feature with tests
3. Submit PR with clear description
4. Address review comments

## Future Enhancements

### Phase 10 - Testing
- [ ] Unit tests for all managers
- [ ] Integration tests for SSH flow
- [ ] UI tests with Espresso

### Phase 11 - Security
- [ ] Certificate pinning
- [ ] Session timeout
- [ ] Automatic logout
- [ ] Memory profiling

### Phase 12 - Release
- [ ] Play Store metadata
- [ ] Privacy policy
- [ ] Release notes
- [ ] Signed APK

## Resources

- [Android Docs](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [JSch Documentation](http://www.jcraft.com/jsch/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## Contact & Support

For issues, create GitHub issue or contact maintainers.
Repository: https://github.com/monghithub/apk-sftp
