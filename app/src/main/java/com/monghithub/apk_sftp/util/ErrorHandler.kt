package com.monghithub.apk_sftp.util

sealed class AppError {
    data class NetworkError(val message: String) : AppError()
    data class AuthenticationError(val message: String) : AppError()
    data class FileOperationError(val message: String) : AppError()
    data class PermissionError(val message: String) : AppError()
    data class TimeoutError(val message: String) : AppError()
    data class UnknownError(val exception: Throwable) : AppError()
}

object ErrorHandler {
    fun handleError(exception: Exception): AppError {
        return when {
            exception.message?.contains("timeout", ignoreCase = true) == true ->
                AppError.TimeoutError("Connection timeout")

            exception.message?.contains("auth", ignoreCase = true) == true ||
            exception.message?.contains("permission", ignoreCase = true) == true ->
                AppError.AuthenticationError("Authentication failed")

            exception.message?.contains("network", ignoreCase = true) == true ->
                AppError.NetworkError("Network error")

            exception.message?.contains("file", ignoreCase = true) == true ->
                AppError.FileOperationError("File operation failed")

            else -> AppError.UnknownError(exception)
        }
    }

    fun getErrorMessage(error: AppError): String {
        return when (error) {
            is AppError.NetworkError -> error.message
            is AppError.AuthenticationError -> error.message
            is AppError.FileOperationError -> error.message
            is AppError.PermissionError -> error.message
            is AppError.TimeoutError -> error.message
            is AppError.UnknownError -> error.exception.message ?: "Unknown error"
        }
    }
}
