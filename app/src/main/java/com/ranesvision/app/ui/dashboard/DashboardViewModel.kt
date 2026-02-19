package com.ranesvision.app.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ranesvision.app.data.sdk.GlassesConfig
import com.ranesvision.app.data.sdk.GlassesMode
import com.ranesvision.app.domain.model.Device
import com.ranesvision.app.domain.usecase.ConnectDeviceUseCase
import com.ranesvision.app.domain.usecase.ScanDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val isScanning: Boolean = false,
    val showDevices: Boolean = false,
    val devices: List<Device> = emptyList(),
    val connectingDeviceId: String? = null,
    val connectedDevice: Device? = null,
    val errorMessage: String? = null,
    val navigateToAlbum: Boolean = false,
    val noDevicesFound: Boolean = false
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val scanDevicesUseCase: ScanDevicesUseCase,
    private val connectDeviceUseCase: ConnectDeviceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    /** Observable current mode for the UI toggle. */
    val currentMode: StateFlow<GlassesMode> = GlassesConfig.modeFlow

    /**
     * Switch between Simulation (MOCK) and Live (REAL) modes.
     * Clears current scan results when switching.
     */
    fun toggleMode(isLive: Boolean) {
        GlassesConfig.mode = if (isLive) GlassesMode.REAL else GlassesMode.MOCK
        _uiState.update {
            it.copy(
                showDevices = false,
                devices = emptyList(),
                errorMessage = null,
                noDevicesFound = false
            )
        }
    }

    fun startScanning() {
        _uiState.update {
            it.copy(
                isScanning = true,
                showDevices = false,
                errorMessage = null,
                noDevicesFound = false
            )
        }

        viewModelScope.launch {
            val result = scanDevicesUseCase()
            result.fold(
                onSuccess = { devices ->
                    _uiState.update {
                        it.copy(
                            isScanning = false,
                            showDevices = devices.isNotEmpty(),
                            devices = devices,
                            noDevicesFound = devices.isEmpty()
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isScanning = false,
                            showDevices = false,
                            errorMessage = error.message ?: "Scan failed"
                        )
                    }
                }
            )
        }
    }

    fun connectToDevice(device: Device) {
        _uiState.update { it.copy(connectingDeviceId = device.id, errorMessage = null) }

        viewModelScope.launch {
            val result = connectDeviceUseCase(device)
            result.fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            connectingDeviceId = null,
                            connectedDevice = device.copy(isConnected = true),
                            navigateToAlbum = true
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            connectingDeviceId = null,
                            errorMessage = error.message ?: "Connection failed"
                        )
                    }
                }
            )
        }
    }

    fun onNavigatedToAlbum() {
        _uiState.update { it.copy(navigateToAlbum = false) }
    }

    fun dismissError() {
        _uiState.update { it.copy(errorMessage = null) }
    }
}
