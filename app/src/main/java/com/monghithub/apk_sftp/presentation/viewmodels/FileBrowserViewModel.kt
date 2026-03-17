package com.monghithub.apk_sftp.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monghithub.apk_sftp.data.local.LocalFileManager
import com.monghithub.apk_sftp.data.remote.SFTPFileManager
import com.monghithub.apk_sftp.data.remote.SSHConnectionManager
import com.monghithub.apk_sftp.domain.models.FileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FileBrowserViewModel(context: Context) : ViewModel() {
    private val localFileManager = LocalFileManager(context)
    private val sshConnectionManager = SSHConnectionManager()
    private val sftpFileManager = SFTPFileManager(sshConnectionManager)

    // Local files
    private val _localFiles = MutableStateFlow<List<FileInfo>>(emptyList())
    val localFiles: StateFlow<List<FileInfo>> = _localFiles.asStateFlow()

    private val _currentLocalPath = MutableStateFlow<String>("")
    val currentLocalPath: StateFlow<String> = _currentLocalPath.asStateFlow()

    // Remote files
    private val _remoteFiles = MutableStateFlow<List<FileInfo>>(emptyList())
    val remoteFiles: StateFlow<List<FileInfo>> = _remoteFiles.asStateFlow()

    private val _currentRemotePath = MutableStateFlow<String>("")
    val currentRemotePath: StateFlow<String> = _currentRemotePath.asStateFlow()

    // Selection
    private val _selectedLocalFiles = MutableStateFlow<Set<String>>(emptySet())
    val selectedLocalFiles: StateFlow<Set<String>> = _selectedLocalFiles.asStateFlow()

    private val _selectedRemoteFiles = MutableStateFlow<Set<String>>(emptySet())
    val selectedRemoteFiles: StateFlow<Set<String>> = _selectedRemoteFiles.asStateFlow()

    init {
        loadLocalFiles()
    }

    fun loadLocalFiles() {
        viewModelScope.launch {
            if (_currentLocalPath.value.isEmpty()) {
                _currentLocalPath.value = localFileManager.getDefaultPath()
            }
            val result = localFileManager.getFiles(_currentLocalPath.value)
            result.onSuccess { files ->
                _localFiles.value = files
            }
        }
    }

    fun navigateLocalDirectory(path: String) {
        viewModelScope.launch {
            _currentLocalPath.value = path
            loadLocalFiles()
        }
    }

    fun navigateLocalParent() {
        viewModelScope.launch {
            val parent = localFileManager.getParentPath(_currentLocalPath.value)
            if (parent != null) {
                _currentLocalPath.value = parent
                loadLocalFiles()
            }
        }
    }

    fun loadRemoteFiles(path: String = "/home") {
        viewModelScope.launch {
            _currentRemotePath.value = path
            val result = sftpFileManager.getRemoteFiles(path)
            result.onSuccess { files ->
                _remoteFiles.value = files
            }
        }
    }

    fun toggleLocalFileSelection(path: String) {
        val current = _selectedLocalFiles.value.toMutableSet()
        if (current.contains(path)) {
            current.remove(path)
        } else {
            current.add(path)
        }
        _selectedLocalFiles.value = current
    }

    fun toggleRemoteFileSelection(path: String) {
        val current = _selectedRemoteFiles.value.toMutableSet()
        if (current.contains(path)) {
            current.remove(path)
        } else {
            current.add(path)
        }
        _selectedRemoteFiles.value = current
    }

    fun clearLocalSelection() {
        _selectedLocalFiles.value = emptySet()
    }

    fun clearRemoteSelection() {
        _selectedRemoteFiles.value = emptySet()
    }
}
