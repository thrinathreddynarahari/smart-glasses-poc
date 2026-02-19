package com.ranesvision.app.data.sdk

import android.content.Context
import com.oudmon.ble.base.bluetooth.BleOperateManager
import com.oudmon.ble.base.communication.ICommandResponse
import com.oudmon.ble.base.communication.rsp.BaseRspCmd
import com.ranesvision.app.domain.model.Device
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceConnector @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var connectedDevice: Device? = null

    init {
        try {
            BleOperateManager.getInstance().addOutDeviceListener(0, object : ICommandResponse<BaseRspCmd> {
                override fun onDataResponse(rsp: BaseRspCmd?) {
                    // Handle response
                    // Example: check if connection success or fail if rsp contains status
                    // For now keeping it empty or logging
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun connect(device: Device): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            BleOperateManager.getInstance().connectDirectly(device.id)
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun disconnect(): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            BleOperateManager.getInstance().unBindDevice()
            connectedDevice = null
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isConnected(): Boolean {
        return try {
            BleOperateManager.getInstance().isConnected()
        } catch (e: Exception) {
             false
        }
    }

    fun getConnectedDevice(): Device? = connectedDevice

    fun setConnected(device: Device) {
        connectedDevice = device.copy(isConnected = true)
    }

    fun clearConnection() {
        connectedDevice = null
    }
}
