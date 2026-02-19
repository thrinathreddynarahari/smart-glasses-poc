package com.ranesvision.app.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ranesvision.app.R
import com.ranesvision.app.ui.theme.NeonBlue
import com.ranesvision.app.ui.theme.NeonBlueGlow
import com.ranesvision.app.ui.theme.SpaceBlack
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashComplete: () -> Unit
) {
    val scale = remember { Animatable(0.85f) }
    val alpha = remember { Animatable(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "splashGlow")

    val glowPulse by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowPulse"
    )

    val streakX by infiniteTransition.animateFloat(
        initialValue = -0.3f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "streak"
    )

    LaunchedEffect(Unit) {
        // Fade in
        alpha.animateTo(1f, animationSpec = tween(800))
        // Slow zoom-in
        scale.animateTo(1.05f, animationSpec = tween(2000))
        // Wait remaining time
        delay(200)
        // Navigate
        onSplashComplete()
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
                        SpaceBlack
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Star field background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val rng = java.util.Random(123)
            for (i in 0 until 80) {
                val x = rng.nextFloat() * w
                val y = rng.nextFloat() * h
                val starSize = 0.5f + rng.nextFloat() * 1.8f
                val starAlpha = 0.2f + rng.nextFloat() * 0.5f
                drawCircle(
                    color = Color.White.copy(alpha = starAlpha),
                    radius = starSize,
                    center = Offset(x, y)
                )
            }
        }

        // Cosmic glow arc
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val centerX = w * 0.5f
            val glowY = h * 0.48f

            // Radial glow
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        NeonBlue.copy(alpha = glowPulse * 0.3f),
                        NeonBlueGlow.copy(alpha = glowPulse * 0.1f),
                        Color.Transparent
                    ),
                    center = Offset(centerX, glowY),
                    radius = w * 0.5f
                ),
                center = Offset(centerX, glowY),
                radius = w * 0.5f
            )

            // Bright arc
            val arcRadius = w * 1.0f
            drawArc(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = glowPulse * 0.7f),
                        NeonBlueGlow.copy(alpha = glowPulse * 0.5f),
                        Color.Transparent
                    )
                ),
                startAngle = 195f,
                sweepAngle = 150f,
                useCenter = false,
                topLeft = Offset(centerX - arcRadius, glowY - arcRadius * 0.1f),
                size = androidx.compose.ui.geometry.Size(arcRadius * 2f, arcRadius * 2f),
                style = Stroke(width = 2.5f, cap = StrokeCap.Round)
            )
        }

        // Moving light streak
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val streakStartX = w * streakX
            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.White.copy(alpha = 0.6f),
                        NeonBlueGlow.copy(alpha = 0.3f),
                        Color.Transparent
                    ),
                    startX = streakStartX - 100f,
                    endX = streakStartX + 100f
                ),
                start = Offset(streakStartX - 100f, h * 0.48f),
                end = Offset(streakStartX + 100f, h * 0.48f),
                strokeWidth = 3f,
                cap = StrokeCap.Round
            )
        }

        // Logo
        Image(
            painter = painterResource(id = R.drawable.ranes_logo),
            contentDescription = "Ranes Vision",
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .scale(scale.value)
                .alpha(alpha.value)
                .offset(y = (-40).dp)
                .clip(RoundedCornerShape(5.dp)),
            contentScale = ContentScale.FillWidth
        )
    }
}
