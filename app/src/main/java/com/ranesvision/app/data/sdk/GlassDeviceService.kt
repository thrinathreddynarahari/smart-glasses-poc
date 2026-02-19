package com.ranesvision.app.data.sdk

import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.service.SmartGlassService
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

/**
 * Delegating implementation of [SmartGlassService] that routes every call
 * to either the mock or the real SDK implementation based on the current
 * value of [GlassesConfig.mode].
 *
 * This is the single binding provided to the DI graph; consumers never
 * need to know which underlying service is active.
 */
@Singleton
class GlassDeviceService @Inject constructor(
    @Named("mock") private val mockService: SmartGlassService,
    @Named("real") private val realService: SmartGlassService
) : SmartGlassService {

    private val active: SmartGlassService
        get() = when (GlassesConfig.mode) {
            GlassesMode.MOCK -> mockService
            GlassesMode.REAL -> realService
        }

    override suspend fun scanDevices(): Result<List<Device>> {
        return active.scanDevices()
    }

    override suspend fun connect(device: Device): Result<Boolean> {
        return active.connect(device)
    }

    override suspend fun disconnect(): Result<Boolean> {
        return active.disconnect()
    }

    override fun isConnected(): Boolean {
        return active.isConnected()
    }

    override fun getConnectedDevice(): Device? {
        return active.getConnectedDevice()
    }
}
