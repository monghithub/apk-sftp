package com.monghithub.apk_sftp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.monghithub.apk_sftp.presentation.screens.ConnectionScreen
import com.monghithub.apk_sftp.presentation.screens.FileBrowserScreen
import com.monghithub.apk_sftp.presentation.screens.HomeScreen
import com.monghithub.apk_sftp.presentation.screens.SettingsScreen
import com.monghithub.apk_sftp.presentation.viewmodels.ConnectionViewModel
import com.monghithub.apk_sftp.presentation.viewmodels.FileBrowserViewModel
import com.monghithub.apk_sftp.presentation.viewmodels.ProfileViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

enum class Screen { HOME, CONNECTION, FILE_BROWSER, SETTINGS }

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    val connectionViewModel = remember { ConnectionViewModel() }
    val profileViewModel = remember { ProfileViewModel(context) }
    val fileBrowserViewModel = remember { FileBrowserViewModel(context) }

    // Request permissions on launch
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { /* handled */ }

    LaunchedEffect(Unit) {
        val perms = mutableListOf(Manifest.permission.CAMERA)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            perms.addAll(listOf(
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
            ))
        } else {
            perms.addAll(listOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ))
        }
        val needed = perms.filter {
            ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
        }
        if (needed.isNotEmpty()) {
            permissionLauncher.launch(needed.toTypedArray())
        }
    }

    // Camera support
    var photoFile by remember { mutableStateOf<File?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && photoFile != null) {
            fileBrowserViewModel.uploadFile(photoFile!!.absolutePath)
        }
    }

    fun takePhoto() {
        val photosDir = File(context.cacheDir, "photos")
        photosDir.mkdirs()
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        val file = File(photosDir, "IMG_${timestamp}.jpg")
        photoFile = file
        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
        cameraLauncher.launch(uri)
    }

    val connectionState by connectionViewModel.connectionState.collectAsState()
    val isConnected by connectionViewModel.isConnected.collectAsState()
    val connectionError by connectionViewModel.error.collectAsState()
    val profiles by profileViewModel.profiles.collectAsState()

    LaunchedEffect(isConnected) {
        if (isConnected) {
            currentScreen = Screen.FILE_BROWSER
        }
    }

    when (currentScreen) {
        Screen.HOME -> {
            HomeScreen(
                profiles = profiles,
                onNewConnection = { currentScreen = Screen.CONNECTION },
                onProfileClick = { profile ->
                    connectionViewModel.connect(
                        hostname = profile.hostname,
                        port = profile.port,
                        username = profile.username
                    )
                    currentScreen = Screen.CONNECTION
                },
                onDeleteProfile = { profileViewModel.deleteProfile(it) },
                onSettings = { currentScreen = Screen.SETTINGS }
            )
        }

        Screen.CONNECTION -> {
            ConnectionScreen(
                onConnect = { hostname, port, username, password, keyPath ->
                    connectionViewModel.connect(hostname, port, username, password, keyPath)
                },
                onBack = { currentScreen = Screen.HOME },
                connectionStatus = connectionState,
                error = connectionError,
                isConnected = isConnected
            )
        }

        Screen.FILE_BROWSER -> {
            val remoteFiles by fileBrowserViewModel.remoteFiles.collectAsState()
            val localFiles by fileBrowserViewModel.localFiles.collectAsState()
            val remotePath by fileBrowserViewModel.currentRemotePath.collectAsState()
            val localPath by fileBrowserViewModel.currentLocalPath.collectAsState()
            val browseError by fileBrowserViewModel.error.collectAsState()
            val transferStatus by fileBrowserViewModel.transferStatus.collectAsState()

            FileBrowserScreen(
                remoteFiles = remoteFiles,
                localFiles = localFiles,
                currentRemotePath = remotePath,
                currentLocalPath = localPath,
                error = browseError,
                transferStatus = transferStatus,
                onNavigateRemote = { fileBrowserViewModel.loadRemoteFiles(it) },
                onNavigateRemoteParent = { fileBrowserViewModel.navigateRemoteParent() },
                onNavigateLocal = { fileBrowserViewModel.loadLocalFiles(it) },
                onNavigateLocalParent = { fileBrowserViewModel.navigateLocalParent() },
                onUpload = { fileBrowserViewModel.uploadFile(it) },
                onDownload = { fileBrowserViewModel.downloadFile(it) },
                onTakePhoto = { takePhoto() },
                onDisconnect = {
                    connectionViewModel.disconnect()
                    currentScreen = Screen.HOME
                }
            )
        }

        Screen.SETTINGS -> {
            SettingsScreen(onBack = { currentScreen = Screen.HOME })
        }
    }
}
