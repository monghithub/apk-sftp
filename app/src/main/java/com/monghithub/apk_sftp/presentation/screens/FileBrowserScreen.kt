package com.monghithub.apk_sftp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.monghithub.apk_sftp.domain.models.FileInfo

@Composable
fun FileBrowserScreen(
    files: List<FileInfo>,
    currentPath: String,
    onNavigate: (String) -> Unit,
    onBack: () -> Unit,
    onUpload: (String) -> Unit,
    onDownload: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Path: $currentPath", modifier = Modifier.weight(1f))
            Button(onClick = onBack) {
                Text("Back")
            }
        }

        LazyColumn {
            items(files) { file ->
                FileItemRow(
                    file = file,
                    onClick = {
                        if (file.isDirectory) {
                            onNavigate(file.path)
                        }
                    },
                    onUpload = { onUpload(file.path) },
                    onDownload = { onDownload(file.path) }
                )
            }
        }
    }
}

@Composable
fun FileItemRow(
    file: FileInfo,
    onClick: () -> Unit,
    onUpload: () -> Unit,
    onDownload: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(
            text = file.name,
            modifier = Modifier.weight(1f)
        )
        Text("${file.size} bytes")

        if (!file.isDirectory) {
            Button(onClick = onUpload, modifier = Modifier.padding(4.dp)) {
                Text("↑")
            }
            Button(onClick = onDownload, modifier = Modifier.padding(4.dp)) {
                Text("↓")
            }
        }
    }
}
