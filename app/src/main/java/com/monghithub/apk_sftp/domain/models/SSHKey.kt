package com.monghithub.apk_sftp.domain.models

data class SSHKey(
    val id: String,
    val name: String,
    val type: String, // RSA, ED25519, etc
    val fingerprint: String,
    val algorithm: String,
    val keySize: Int,
    val importDate: Long
)
