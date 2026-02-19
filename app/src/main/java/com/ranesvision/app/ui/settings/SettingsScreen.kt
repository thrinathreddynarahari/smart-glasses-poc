package com.ranesvision.app.ui.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothDisabled
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ranesvision.app.data.sdk.GlassesMode
import com.ranesvision.app.ui.components.DeviceCard
import com.ranesvision.app.ui.components.GlassCard
import com.ranesvision.app.ui.theme.*

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentMode by viewModel.currentMode.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage, uiState.disconnectMessage) {
        uiState.errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissMessage()
        }
        uiState.disconnectMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissMessage()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(SpaceBlack, DeepNavy, SpaceBlack)
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.headlineLarge,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            // Scan Devices
//            item {
//                SettingsActionCard(
//                    icon = Icons.Default.Search,
//                    title = "Scan Devices",
//                    subtitle = "Search for nearby smart glasses",
//                    isLoading = uiState.isScanning,
//                    onClick = { viewModel.scanDevices() }
//                )
//            }

            // Scan error
            if (uiState.scanError != null) {
                item {
                    GlassCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(ErrorRed.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = null,
                                    tint = ErrorRed,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = uiState.scanError!!,
                                style = MaterialTheme.typography.bodyMedium,
                                color = ErrorRed
                            )
                        }
                    }
                }
            }

            // Scanned devices list
            if (uiState.scannedDevices.isNotEmpty()) {
                itemsIndexed(uiState.scannedDevices) { index, device ->
                    AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(tween(300 + index * 100)) +
                                slideInVertically(tween(300 + index * 100)) { it / 2 }
                    ) {
                        DeviceCard(
                            deviceName = device.name,
                            signalStrength = device.signalStrength,
                            onConnect = { /* Connect from settings */ }
                        )
                    }
                }
            }

            // Disconnect
//            item {
//                SettingsActionCard(
//                    icon = Icons.Default.BluetoothDisabled,
//                    title = "Disconnect Device",
//                    subtitle = "Disconnect from current smart glasses",
//                    isLoading = uiState.isDisconnecting,
//                    onClick = { viewModel.disconnect() }
//                )
//            }

            // Divider
//            item {
//                @Suppress("DEPRECATION")
//                Divider(
//                    color = GlassBorder,
//                    thickness = 1.dp
//                )
//            }

            // Mode Toggle (Developer Option)
//            item {
//                GlassCard(modifier = Modifier.fillMaxWidth()) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(20.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .size(44.dp)
//                                .clip(CircleShape)
//                                .background(
//                                    Brush.linearGradient(
//                                        colors = listOf(
//                                            NeonBlueDark,
//                                            NeonBlue.copy(alpha = 0.3f)
//                                        )
//                                    )
//                                ),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Icon(
//                                Icons.Default.Code,
//                                contentDescription = null,
//                                tint = NeonBlue,
//                                modifier = Modifier.size(22.dp)
//                            )
//                        }
//
//                        Spacer(modifier = Modifier.width(14.dp))
//
//                        Column(modifier = Modifier.weight(1f)) {
//                            Text(
//                                text = "SDK Mode",
//                                style = MaterialTheme.typography.titleMedium,
//                                color = TextPrimary
//                            )
//                            Text(
//                                text = if (currentMode == GlassesMode.REAL) "Live (Real SDK)" else "Simulation (Mock)",
//                                style = MaterialTheme.typography.bodySmall,
//                                color = TextMuted
//                            )
//                        }
//
//                        Switch(
//                            checked = currentMode == GlassesMode.REAL,
//                            onCheckedChange = { viewModel.toggleMode(it) },
//                            colors = SwitchDefaults.colors(
//                                checkedThumbColor = NeonBlue,
//                                checkedTrackColor = NeonBlueDark.copy(alpha = 0.5f),
//                                uncheckedThumbColor = TextMuted,
//                                uncheckedTrackColor = CardSurface,
//                                uncheckedBorderColor = GlassBorder
//                            )
//                        )
//                    }
//                }
//            }

            // Divider
//            item {
//                @Suppress("DEPRECATION")
//                Divider(
//                    color = GlassBorder,
//                    thickness = 1.dp
//                )
//            }

            // App Info
            item {
                GlassCard(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        Brush.linearGradient(
                                            colors = listOf(NeonBlueDark, NeonBlue.copy(alpha = 0.3f))
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Info,
                                    contentDescription = null,
                                    tint = NeonBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "App Info",
                                style = MaterialTheme.typography.titleMedium,
                                color = TextPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        InfoRow(label = "App Version", value = "1.0.0")
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoRow(label = "SDK Version", value = "v01 (2025-07-23)")
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoRow(
                            label = "Active Mode",
                            value = if (currentMode == GlassesMode.REAL) "Live" else "Simulation"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        InfoRow(label = "Build", value = "Production")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = NeonBlueDark,
                    contentColor = Color.White
                )
            }
        )
    }
}

@Composable
private fun SettingsActionCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(NeonBlueDark, NeonBlue.copy(alpha = 0.3f))
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = NeonBlue,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextMuted
                )
            }

                if (isLoading) {
                // Simplified usage to avoid NoSuchMethodError
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = NeonBlue
                )
            }
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary
        )
    }
}
