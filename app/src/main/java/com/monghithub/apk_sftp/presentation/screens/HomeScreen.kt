package com.monghithub.apk_sftp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.monghithub.apk_sftp.domain.models.ConnectionProfile

@Composable
fun HomeScreen(
    profiles: List<ConnectionProfile>,
    onNewConnection: () -> Unit,
    onProfileClick: (ConnectionProfile) -> Unit,
    onDeleteProfile: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "APK-SFTP",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            "SSH/SFTP Client",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNewConnection,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("New Connection")
        }

        if (profiles.isNotEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                "Saved Profiles",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(profiles, key = { it.id }) { profile ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { onProfileClick(profile) }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    profile.name,
                                    style = MaterialTheme.typography.titleSmall
                                )
                                Text(
                                    "${profile.username}@${profile.hostname}:${profile.port}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            TextButton(onClick = { onDeleteProfile(profile.id) }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}
