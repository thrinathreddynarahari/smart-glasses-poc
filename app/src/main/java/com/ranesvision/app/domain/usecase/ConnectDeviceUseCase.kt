package com.ranesvision.app.domain.usecase

import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.service.SmartGlassService
import javax.inject.Inject

class ConnectDeviceUseCase @Inject constructor(
    private val service: SmartGlassService
) {
    suspend operator fun invoke(device: Device): Result<Boolean> {
        return service.connect(device)
    }
}
