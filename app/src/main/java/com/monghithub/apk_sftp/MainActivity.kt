package com.monghithub.apk_sftp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.monghithub.apk_sftp.presentation.screens.ConnectionScreen
import com.monghithub.apk_sftp.presentation.screens.FileBrowserScreen
import com.monghithub.apk_sftp.presentation.screens.HomeScreen
import com.monghithub.apk_sftp.presentation.viewmodels.ConnectionViewModel
import com.monghithub.apk_sftp.presentation.viewmodels.FileBrowserViewModel
import com.monghithub.apk_sftp.presentation.viewmodels.ProfileViewModel

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

enum class Screen { HOME, CONNECTION, FILE_BROWSER }

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    val connectionViewModel = remember { ConnectionViewModel() }
    val profileViewModel = remember { ProfileViewModel(context) }
    val fileBrowserViewModel = remember { FileBrowserViewModel(context) }

    val connectionState by connectionViewModel.connectionState.collectAsState()
    val isConnected by connectionViewModel.isConnected.collectAsState()
    val connectionError by connectionViewModel.error.collectAsState()
    val profiles by profileViewModel.profiles.collectAsState()

    // Navigate to file browser when connected
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
                onDeleteProfile = { profileViewModel.deleteProfile(it) }
            )
        }

        Screen.CONNECTION -> {
            ConnectionScreen(
                onConnect = { hostname, port, username, password, keyPath ->
                    connectionViewModel.connect(hostname, port, username, password, keyPath)
                },
                onBack = {
                    currentScreen = Screen.HOME
                },
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
                onDisconnect = {
                    connectionViewModel.disconnect()
                    currentScreen = Screen.HOME
                }
            )
        }
    }
}
