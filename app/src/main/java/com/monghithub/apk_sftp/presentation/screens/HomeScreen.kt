package com.monghithub.apk_sftp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onConnectionsClick: () -> Unit,
    onProfilesClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("APK-SFTP - Android SSH/SFTP Client")

        Button(onClick = onConnectionsClick, modifier = Modifier.padding(8.dp)) {
            Text("New Connection")
        }

        Button(onClick = onProfilesClick, modifier = Modifier.padding(8.dp)) {
            Text("Saved Profiles")
        }

        Button(onClick = onSettingsClick, modifier = Modifier.padding(8.dp)) {
            Text("Settings")
        }
    }
}
