package com.monghithub.apk_sftp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monghithub.apk_sftp.data.remote.SSHConnectionManager
import com.monghithub.apk_sftp.domain.models.SSHConnection
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConnectionViewModel : ViewModel() {
    private val sshManager = SSHConnectionManager()

    private val _connectionState = MutableStateFlow("Disconnected")
    val connectionState: StateFlow<String> = _connectionState.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun connect(
        hostname: String,
        port: Int,
        username: String,
        keyPath: String
    ) {
        viewModelScope.launch {
            _connectionState.value = "Connecting"
            _error.value = null

            val connection = SSHConnection(
                hostname = hostname,
                port = port,
                username = username,
                keyPath = keyPath
            )

            val result = sshManager.connect(connection)
            result.onSuccess {
                _connectionState.value = "Connected"
                _connectionState.value = "Connected to ${sshManager.getConnectionInfo()}"
            }.onFailure { exception ->
                _connectionState.value = "Disconnected"
                _error.value = exception.message ?: "Unknown error"
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            val result = sshManager.disconnect()
            result.onSuccess {
                _connectionState.value = "Disconnected"
                _error.value = null
            }.onFailure { exception ->
                _error.value = exception.message ?: "Disconnect failed"
            }
        }
    }

    fun isConnected(): Boolean = sshManager.isConnected()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            sshManager.disconnect()
        }
    }
}
