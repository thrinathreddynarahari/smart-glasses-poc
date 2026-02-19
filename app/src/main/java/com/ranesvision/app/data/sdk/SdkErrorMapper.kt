package com.ranesvision.app.data.sdk

import com.ranesvision.app.domain.model.SmartGlassError

object SdkErrorMapper {

    fun mapException(exception: Throwable): SmartGlassError {
        return when {
            exception.message?.contains("bluetooth", ignoreCase = true) == true ->
                SmartGlassError.BluetoothDisabled(
                    exception.message ?: "Bluetooth is disabled"
                )
            exception.message?.contains("timeout", ignoreCase = true) == true ->
                SmartGlassError.Timeout(
                    exception.message ?: "Operation timed out"
                )
            exception.message?.contains("not found", ignoreCase = true) == true ->
                SmartGlassError.DeviceNotFound(
                    exception.message ?: "Device not found"
                )
            exception.message?.contains("scan", ignoreCase = true) == true ->
                SmartGlassError.ScanFailed(
                    exception.message ?: "Device scan failed"
                )
            exception.message?.contains("connect", ignoreCase = true) == true ->
                SmartGlassError.ConnectionFailed(
                    exception.message ?: "Connection failed"
                )
            else ->
                SmartGlassError.Unknown(
                    exception.message ?: "An unexpected error occurred"
                )
        }
    }

    fun mapErrorCode(code: Int, message: String = ""): SmartGlassError {
        return when (code) {
            1 -> SmartGlassError.BluetoothDisabled(message.ifEmpty { "Bluetooth is disabled" })
            2 -> SmartGlassError.ScanFailed(message.ifEmpty { "Device scan failed" })
            3 -> SmartGlassError.DeviceNotFound(message.ifEmpty { "Device not found" })
            4 -> SmartGlassError.ConnectionFailed(message.ifEmpty { "Connection failed" })
            5 -> SmartGlassError.Timeout(message.ifEmpty { "Operation timed out" })
            else -> SmartGlassError.Unknown(message.ifEmpty { "Unknown error (code: $code)" })
        }
    }
}
