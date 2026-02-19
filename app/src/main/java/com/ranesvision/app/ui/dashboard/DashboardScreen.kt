package com.ranesvision.app.ui.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ranesvision.app.R
import com.ranesvision.app.data.sdk.GlassesMode
import com.ranesvision.app.ui.components.CosmicGlowArc
import com.ranesvision.app.ui.components.DeviceCard
import com.ranesvision.app.ui.components.FlowingWaveBackground
import com.ranesvision.app.ui.components.GradientPillButton
import com.ranesvision.app.ui.components.RadarAnimation
import com.ranesvision.app.ui.components.StarField
import com.ranesvision.app.ui.theme.CardSurface
import com.ranesvision.app.ui.theme.ErrorRed
import com.ranesvision.app.ui.theme.GlassBorder
import com.ranesvision.app.ui.theme.NeonBlue
import com.ranesvision.app.ui.theme.NeonBlueDark
import com.ranesvision.app.ui.theme.SpaceBlack
import com.ranesvision.app.ui.theme.TextMuted
import com.ranesvision.app.ui.theme.TextPrimary
import com.ranesvision.app.ui.theme.TextSecondary

@Composable
fun DashboardScreen(
    onNavigateToAlbum: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentMode by viewModel.currentMode.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.navigateToAlbum) {
        if (uiState.navigateToAlbum) {
            onNavigateToAlbum()
            viewModel.onNavigatedToAlbum()
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.dismissError()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SpaceBlack,
                        Color(0xFF0A1628),
                        Color(0xFF0D1B2A),
                        Color(0xFF0F1E30),
                        SpaceBlack
                    )
                )
            )
    ) {
        // Animated backgrounds
        StarField()
        CosmicGlowArc()
        FlowingWaveBackground()

        // Main content
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(48.dp))

            // -- Mode Toggle: Simulation / Live --
            ModeToggle(
                currentMode = currentMode,
                onModeChanged = { isLive -> viewModel.toggleMode(isLive) },
                modifier = Modifier.padding(horizontal = 40.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.ranes_logo),
                contentDescription = "Ranes Vision",
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(24.dp)),
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.weight(1f))

            // Scanning state
            AnimatedVisibility(
                visible = uiState.isScanning,
                enter = fadeIn(tween(300)),
                exit = fadeOut(tween(300))
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 24.dp)
                ) {
                    RadarAnimation(size = 180.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Scanning nearby devices...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // No devices found
            AnimatedVisibility(
                visible = uiState.noDevicesFound && !uiState.isScanning,
                enter = fadeIn(tween(400)),
                exit = fadeOut(tween(300))
            ) {
                NoDevicesFoundCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(bottom = 16.dp)
                )
            }

            // Device list
            AnimatedVisibility(
                visible = uiState.showDevices && !uiState.isScanning,
                enter = fadeIn(tween(500)) + slideInVertically(
                    tween(500),
                    initialOffsetY = { it / 3 }
                ),
                exit = fadeOut(tween(300))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(uiState.devices) { _, device ->
                        DeviceCard(
                            deviceName = device.name,
                            signalStrength = device.signalStrength,
                            isConnecting = uiState.connectingDeviceId == device.id,
                            onConnect = { viewModel.connectToDevice(device) }
                        )
                    }
                }
            }

            // "Connecting devices" button
            AnimatedVisibility(
                visible = !uiState.isScanning && !uiState.showDevices,
                enter = fadeIn(tween(300)),
                exit = fadeOut(tween(300))
            ) {
                Spacer(modifier = Modifier.weight(1f))
            }

            GradientPillButton(
                text = if (uiState.isScanning) "Scanning..." else "Connecting devices",
                onClick = { viewModel.startScanning() },
                enabled = !uiState.isScanning,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 100.dp)
            )
        }

        // Snackbar for errors
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter),
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = ErrorRed.copy(alpha = 0.9f),
                    contentColor = Color.White
                )
            }
        )
    }
}

// ---------------------------------------------------------------------------
// Mode Toggle Composable
// ---------------------------------------------------------------------------

@Composable
private fun ModeToggle(
    currentMode: GlassesMode,
    onModeChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isLive = currentMode == GlassesMode.REAL

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(CardSurface.copy(alpha = 0.6f))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.12f),
                        Color.White.copy(alpha = 0.04f)
                    )
                ),
                shape = RoundedCornerShape(22.dp)
            )
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Simulation tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .then(
                        if (!isLive) Modifier.background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    NeonBlueDark.copy(alpha = 0.7f),
                                    NeonBlue.copy(alpha = 0.3f)
                                )
                            )
                        ) else Modifier
                    )
                    .clickable { onModeChanged(false) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Simulation",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = if (!isLive) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    color = if (!isLive) TextPrimary else TextMuted
                )
            }

            // Live tab
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .then(
                        if (isLive) Modifier.background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    NeonBlue.copy(alpha = 0.3f),
                                    NeonBlueDark.copy(alpha = 0.7f)
                                )
                            )
                        ) else Modifier
                    )
                    .clickable { onModeChanged(true) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Live",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = if (isLive) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp
                    ),
                    color = if (isLive) TextPrimary else TextMuted
                )
            }
        }
    }
}

// ---------------------------------------------------------------------------
// No Devices Found Card
// ---------------------------------------------------------------------------

@Composable
private fun NoDevicesFoundCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(CardSurface.copy(alpha = 0.7f))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.10f),
                        Color.White.copy(alpha = 0.03f)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                Icons.Default.SearchOff,
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = TextMuted.copy(alpha = 0.5f)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "No devices available",
                style = MaterialTheme.typography.titleMedium,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Ensure your smart glasses are powered on and nearby, then try scanning again.",
                style = MaterialTheme.typography.bodySmall,
                color = TextMuted,
                textAlign = TextAlign.Center
            )
        }
    }
}
