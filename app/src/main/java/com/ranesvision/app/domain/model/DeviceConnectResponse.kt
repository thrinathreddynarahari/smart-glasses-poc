package com.ranesvision.app.domain.model

/**
 * Structured response returned after a connect attempt.
 *
 * Always includes:
 *  - connection status
 *  - the connected device (if successful)
 *  - full device list (mock or real, depending on configuration)
 *  - error message (if any)
 */
data class DeviceConnectResponse(
    val isConnected: Boolean,
    val connectedDevice: Device?,
    val availableDevices: List<Device>,
    val errorMessage: String?
)
