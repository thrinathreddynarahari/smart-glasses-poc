package com.ranesvision.app.data.sdk

import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.service.SmartGlassService
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Mock implementation of [SmartGlassService] for testing without
 * physical smart glasses hardware. Returns simulated devices
 * with realistic delays.
 */
@Singleton
class MockSmartGlassService @Inject constructor() : SmartGlassService {

    private var connectedDevice: Device? = null

    private val mockDevices = listOf(
        Device(
            id = "SG-X1-001",
            name = "SmartGlass X1",
            signalStrength = -42,
            isConnected = false
        ),
        Device(
            id = "SG-PRO-002",
            name = "SmartGlass Pro",
            signalStrength = -55,
            isConnected = false
        ),
        Device(
            id = "VAR-LITE-003",
            name = "Vision AR Lite",
            signalStrength = -68,
            isConnected = false
        )
    )

    override suspend fun scanDevices(): Result<List<Device>> {
        // Simulate scan delay
        delay(2000)
        return Result.success(mockDevices)
    }

    override suspend fun connect(device: Device): Result<Boolean> {
        // Simulate connection delay
        delay(1500)
        connectedDevice = device.copy(isConnected = true)
        return Result.success(true)
    }

    override suspend fun disconnect(): Result<Boolean> {
        delay(500)
        connectedDevice = null
        return Result.success(true)
    }

    override fun isConnected(): Boolean = connectedDevice != null

    override fun getConnectedDevice(): Device? = connectedDevice
}
