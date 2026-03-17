package com.monghithub.apk_sftp.util

import android.util.Log

object Logger {
    private const val TAG = "APK-SFTP"

    fun d(tag: String, message: String) {
        Log.d("$TAG:$tag", message)
    }

    fun d(tag: String, message: String, throwable: Throwable) {
        Log.d("$TAG:$tag", message, throwable)
    }

    fun i(tag: String, message: String) {
        Log.i("$TAG:$tag", message)
    }

    fun w(tag: String, message: String) {
        Log.w("$TAG:$tag", message)
    }

    fun e(tag: String, message: String) {
        Log.e("$TAG:$tag", message)
    }

    fun e(tag: String, message: String, throwable: Throwable) {
        Log.e("$TAG:$tag", message, throwable)
    }
}
