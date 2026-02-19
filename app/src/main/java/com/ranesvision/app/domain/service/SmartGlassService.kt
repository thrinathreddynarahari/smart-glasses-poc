package com.ranesvision.app.domain.service

import com.ranesvision.app.domain.model.Device

interface SmartGlassService {
    suspend fun scanDevices(): Result<List<Device>>
    suspend fun connect(device: Device): Result<Boolean>
    suspend fun disconnect(): Result<Boolean>
    fun isConnected(): Boolean
    fun getConnectedDevice(): Device?
}
