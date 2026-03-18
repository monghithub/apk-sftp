package com.monghithub.apk_sftp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ConnectionScreen(
    onConnect: (hostname: String, port: Int, username: String, password: String?, keyPath: String) -> Unit,
    onBack: () -> Unit,
    connectionStatus: String,
    error: String?,
    isConnected: Boolean
) {
    var hostname by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("22") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var keyPath by remember { mutableStateOf("") }
    var usePassword by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "New Connection",
                style = MaterialTheme.typography.headlineSmall
            )
            TextButton(onClick = onBack) {
                Text("Cancel")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = hostname,
            onValueChange = { hostname = it },
            label = { Text("Hostname") },
            placeholder = { Text("192.168.1.100") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = port,
            onValueChange = { port = it },
            label = { Text("Port") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            FilterChip(
                selected = usePassword,
                onClick = { usePassword = true },
                label = { Text("Password") },
                modifier = Modifier.padding(end = 8.dp)
            )
            FilterChip(
                selected = !usePassword,
                onClick = { usePassword = false },
                label = { Text("SSH Key") }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (usePassword) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            OutlinedTextField(
                value = keyPath,
                onValueChange = { keyPath = it },
                label = { Text("Key file path") },
                placeholder = { Text("/storage/emulated/0/Download/id_rsa") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (connectionStatus != "Disconnected") {
            Text(
                connectionStatus,
                color = if (isConnected) MaterialTheme.colorScheme.primary
                       else MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (error != null) {
            Text(
                error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                val pwd = if (usePassword && password.isNotBlank()) password else null
                val key = if (!usePassword && keyPath.isNotBlank()) keyPath else ""
                onConnect(hostname, port.toIntOrNull() ?: 22, username, pwd, key)
            },
            enabled = hostname.isNotBlank() && username.isNotBlank() &&
                    (usePassword && password.isNotBlank() || !usePassword && keyPath.isNotBlank()) &&
                    connectionStatus != "Connecting...",
            modifier = Modifier.fillMaxWidth()
        ) {
            if (connectionStatus == "Connecting...") {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text("Connect")
        }
    }
}
