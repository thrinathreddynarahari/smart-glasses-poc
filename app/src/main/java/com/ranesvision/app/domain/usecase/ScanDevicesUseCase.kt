package com.ranesvision.app.domain.usecase

import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.service.SmartGlassService
import javax.inject.Inject

class ScanDevicesUseCase @Inject constructor(
    private val service: SmartGlassService
) {
    suspend operator fun invoke(): Result<List<Device>> {
        return service.scanDevices()
    }
}
