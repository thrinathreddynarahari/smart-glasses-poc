package com.ranesvision.app.domain.model

sealed class SmartGlassError(val message: String) {
    class ConnectionFailed(message: String = "Unable to connect to device") : SmartGlassError(message)
    class DeviceNotFound(message: String = "Device not found") : SmartGlassError(message)
    class BluetoothDisabled(message: String = "Bluetooth is disabled") : SmartGlassError(message)
    class ScanFailed(message: String = "Device scan failed") : SmartGlassError(message)
    class Timeout(message: String = "Operation timed out") : SmartGlassError(message)
    class Unknown(message: String = "An unexpected error occurred") : SmartGlassError(message)
}
