package com.ranesvision.app.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ranesvision.app.ui.theme.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Pastel gradient "Connecting devices" button matching the reference design.
 * Gradient flows from soft lavender/pink on left to mint/cyan on right.
 */
@Composable
fun GradientPillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val gradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFE8D5F5), // Soft lavender
            Color(0xFFD5E8F5), // Light blue
            Color(0xFFC5F0E8), // Mint
            Color(0xFFD0F5E0)  // Light green
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(gradientBrush)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color(0xFF2A2A3A),
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        )
    }
}

/**
 * Glassmorphism card with subtle border and translucent background.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .then(
                if (onClick != null) Modifier.clickable(onClick = onClick)
                else Modifier
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.15f),
                        Color.White.copy(alpha = 0.05f)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardSurface.copy(alpha = 0.7f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        content()
    }
}

/**
 * Animated radar/scanning UI with rotating sweep and pulsing circles.
 */
@Composable
fun RadarAnimation(
    modifier: Modifier = Modifier,
    size: Dp = 200.dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "radar")

    val sweepAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweep"
    )

    val pulse1 by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse1"
    )

    val pulse2 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, delayMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "pulse2"
    )

    Canvas(modifier = modifier.size(size)) {
        val center = Offset(this.size.width / 2f, this.size.height / 2f)
        val maxRadius = this.size.minDimension / 2f

        // Concentric circles
        for (i in 1..3) {
            val radius = maxRadius * (i / 3f)
            drawCircle(
                color = NeonBlue.copy(alpha = 0.15f),
                radius = radius,
                center = center,
                style = Stroke(width = 1.dp.toPx())
            )
        }

        // Pulsing rings
        drawCircle(
            color = NeonBlue.copy(alpha = 0.3f * (1f - pulse1)),
            radius = maxRadius * pulse1,
            center = center,
            style = Stroke(width = 2.dp.toPx())
        )
        drawCircle(
            color = NeonBlueBright.copy(alpha = 0.2f * (1f - pulse2)),
            radius = maxRadius * pulse2,
            center = center,
            style = Stroke(width = 2.dp.toPx())
        )

        // Rotating sweep line
        rotate(sweepAngle) {
            drawLine(
                brush = Brush.linearGradient(
                    colors = listOf(
                        NeonBlue.copy(alpha = 0.8f),
                        NeonBlue.copy(alpha = 0f)
                    ),
                    start = center,
                    end = Offset(center.x, center.y - maxRadius)
                ),
                start = center,
                end = Offset(center.x, center.y - maxRadius),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        }

        // Center dot
        drawCircle(
            color = NeonBlue,
            radius = 4.dp.toPx(),
            center = center
        )
    }
}

/**
 * Animated flowing wave lines matching the dark background pattern
 * in the reference design.
 */
@Composable
fun FlowingWaveBackground(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "waves")

    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 2f * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wavePhase"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = this.size.width
        val height = this.size.height

        // Draw multiple wave layers
        for (layer in 0..12) {
            val yBase = height * 0.35f + (layer * height * 0.05f)
            val amplitude = 15f + layer * 3f
            val frequency = 0.003f + layer * 0.0005f
            val alpha = 0.05f + (layer * 0.015f)

            val path = androidx.compose.ui.graphics.Path()
            path.moveTo(0f, yBase)

            for (x in 0..width.toInt() step 3) {
                val xFloat = x.toFloat()
                val y = yBase + amplitude * sin(xFloat * frequency + phase + layer * 0.5f)
                path.lineTo(xFloat, y)
            }

            drawPath(
                path = path,
                color = Color.White.copy(alpha = alpha.coerceAtMost(0.2f)),
                style = Stroke(width = 1f)
            )
        }
    }
}

/**
 * Signal strength indicator with color coding.
 */
