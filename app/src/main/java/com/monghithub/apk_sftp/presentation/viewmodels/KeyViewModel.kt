package com.monghithub.apk_sftp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monghithub.apk_sftp.data.local.KeyManager
import com.monghithub.apk_sftp.domain.models.SSHKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class KeyViewModel(context: Context) : ViewModel() {
    private val keyManager = KeyManager(context)

    private val _keys = MutableStateFlow<List<SSHKey>>(emptyList())
    val keys: StateFlow<List<SSHKey>> = _keys.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun importKey(keyPath: String, name: String) {
        viewModelScope.launch {
            val result = keyManager.importKey(keyPath, name)
            result.onSuccess { key ->
                _keys.value = _keys.value + key
            }.onFailure { exception ->
                _error.value = exception.message ?: "Failed to import key"
            }
        }
    }

    fun deleteKey(keyId: String) {
        viewModelScope.launch {
            val result = keyManager.deleteKey(keyId)
            result.onSuccess {
                _keys.value = _keys.value.filterNot { it.id == keyId }
            }.onFailure { exception ->
                _error.value = exception.message ?: "Failed to delete key"
            }
        }
    }

    fun renameKey(keyId: String, newName: String) {
        viewModelScope.launch {
            val result = keyManager.renameKey(keyId, newName)
            result.onSuccess {
                _keys.value = _keys.value.map {
                    if (it.id == keyId) it.copy(name = newName) else it
                }
            }.onFailure { exception ->
                _error.value = exception.message ?: "Failed to rename key"
            }
        }
    }

    fun loadKeys() {
        viewModelScope.launch {
            _keys.value = keyManager.getKeys()
        }
    }
}
