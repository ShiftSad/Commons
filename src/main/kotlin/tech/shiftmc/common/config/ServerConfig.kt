package tech.shiftmc.common.config

data class ServerConfig(
    // If null, velocity will not be used.
    val velocitySecret: String = "",
    val onlineMode: Boolean = true,
    val ip: String = "0.0.0.0",
    val port: Int = 25565,
    val entityViewDistance: Int = 5,
    val chunkViewDistance: Int = 8,
)
