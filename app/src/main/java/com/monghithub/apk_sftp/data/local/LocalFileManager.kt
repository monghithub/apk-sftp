package com.monghithub.apk_sftp.data.local

import android.content.Context
import android.os.Environment
import com.monghithub.apk_sftp.domain.models.FileInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class LocalFileManager(private val context: Context) {

    suspend fun getFiles(path: String): Result<List<FileInfo>> = withContext(Dispatchers.IO) {
        try {
            val dir = File(path)
            if (!dir.exists() || !dir.isDirectory) {
                return@withContext Result.failure(Exception("Directory not found"))
            }

            val files = dir.listFiles()?.map { file ->
                FileInfo(
                    name = file.name,
                    path = file.absolutePath,
                    isDirectory = file.isDirectory,
                    size = file.length(),
                    modifiedDate = file.lastModified()
                )
            }?.sortedWith(compareBy({ !it.isDirectory }, { it.name }))
                ?: emptyList()

            Result.success(files)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDefaultPath(): String = withContext(Dispatchers.IO) {
        Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS
        ).absolutePath
    }

    suspend fun getParentPath(currentPath: String): String? = withContext(Dispatchers.IO) {
        File(currentPath).parentFile?.absolutePath
    }

    suspend fun deleteFile(path: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val file = File(path)
            if (file.delete()) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to delete file"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun renameFile(path: String, newName: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val file = File(path)
                val newFile = File(file.parent, newName)
                if (file.renameTo(newFile)) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Failed to rename file"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
}
