package com.monghithub.apk_sftp.data.remote

import com.jcraft.jsch.Session
import com.jcraft.jsch.ChannelSftp
import com.monghithub.apk_sftp.domain.models.FileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class SFTPFileManager(private val sshConnectionManager: SSHConnectionManager) {

    private fun getSftpChannel(): ChannelSftp? {
        val session = sshConnectionManager.getSession() ?: return null
        val channel = session.openChannel("sftp") as ChannelSftp
        channel.connect()
        return channel
    }

    suspend fun getRemoteFiles(remotePath: String): Result<List<FileInfo>> =
        withContext(Dispatchers.IO) {
            try {
                val channel = getSftpChannel() ?: return@withContext Result.failure(
                    Exception("Not connected")
                )

                @Suppress("UNCHECKED_CAST")
                val entries = channel.ls(remotePath) as? java.util.Vector<ChannelSftp.LsEntry>
                val files = entries
                    ?.filterNot { it.filename == "." || it.filename == ".." }
                    ?.map { entry ->
                        FileInfo(
                            name = entry.filename,
                            path = "$remotePath/${entry.filename}",
                            isDirectory = entry.attrs.isDir,
                            size = entry.attrs.size,
                            modifiedDate = entry.attrs.mTime.toLong() * 1000,
                            permissions = String.format("%o", entry.attrs.permissions)
                        )
                    }
                    ?.sortedWith(compareBy({ !it.isDirectory }, { it.name }))
                    ?: emptyList()

                channel.disconnect()
                Result.success(files)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun deleteRemoteFile(remotePath: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val channel = getSftpChannel() ?: return@withContext Result.failure(
                    Exception("Not connected")
                )

                channel.rm(remotePath)
                channel.disconnect()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun renameRemoteFile(remotePath: String, newName: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val channel = getSftpChannel() ?: return@withContext Result.failure(
                    Exception("Not connected")
                )

                val remoteDir = remotePath.substringBeforeLast("/")
                val newPath = "$remoteDir/$newName"
                channel.rename(remotePath, newPath)
                channel.disconnect()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun uploadFile(
        localPath: String,
        remotePath: String,
        onProgress: (Long, Long) -> Unit = { _, _ -> }
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val channel = getSftpChannel() ?: return@withContext Result.failure(
                Exception("Not connected")
            )

            val file = File(localPath)
            channel.put(localPath, remotePath)
            channel.disconnect()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun downloadFile(
        remotePath: String,
        localPath: String,
        onProgress: (Long, Long) -> Unit = { _, _ -> }
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val channel = getSftpChannel() ?: return@withContext Result.failure(
                Exception("Not connected")
            )

            channel.get(remotePath, localPath)
            channel.disconnect()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
