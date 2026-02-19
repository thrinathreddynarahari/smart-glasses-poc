package com.ranesvision.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import com.oudmon.ble.base.bluetooth.BleOperateManager

@HiltAndroidApp
class RanesVisionApp : Application() {

    companion object {
        private const val TAG = "RanesVisionApp"
    }

    override fun onCreate() {
        super.onCreate()
        initSdk()
    }

    /**
     * Initialises the BLE SDK subsystem.
     * Wrapped in try-catch so the app still starts on emulators
     * where SDK native libraries may not be available.
     */
    private fun initSdk() {
        try {
            // Ensure BleOperateManager singleton is ready
            BleOperateManager.getInstance()
            Log.i(TAG, "SDK initialised successfully")
        } catch (e: Exception) {
            Log.w(TAG, "SDK init skipped: ${e.message}")
        } catch (e: NoClassDefFoundError) {
            Log.w(TAG, "SDK classes not available (emulator?): ${e.message}")
        }
    }
}
