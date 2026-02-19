package com.ranesvision.app.data.sdk

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Operational mode for the glasses SDK layer.
 *
 * MOCK  - Returns hardcoded simulated devices. Safe for emulator / demo.
 * REAL  - Invokes the physical BLE SDK (BleScannerHelper, BleOperateManager).
 */
enum class GlassesMode {
    MOCK,
    REAL
}

/**
 * Global, singleton configuration that determines which SDK
 * implementation is used at runtime.
 *
 * Default is [GlassesMode.MOCK].
 *
 * Switching modes requires only:
 * ```
 * GlassesConfig.mode = GlassesMode.REAL
 * ```
 */
object GlassesConfig {

    private val _modeFlow = MutableStateFlow(GlassesMode.MOCK)

    /** Observable mode state for Compose / ViewModel consumption. */
    val modeFlow: StateFlow<GlassesMode> = _modeFlow.asStateFlow()

    /** Current operational mode. */
    var mode: GlassesMode
        get() = _modeFlow.value
        set(value) {
            _modeFlow.value = value
        }
}
