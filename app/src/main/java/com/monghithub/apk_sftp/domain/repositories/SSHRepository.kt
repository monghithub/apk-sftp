package com.monghithub.apk_sftp.domain.repositories

import com.monghithub.apk_sftp.domain.models.SSHConnection
import kotlinx.coroutines.flow.Flow

interface SSHRepository {
    fun getConnectionState(): Flow<String>
    suspend fun connect(connection: SSHConnection): Result<Unit>
    suspend fun disconnect(): Result<Unit>
    suspend fun isConnected(): Boolean
}
