package com.ranesvision.app.domain.model

data class Device(
    val id: String,
    val name: String,
    val signalStrength: Int = -50,
    val isConnected: Boolean = false
)
