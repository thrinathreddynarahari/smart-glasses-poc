package com.ranesvision.app.data.sdk

import android.content.Context
import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.model.SmartGlassError
import com.ranesvision.app.domain.service.SmartGlassService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * HeyCyanManager wraps the real AAR SDK functions.
 * When the physical SDK API surface is known, replace the stub
 * implementations below with actual SDK calls.
 *
 * For demo/testing without hardware, use [MockSmartGlassService] instead.
 */
@Singleton
class HeyCyanManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val deviceScanner: DeviceScanner,
    private val deviceConnector: DeviceConnector
) : SmartGlassService {

    override suspend fun scanDevices(): Result<List<Device>> = withContext(Dispatchers.IO) {
        try {
            val result = deviceScanner.startScan()
            result
        } catch (e: Exception) {
            val error = SdkErrorMapper.mapException(e)
            Result.failure(Exception(error.message))
        }
    }

    override suspend fun connect(device: Device): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val result = deviceConnector.connect(device)
            if (result.isSuccess) {
                deviceConnector.setConnected(device)
            }
            result
        } catch (e: Exception) {
            val error = SdkErrorMapper.mapException(e)
            Result.failure(Exception(error.message))
        }
    }

    override suspend fun disconnect(): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val result = deviceConnector.disconnect()
            if (result.isSuccess) {
                deviceConnector.clearConnection()
            }
            result
        } catch (e: Exception) {
            val error = SdkErrorMapper.mapException(e)
            Result.failure(Exception(error.message))
        }
    }

    override fun isConnected(): Boolean = deviceConnector.isConnected()

    override fun getConnectedDevice(): Device? = deviceConnector.getConnectedDevice()
}
