package com.monghithub.apk_sftp.util

import android.content.Context
import androidx.security.crypto.MasterKey

object SecurityUtils {
    private const val SESSION_TIMEOUT_MS = 15 * 60 * 1000L // 15 minutes

    fun createMasterKey(context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    fun isSessionExpired(lastActivityTime: Long): Boolean {
        return System.currentTimeMillis() - lastActivityTime > SESSION_TIMEOUT_MS
    }

    fun updateActivityTime(): Long {
        return System.currentTimeMillis()
    }
}
