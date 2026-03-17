package com.monghithub.apk_sftp.domain.models

data class SSHConnection(
    val hostname: String,
    val port: Int = 22,
    val username: String,
    val keyPath: String,
    val passphrase: String? = null
)
