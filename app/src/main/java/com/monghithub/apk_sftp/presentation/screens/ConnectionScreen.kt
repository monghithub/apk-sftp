package com.monghithub.apk_sftp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConnectionScreen(
    onConnect: (String, Int, String, String) -> Unit,
    onDisconnect: () -> Unit,
    isConnected: Boolean,
    connectionStatus: String
) {
    val (hostname, setHostname) = remember { mutableStateOf("") }
    val (port, setPort) = remember { mutableStateOf("22") }
    val (username, setUsername) = remember { mutableStateOf("") }
    val (keyPath, setKeyPath) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("SSH Connection")

        if (isConnected) {
            Text("Status: $connectionStatus", modifier = Modifier.padding(8.dp))
            Button(onClick = onDisconnect) {
                Text("Disconnect")
            }
        } else {
            OutlinedTextField(
                value = hostname,
                onValueChange = setHostname,
                label = { Text("Hostname") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = port,
                onValueChange = setPort,
                label = { Text("Port") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = setUsername,
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            OutlinedTextField(
                value = keyPath,
                onValueChange = setKeyPath,
                label = { Text("Key Path") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    onConnect(hostname, port.toIntOrNull() ?: 22, username, keyPath)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Connect")
            }
        }
    }
}
