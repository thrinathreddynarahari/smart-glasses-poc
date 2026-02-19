package com.ranesvision.app.data.sdk

import android.app.Application
import android.bluetooth.BluetoothDevice
import android.content.Context
import com.oudmon.ble.base.scan.BleScannerHelper
import com.oudmon.ble.base.scan.ScanRecord
import com.oudmon.ble.base.scan.ScanWrapperCallback
import com.ranesvision.app.domain.model.Device
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceScanner @Inject constructor(
    @ApplicationContext private val context: Context
) {

    suspend fun startScan(): Result<List<Device>> = withContext(Dispatchers.IO) {
        try {
            val devices = callbackFlow<List<Device>> {
                val scanCallback = object : ScanWrapperCallback {
                    
                    override fun onStart() {
                        // Scan started
                    }

                    override fun onStop() {
                        // Scan stopped
                    }

                    override fun onParsedData(device: BluetoothDevice?, scanRecord: ScanRecord?) {
                        // RSSI is not directly available here in signature, assuming handled by onLeScan or default
                        emitDevice(device, -50) 
                    }

                    override fun onLeScan(device: BluetoothDevice?, rssi: Int, scanRecord: ByteArray?) {
                        emitDevice(device, rssi)
                    }

                    override fun onBatchScanResults(results: MutableList<android.bluetooth.le.ScanResult>?) {
                         results?.forEach { result ->
                             emitDevice(result.device, result.rssi)
                         }
                    }

                    override fun onScanFailed(errorCode: Int) {
                        try {
                            close(Exception("Scan failed with error: $errorCode"))
                        } catch (e: Exception) {
                            // Ignore
                        }
                    }

                    private fun emitDevice(device: BluetoothDevice?, rssi: Int) {
                        device?.let {
                            val mappedDevice = Device(
                                id = it.address ?: UUID.randomUUID().toString(),
                                name = it.name ?: "Unknown Device",
                                signalStrength = rssi,
                                isConnected = false
                            )
                            trySend(listOf(mappedDevice))
                        }
                    }
                }

                BleScannerHelper.getInstance().scanDevice(context, null, scanCallback)

                awaitClose {
                    BleScannerHelper.getInstance().stopScan(context)
                }
            }.first()
            
            Result.success(devices)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun stopScan() {
        try {
            BleScannerHelper.getInstance().stopScan(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
