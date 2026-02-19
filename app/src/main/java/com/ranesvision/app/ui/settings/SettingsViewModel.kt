package com.ranesvision.app.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranesvision.app.data.sdk.GlassesConfig
import com.ranesvision.app.data.sdk.GlassesMode
import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.usecase.DisconnectUseCase
import com.ranesvision.app.domain.usecase.ScanDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val isScanning: Boolean = false,
    val scannedDevices: List<Device> = emptyList(),
    val scanError: String? = null,
    val isDisconnecting: Boolean = false,
    val disconnectMessage: String? = null,
    val errorMessage: String? = null
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val scanDevicesUseCase: ScanDevicesUseCase,
    private val disconnectUseCase: DisconnectUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    /** Observable current mode for the Settings toggle. */
    val currentMode: StateFlow<GlassesMode> = GlassesConfig.modeFlow

    /**
     * Switch between Simulation (MOCK) and Live (REAL) modes.
     * Clears current scan results when switching.
     */
    fun toggleMode(isLive: Boolean) {
        GlassesConfig.mode = if (isLive) GlassesMode.REAL else GlassesMode.MOCK
        _uiState.update {
            it.copy(
                scannedDevices = emptyList(),
                scanError = null
            )
        }
    }

    fun scanDevices() {
        _uiState.update { it.copy(isScanning = true, scanError = null, scannedDevices = emptyList()) }

        viewModelScope.launch {
            val result = scanDevicesUseCase()
            result.fold(
                onSuccess = { devices ->
                    _uiState.update {
                        it.copy(
                            isScanning = false,
                            scannedDevices = devices,
                            scanError = if (devices.isEmpty()) "No devices found nearby" else null
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isScanning = false,
                            scanError = error.message ?: "Scan failed"
                        )
                    }
                }
            )
        }
    }

    fun disconnect() {
        _uiState.update { it.copy(isDisconnecting = true, errorMessage = null) }

        viewModelScope.launch {
            val result = disconnectUseCase()
            result.fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            isDisconnecting = false,
                            disconnectMessage = "Disconnected successfully"
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isDisconnecting = false,
                            errorMessage = error.message ?: "Disconnect failed"
                        )
                    }
                }
            )
        }
    }

    fun dismissMessage() {
        _uiState.update { it.copy(disconnectMessage = null, errorMessage = null) }
    }
}
