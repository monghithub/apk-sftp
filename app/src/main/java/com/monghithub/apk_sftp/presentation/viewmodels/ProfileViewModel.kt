package com.monghithub.apk_sftp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monghithub.apk_sftp.data.local.ProfileManager
import com.monghithub.apk_sftp.domain.models.ConnectionProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ProfileViewModel(context: Context) : ViewModel() {
    private val profileManager = ProfileManager(context)

    private val _profiles = MutableStateFlow<List<ConnectionProfile>>(emptyList())
    val profiles: StateFlow<List<ConnectionProfile>> = _profiles.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadProfiles()
    }

    fun loadProfiles() {
        viewModelScope.launch {
            _profiles.value = profileManager.getProfiles()
        }
    }

    fun createProfile(
        name: String,
        hostname: String,
        port: Int,
        username: String,
        keyId: String,
        isDefault: Boolean = false
    ) {
        viewModelScope.launch {
            val profile = ConnectionProfile(
                id = UUID.randomUUID().toString(),
                name = name,
                hostname = hostname,
                port = port,
                username = username,
                keyId = keyId,
                isDefault = isDefault
            )

            val result = profileManager.createProfile(profile)
            result.onSuccess {
                loadProfiles()
            }.onFailure { exception ->
                _error.value = exception.message ?: "Failed to create profile"
            }
        }
    }

    fun deleteProfile(profileId: String) {
        viewModelScope.launch {
            val result = profileManager.deleteProfile(profileId)
            result.onSuccess {
                loadProfiles()
            }.onFailure { exception ->
                _error.value = exception.message ?: "Failed to delete profile"
            }
        }
    }

    fun updateProfile(profile: ConnectionProfile) {
        viewModelScope.launch {
            val result = profileManager.updateProfile(profile)
            result.onSuccess {
                loadProfiles()
            }.onFailure { exception ->
                _error.value = exception.message ?: "Failed to update profile"
            }
        }
    }

    fun getDefaultProfile(): ConnectionProfile? = _profiles.value.firstOrNull { it.isDefault }
}
