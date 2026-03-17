package com.monghithub.apk_sftp.domain.models

data class FileInfo(
    val name: String,
    val path: String,
    val isDirectory: Boolean,
    val size: Long,
    val modifiedDate: Long,
    val permissions: String? = null // For remote files
)
