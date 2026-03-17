package com.monghithub.apk_sftp.data.remote

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import com.monghithub.apk_sftp.domain.models.SSHConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties

class SSHConnectionManager {
    private val jsch = JSch()
    private var session: Session? = null
    private var isConnected = false

    suspend fun connect(connection: SSHConnection, timeoutMs: Int = 30000): Result<Unit> =
        withContext(Dispatchers.Default) {
            try {
                // Create session
                session = jsch.getSession(
                    connection.username,
                    connection.hostname,
                    connection.port
                ).apply {
                    // Add private key
                    jsch.addIdentity(connection.keyPath)

                    // Configure session properties
                    val config = Properties()
                    config["StrictHostKeyChecking"] = "no"
                    config["PreferredAuthentications"] = "publickey"
                    setConfig(config)

                    // Set timeout
                    setServerAliveInterval(30000)
                    serverAliveCountMax = 3
                }

                // Connect
                session?.connect(timeoutMs)
                isConnected = true
                Result.success(Unit)
            } catch (e: Exception) {
                isConnected = false
                Result.failure(e)
            }
        }

    suspend fun disconnect(): Result<Unit> = withContext(Dispatchers.Default) {
        try {
            session?.disconnect()
            isConnected = false
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getSession(): Session? = session

    fun isConnected(): Boolean = isConnected && session?.isConnected == true

    fun getConnectionInfo(): String? = session?.host?.let { host ->
        "${session?.userName}@$host:${session?.port}"
    }
}
