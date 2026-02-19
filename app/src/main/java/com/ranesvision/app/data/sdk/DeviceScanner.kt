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
        val foundDevices = java.util.concurrent.ConcurrentHashMap<String, Device>()
        android.util.Log.d("DeviceScanner", "Starting scan with 10s timeout")

        try {
            kotlinx.coroutines.withTimeout(10000L) {
                callbackFlow {
                    val scanCallback = object : ScanWrapperCallback {
                        override fun onStart() {
                            android.util.Log.d("DeviceScanner", "SDK onStart")
                        }

                        override fun onStop() {
                            android.util.Log.d("DeviceScanner", "SDK onStop")
                        }

                        override fun onParsedData(device: BluetoothDevice?, scanRecord: ScanRecord?) {
                            // Some SDK versions use this
                            device?.let { emitDevice(it, -55) }
                        }

                        override fun onLeScan(device: BluetoothDevice?, rssi: Int, scanRecord: ByteArray?) {
                            device?.let { emitDevice(it, rssi) }
                        }

                        override fun onBatchScanResults(results: MutableList<android.bluetooth.le.ScanResult>?) {
                            results?.forEach { 
                                emitDevice(it.device, it.rssi) 
                            }
                        }

                        override fun onScanFailed(errorCode: Int) {
                             android.util.Log.e("DeviceScanner", "SDK onScanFailed: $errorCode")
                             close(Exception("Scan failed with error: $errorCode"))
                        }

                        private fun emitDevice(device: BluetoothDevice, rssi: Int) {
                            val id = device.address ?: return
                            val name = device.name ?: "Unknown Device"
                            // Filter weak signals or invalid names if needed
                            val mappedDevice = Device(
                                id = id,
                                name = name,
                                signalStrength = rssi,
                                isConnected = false
                            )
                            trySend(mappedDevice)
                        }
                    }

                    android.util.Log.d("DeviceScanner", "Invoking BleScannerHelper.scanDevice")
                    BleScannerHelper.getInstance().scanDevice(context, null, scanCallback)

                    awaitClose {
                        android.util.Log.d("DeviceScanner", "Closing scan flow, stopping scanner")
                        BleScannerHelper.getInstance().stopScan(context)
                    }
                }.collect { device ->
                    if (!foundDevices.containsKey(device.id)) {
                        android.util.Log.d("DeviceScanner", "Found device: ${device.name} [${device.id}]")
                        foundDevices[device.id] = device
                    }
                }
            }
             // Should not be reached due to timeout, unless flow completes (it doesn't)
             Result.success(foundDevices.values.toList())
        } catch (e: kotlinx.coroutines.TimeoutCancellationException) {
            android.util.Log.d("DeviceScanner", "Scan timeout reached. Found ${foundDevices.size} devices.")
            Result.success(foundDevices.values.toList())
        } catch (e: Exception) {
            android.util.Log.e("DeviceScanner", "Scan error: ${e.message}", e)
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
