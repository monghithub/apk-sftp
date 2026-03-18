package com.monghithub.apk_sftp.data.remote

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import com.monghithub.apk_sftp.domain.models.SSHConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties

object SSHConnectionManager {
    private val jsch = JSch()
    private var session: Session? = null

    suspend fun connect(connection: SSHConnection, timeoutMs: Int = 30000): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                disconnect()

                if (connection.keyPath.isNotBlank()) {
                    jsch.addIdentity(connection.keyPath)
                }

                session = jsch.getSession(
                    connection.username,
                    connection.hostname,
                    connection.port
                ).apply {
                    val config = Properties()
                    config["StrictHostKeyChecking"] = "no"

                    if (connection.keyPath.isNotBlank()) {
                        config["PreferredAuthentications"] = "publickey"
                    } else if (!connection.password.isNullOrBlank()) {
                        config["PreferredAuthentications"] = "password"
                        setPassword(connection.password)
                    }

                    setConfig(config)
                    setServerAliveInterval(30000)
                    serverAliveCountMax = 3
                }

                session?.connect(timeoutMs)
                Result.success(Unit)
            } catch (e: Exception) {
                session = null
                Result.failure(e)
            }
        }

    suspend fun disconnect(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            session?.disconnect()
            session = null
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getSession(): Session? = session

    fun isConnected(): Boolean = session?.isConnected == true

    fun getConnectionInfo(): String? = session?.let { s ->
        "${s.userName}@${s.host}:${s.port}"
    }
}
