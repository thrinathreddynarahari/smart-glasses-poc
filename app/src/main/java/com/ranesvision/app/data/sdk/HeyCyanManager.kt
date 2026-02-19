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
        android.util.Log.d("HeyCyanManager", "Starting device scan via SDK...")
        try {
            val result = deviceScanner.startScan()
            android.util.Log.d("HeyCyanManager", "Scan completed. Found ${result.getOrNull()?.size ?: 0} devices.")
            result
        } catch (e: Exception) {
            android.util.Log.e("HeyCyanManager", "Scan failed: ${e.message}", e)
            val error = SdkErrorMapper.mapException(e)
            Result.failure(Exception(error.message))
        }
    }

    override suspend fun connect(device: Device): Result<Boolean> = withContext(Dispatchers.IO) {
        android.util.Log.d("HeyCyanManager", "Attempting to connect to device: ${device.name} (${device.id})")
        try {
            val result = deviceConnector.connect(device)
            if (result.isSuccess) {
                android.util.Log.d("HeyCyanManager", "Connection successful to ${device.name}")
                deviceConnector.setConnected(device)
            } else {
                android.util.Log.w("HeyCyanManager", "Connection failed to ${device.name}")
            }
            result
        } catch (e: Exception) {
            android.util.Log.e("HeyCyanManager", "Connection error: ${e.message}", e)
            val error = SdkErrorMapper.mapException(e)
            Result.failure(Exception(error.message))
        }
    }

    override suspend fun disconnect(): Result<Boolean> = withContext(Dispatchers.IO) {
        android.util.Log.d("HeyCyanManager", "Disconnecting from current device...")
        try {
            val result = deviceConnector.disconnect()
            if (result.isSuccess) {
                android.util.Log.d("HeyCyanManager", "Disconnection successful")
                deviceConnector.clearConnection()
            } else {
                android.util.Log.w("HeyCyanManager", "Disconnection failed (or no device connected)")
            }
            result
        } catch (e: Exception) {
            android.util.Log.e("HeyCyanManager", "Disconnection error: ${e.message}", e)
            val error = SdkErrorMapper.mapException(e)
            Result.failure(Exception(error.message))
        }
    }

    override fun isConnected(): Boolean = deviceConnector.isConnected()

    override fun getConnectedDevice(): Device? = deviceConnector.getConnectedDevice()
}
