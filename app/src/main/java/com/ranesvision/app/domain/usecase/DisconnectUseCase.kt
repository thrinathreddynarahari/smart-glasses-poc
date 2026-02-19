package com.ranesvision.app.domain.usecase

import com.ranesvision.app.domain.service.SmartGlassService
import javax.inject.Inject

class DisconnectUseCase @Inject constructor(
    private val service: SmartGlassService
) {
    suspend operator fun invoke(): Result<Boolean> {
        return service.disconnect()
    }
}