@Composable
fun SignalStrengthIndicator(
    signalStrength: Int,
    modifier: Modifier = Modifier
) {
    val bars = when {
        signalStrength > -50 -> 4
        signalStrength > -60 -> 3
        signalStrength > -70 -> 2
        else -> 1
    }
    val color = when {
        signalStrength > -50 -> SignalStrong
        signalStrength > -65 -> SignalMedium
        else -> SignalWeak
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        for (i in 1..4) {
            val barHeight = (8 + i * 4).dp
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(barHeight)
                    .clip(RoundedCornerShape(2.dp))
                    .background(
                        if (i <= bars) color
                        else TextMuted.copy(alpha = 0.3f)
                    )
            )
        }
    }
}

/**
 * Device card for scan results, with glassmorphism style.
 */
@Composable
fun DeviceCard(
    deviceName: String,
    signalStrength: Int,
    isConnecting: Boolean = false,
    onConnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    GlassCard(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Device icon
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
                // Glasses/device icon drawn with canvas
                Canvas(modifier = Modifier.size(24.dp)) {
                    val w = this.size.width
                    val h = this.size.height
                    drawCircle(
                        color = NeonBlue,
                        radius = w * 0.2f,
                        center = Offset(w * 0.3f, h * 0.5f),
                        style = Stroke(width = 2f)
                    )
                    drawCircle(
                        color = NeonBlue,
                        radius = w * 0.2f,
                        center = Offset(w * 0.7f, h * 0.5f),
                        style = Stroke(width = 2f)
                    )
                    drawLine(
                        color = NeonBlue,
                        start = Offset(w * 0.5f, h * 0.4f),
                        end = Offset(w * 0.5f, h * 0.6f),
                        strokeWidth = 2f
                    )
                }
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = deviceName,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SignalStrengthIndicator(signalStrength = signalStrength)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${signalStrength} dBm",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextMuted
                    )
                }
            }

            // Connect button
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(NeonBlueDim, NeonBlue)
                        )
                    )
                    .clickable(enabled = !isConnecting, onClick = onConnect)
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isConnecting) "..." else "Connect",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                )
            }
        }
    }
}

/**
 * The cosmic glow background element - a bright white/blue arc
 * matching the planetary horizon effect in the reference.
 */
@Composable
fun CosmicGlowArc(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "glow")

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowPulse"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = this.size.width
        val h = this.size.height

        // Large arc - planetary horizon glow
        val arcY = h * 0.30f
        val arcRadius = w * 1.2f

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    Color.White.copy(alpha = glowAlpha * 0.4f),
                    NeonBlue.copy(alpha = glowAlpha * 0.2f),
                    Color.Transparent
                ),
                center = Offset(w * 0.5f, arcY + arcRadius * 0.85f),
                radius = arcRadius * 0.3f
            ),
            center = Offset(w * 0.5f, arcY + arcRadius * 0.85f),
            radius = arcRadius * 0.3f
        )

        // Bright thin arc line
        drawArc(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.White.copy(alpha = glowAlpha * 0.8f),
                    NeonBlueGlow.copy(alpha = glowAlpha * 0.6f),
                    Color.Transparent
                )
            ),
            startAngle = 190f,
            sweepAngle = 160f,
            useCenter = false,
            topLeft = Offset(w * 0.5f - arcRadius, arcY),
            size = androidx.compose.ui.geometry.Size(arcRadius * 2f, arcRadius * 2f),
            style = Stroke(width = 2f)
        )
    }
}

/**
 * Star field background with small scattered dots.
 */
@Composable
fun StarField(
    modifier: Modifier = Modifier,
    starCount: Int = 60
) {
    val infiniteTransition = rememberInfiniteTransition(label = "stars")

    val twinkle by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "twinkle"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = this.size.width
        val h = this.size.height

        // Pseudorandom star positions using a seed
        val rng = java.util.Random(42)
        for (i in 0 until starCount) {
            val x = rng.nextFloat() * w
            val y = rng.nextFloat() * h * 0.5f // stars in upper half
            val starSize = 0.5f + rng.nextFloat() * 1.5f
            val alpha = if (i % 5 == 0) twinkle * 0.6f else 0.3f + rng.nextFloat() * 0.3f

            drawCircle(
                color = Color.White.copy(alpha = alpha),
                radius = starSize,
                center = Offset(x, y)
            )
        }
    }
}
