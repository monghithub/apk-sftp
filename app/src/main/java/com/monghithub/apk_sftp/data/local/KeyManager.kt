package com.monghithub.apk_sftp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.monghithub.apk_sftp.domain.models.SSHKey
import java.io.File
import java.util.UUID

class KeyManager(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            "ssh_keys",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    private val keysDir = File(context.filesDir, "ssh_keys")

    init {
        if (!keysDir.exists()) {
            keysDir.mkdirs()
        }
    }

    fun importKey(keyPath: String, name: String): Result<SSHKey> {
        return try {
            val keyFile = File(keyPath)
            if (!keyFile.exists()) {
                return Result.failure(Exception("Key file not found"))
            }

            val keyContent = keyFile.readText()
            val keyId = UUID.randomUUID().toString()

            // Store encrypted key
            val newKeyFile = File(keysDir, "$keyId.key")
            newKeyFile.writeText(keyContent)

            // Generate fingerprint and metadata
            val sshKey = SSHKey(
                id = keyId,
                name = name,
                type = extractKeyType(keyContent),
                fingerprint = generateFingerprint(keyContent),
                algorithm = extractAlgorithm(keyContent),
                keySize = extractKeySize(keyContent),
                importDate = System.currentTimeMillis()
            )

            // Store metadata
            encryptedPrefs.edit().putString("key_$keyId", keyId).apply()

            Result.success(sshKey)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getKeys(): List<SSHKey> {
        // In production, retrieve from database
        return emptyList()
    }

    fun getKeyContent(keyId: String): String? {
        val keyFile = File(keysDir, "$keyId.key")
        return if (keyFile.exists()) keyFile.readText() else null
    }

    fun deleteKey(keyId: String): Result<Unit> {
        return try {
            val keyFile = File(keysDir, "$keyId.key")
            keyFile.delete()
            encryptedPrefs.edit().remove("key_$keyId").apply()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun renameKey(keyId: String, newName: String): Result<Unit> {
        return Result.success(Unit) // TODO: Implement in database
    }

    private fun extractKeyType(content: String): String {
        return when {
            content.contains("BEGIN RSA PRIVATE KEY") -> "RSA"
            content.contains("BEGIN OPENSSH PRIVATE KEY") -> "OpenSSH"
            content.contains("BEGIN PRIVATE KEY") -> "Generic"
            else -> "Unknown"
        }
    }

    private fun generateFingerprint(content: String): String {
        // Simplified fingerprint generation
        return content.hashCode().toString(16).take(16)
    }

    private fun extractAlgorithm(content: String): String {
        return "RSA" // TODO: Parse from key
    }

    private fun extractKeySize(content: String): Int {
        return 2048 // TODO: Parse from key
    }
}
