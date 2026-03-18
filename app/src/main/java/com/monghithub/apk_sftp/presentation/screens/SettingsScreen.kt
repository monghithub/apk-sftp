package com.monghithub.apk_sftp.presentation.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

data class PermissionItem(
    val name: String,
    val permission: String,
    val description: String,
    val isSpecial: Boolean = false
)

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var refreshTrigger by remember { mutableIntStateOf(0) }

    val permissions = remember {
        listOf(
            PermissionItem("Camera", Manifest.permission.CAMERA, "Take photos to upload"),
            PermissionItem("Location", Manifest.permission.ACCESS_FINE_LOCATION, "Photo metadata"),
            PermissionItem("Read Storage", Manifest.permission.READ_EXTERNAL_STORAGE, "Browse local files"),
            PermissionItem("Write Storage", Manifest.permission.WRITE_EXTERNAL_STORAGE, "Save downloads"),
        ) + if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                PermissionItem("Images", Manifest.permission.READ_MEDIA_IMAGES, "Access photos"),
                PermissionItem("Video", Manifest.permission.READ_MEDIA_VIDEO, "Access videos"),
                PermissionItem("Audio", Manifest.permission.READ_MEDIA_AUDIO, "Access audio"),
            )
        } else emptyList()
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { refreshTrigger++ }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Settings", style = MaterialTheme.typography.headlineSmall)
            TextButton(onClick = onBack) { Text("Back") }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Permissions", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // All Files Access (special permission)
        val hasAllFiles = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else true

        // Force recomposition on refresh
        key(refreshTrigger) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (hasAllFiles) MaterialTheme.colorScheme.primaryContainer
                        else MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text("All Files Access", style = MaterialTheme.typography.titleSmall)
                            Text(
                                "Full access to all files on device",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        if (hasAllFiles) {
                            Text(
                                "Granted",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        } else {
                            Button(onClick = {
                                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                                    data = Uri.parse("package:${context.packageName}")
                                }
                                context.startActivity(intent)
                            }) {
                                Text("Grant")
                            }
                        }
                    }
                }
            }

            // Regular permissions
            permissions.forEach { perm ->
                val granted = ContextCompat.checkSelfPermission(
                    context, perm.permission
                ) == PackageManager.PERMISSION_GRANTED

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (granted) MaterialTheme.colorScheme.surfaceVariant
                        else MaterialTheme.colorScheme.surface
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(perm.name, style = MaterialTheme.typography.titleSmall)
                            Text(perm.description, style = MaterialTheme.typography.bodySmall)
                        }
                        if (granted) {
                            Text(
                                "Granted",
                                color = MaterialTheme.colorScheme.primary,
                                style = MaterialTheme.typography.labelMedium
                            )
                        } else {
                            Text(
                                "Denied",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val needed = permissions.map { it.permission }.filter {
                    ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED
                }
                if (needed.isNotEmpty()) {
                    permissionLauncher.launch(needed.toTypedArray())
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Request All Permissions")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.parse("package:${context.packageName}")
                }
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Open App Settings")
        }
    }
}
