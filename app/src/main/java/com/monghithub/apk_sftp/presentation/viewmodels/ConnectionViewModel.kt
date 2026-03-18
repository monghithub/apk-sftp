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

    private val _connectionState = MutableStateFlow("Disconnected")
    val connectionState: StateFlow<String> = _connectionState.asStateFlow()

    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> = _isConnected.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun connect(
        hostname: String,
        port: Int,
        username: String,
        password: String? = null,
        keyPath: String = ""
    ) {
        viewModelScope.launch {
            _connectionState.value = "Connecting..."
            _error.value = null

            val connection = SSHConnection(
                hostname = hostname,
                port = port,
                username = username,
                keyPath = keyPath,
                password = password
            )

            val result = SSHConnectionManager.connect(connection)
            result.onSuccess {
                _isConnected.value = true
                _connectionState.value = "Connected to ${SSHConnectionManager.getConnectionInfo()}"
            }.onFailure { exception ->
                _isConnected.value = false
                _connectionState.value = "Disconnected"
                _error.value = exception.message ?: "Connection failed"
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            SSHConnectionManager.disconnect()
            _isConnected.value = false
            _connectionState.value = "Disconnected"
            _error.value = null
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            SSHConnectionManager.disconnect()
        }
    }
}
