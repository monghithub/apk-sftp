package com.monghithub.apk_sftp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.monghithub.apk_sftp.domain.models.ConnectionProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.util.UUID

@Serializable
data class ProfileData(
    val id: String,
    val name: String,
    val hostname: String,
    val port: Int,
    val username: String,
    val keyId: String,
    val isDefault: Boolean,
    val createdDate: Long
)

class ProfileManager(context: Context) {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs: SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            "connection_profiles",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    suspend fun createProfile(profile: ConnectionProfile): Result<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val data = ProfileData(
                    id = profile.id,
                    name = profile.name,
                    hostname = profile.hostname,
                    port = profile.port,
                    username = profile.username,
                    keyId = profile.keyId,
                    isDefault = profile.isDefault,
                    createdDate = profile.createdDate
                )
                val json = Json.encodeToString(ProfileData.serializer(), data)
                encryptedPrefs.edit().putString("profile_${profile.id}", json).apply()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    suspend fun getProfiles(): List<ConnectionProfile> = withContext(Dispatchers.IO) {
        encryptedPrefs.all.values.mapNotNull { json ->
            try {
                val data = Json.decodeFromString(ProfileData.serializer(), json as String)
                ConnectionProfile(
                    id = data.id,
                    name = data.name,
                    hostname = data.hostname,
                    port = data.port,
                    username = data.username,
                    keyId = data.keyId,
                    isDefault = data.isDefault,
                    createdDate = data.createdDate
                )
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun deleteProfile(profileId: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            encryptedPrefs.edit().remove("profile_$profileId").apply()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateProfile(profile: ConnectionProfile): Result<Unit> =
        withContext(Dispatchers.IO) {
            deleteProfile(profile.id)
            createProfile(profile)
        }

    suspend fun getDefaultProfile(): ConnectionProfile? = withContext(Dispatchers.IO) {
        getProfiles().firstOrNull { it.isDefault }
    }

    suspend fun exportProfiles(): String = withContext(Dispatchers.IO) {
        val profiles = getProfiles().map { profile ->
            ProfileData(
                id = profile.id,
                name = profile.name,
                hostname = profile.hostname,
                port = profile.port,
                username = profile.username,
                keyId = profile.keyId,
                isDefault = profile.isDefault,
                createdDate = profile.createdDate
            )
        }
        Json.encodeToString(ListSerializer(ProfileData.serializer()), profiles)
    }

    suspend fun importProfiles(json: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            // TODO: Implement import
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
