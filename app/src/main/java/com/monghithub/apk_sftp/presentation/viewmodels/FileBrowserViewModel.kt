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
    private val sftpFileManager = SFTPFileManager(SSHConnectionManager)

    private val _remoteFiles = MutableStateFlow<List<FileInfo>>(emptyList())
    val remoteFiles: StateFlow<List<FileInfo>> = _remoteFiles.asStateFlow()

    private val _currentRemotePath = MutableStateFlow("/home")
    val currentRemotePath: StateFlow<String> = _currentRemotePath.asStateFlow()

    private val _localFiles = MutableStateFlow<List<FileInfo>>(emptyList())
    val localFiles: StateFlow<List<FileInfo>> = _localFiles.asStateFlow()

    private val _currentLocalPath = MutableStateFlow("")
    val currentLocalPath: StateFlow<String> = _currentLocalPath.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val _transferStatus = MutableStateFlow<String?>(null)
    val transferStatus: StateFlow<String?> = _transferStatus.asStateFlow()

    init {
        loadLocalFiles()
        loadRemoteFiles()
    }

    fun loadRemoteFiles(path: String = _currentRemotePath.value) {
        viewModelScope.launch {
            _error.value = null
            _currentRemotePath.value = path
            val result = sftpFileManager.getRemoteFiles(path)
            result.onSuccess { files ->
                _remoteFiles.value = files
            }.onFailure { e ->
                _error.value = "Remote: ${e.message}"
            }
        }
    }

    fun navigateRemoteParent() {
        val parent = _currentRemotePath.value.substringBeforeLast("/")
        loadRemoteFiles(if (parent.isEmpty()) "/" else parent)
    }

    fun loadLocalFiles(path: String = _currentLocalPath.value) {
        viewModelScope.launch {
            if (path.isEmpty()) {
                _currentLocalPath.value = localFileManager.getDefaultPath()
            } else {
                _currentLocalPath.value = path
            }
            val result = localFileManager.getFiles(_currentLocalPath.value)
            result.onSuccess { files ->
                _localFiles.value = files
            }
        }
    }

    fun navigateLocalParent() {
        viewModelScope.launch {
            val parent = localFileManager.getParentPath(_currentLocalPath.value)
            if (parent != null) {
                loadLocalFiles(parent)
            }
        }
    }

    fun uploadFile(localPath: String) {
        viewModelScope.launch {
            _transferStatus.value = "Uploading..."
            val remoteDest = "${_currentRemotePath.value}/${localPath.substringAfterLast("/")}"
            val result = sftpFileManager.uploadFile(localPath, remoteDest)
            result.onSuccess {
                _transferStatus.value = "Upload complete"
                loadRemoteFiles()
            }.onFailure { e ->
                _transferStatus.value = "Upload failed: ${e.message}"
            }
        }
    }

    fun downloadFile(remotePath: String) {
        viewModelScope.launch {
            _transferStatus.value = "Downloading..."
            val localDest = "${_currentLocalPath.value}/${remotePath.substringAfterLast("/")}"
            val result = sftpFileManager.downloadFile(remotePath, localDest)
            result.onSuccess {
                _transferStatus.value = "Download complete"
                loadLocalFiles()
            }.onFailure { e ->
                _transferStatus.value = "Download failed: ${e.message}"
            }
        }
    }

    fun clearTransferStatus() {
        _transferStatus.value = null
    }
}
