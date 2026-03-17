package com.monghithub.apk_sftp.domain.models

data class ConnectionProfile(
    val id: String,
    val name: String,
    val hostname: String,
    val port: Int = 22,
    val username: String,
    val keyId: String,
    val isDefault: Boolean = false,
    val createdDate: Long = System.currentTimeMillis()
)
