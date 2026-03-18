package com.monghithub.apk_sftp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.monghithub.apk_sftp.domain.models.FileInfo

@Composable
fun FileBrowserScreen(
    remoteFiles: List<FileInfo>,
    localFiles: List<FileInfo>,
    currentRemotePath: String,
    currentLocalPath: String,
    error: String?,
    transferStatus: String?,
    onNavigateRemote: (String) -> Unit,
    onNavigateRemoteParent: () -> Unit,
    onNavigateLocal: (String) -> Unit,
    onNavigateLocalParent: () -> Unit,
    onUpload: (String) -> Unit,
    onDownload: (String) -> Unit,
    onDisconnect: () -> Unit
) {
    var showRemote by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (showRemote) "Remote" else "Local",
                style = MaterialTheme.typography.titleMedium
            )
            Row {
                FilterChip(
                    selected = showRemote,
                    onClick = { showRemote = true },
                    label = { Text("Remote") },
                    modifier = Modifier.padding(end = 4.dp)
                )
                FilterChip(
                    selected = !showRemote,
                    onClick = { showRemote = false },
                    label = { Text("Local") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = onDisconnect) {
                    Text("Disconnect")
                }
            }
        }

        // Transfer status
        if (transferStatus != null) {
            Text(
                transferStatus,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Error
        if (error != null) {
            Text(
                error,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }

        // Path bar
        val currentPath = if (showRemote) currentRemotePath else currentLocalPath
        val onParent = if (showRemote) onNavigateRemoteParent else onNavigateLocalParent

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onParent) {
                Text("..")
            }
            Text(
                currentPath,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }

        Divider()

        // File list
        val files = if (showRemote) remoteFiles else localFiles

        if (files.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Empty directory", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(files, key = { it.path }) { file ->
                    FileRow(
                        file = file,
                        isRemote = showRemote,
                        onClick = {
                            if (file.isDirectory) {
                                if (showRemote) onNavigateRemote(file.path)
                                else onNavigateLocal(file.path)
                            }
                        },
                        onTransfer = {
                            if (showRemote) onDownload(file.path)
                            else onUpload(file.path)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun FileRow(
    file: FileInfo,
    isRemote: Boolean,
    onClick: () -> Unit,
    onTransfer: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = if (file.isDirectory) "\uD83D\uDCC1" else "\uD83D\uDCC4",
            modifier = Modifier.padding(end = 8.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = file.name,
                fontWeight = if (file.isDirectory) FontWeight.Medium else FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (!file.isDirectory) {
                Text(
                    text = formatSize(file.size),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (!file.isDirectory) {
            TextButton(onClick = onTransfer) {
                Text(if (isRemote) "Download" else "Upload")
            }
        }
    }
}

private fun formatSize(bytes: Long): String {
    if (bytes < 1024) return "$bytes B"
    val kb = bytes / 1024.0
    if (kb < 1024) return String.format("%.1f KB", kb)
    val mb = kb / 1024.0
    if (mb < 1024) return String.format("%.1f MB", mb)
    val gb = mb / 1024.0
    return String.format("%.1f GB", gb)
}
