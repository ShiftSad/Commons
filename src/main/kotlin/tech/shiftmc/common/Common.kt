package tech.shiftmc.common

import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.velocity.VelocityProxy
import tech.shiftmc.common.config.ConfigHelper
import tech.shiftmc.common.config.ServerConfig
import java.nio.file.Path

class Common {

    lateinit var config: ServerConfig
    private val configPath = Path.of("./config.json")

    fun initServer() {
        config = ConfigHelper.initConfigFile(configPath, ServerConfig())

        val server = MinecraftServer.init()

        System.setProperty("minestom.entity-view-distance", config.entityViewDistance.toString())
        System.setProperty("minestom.chunk-view-distance", config.chunkViewDistance.toString())
        MinecraftServer.setCompressionThreshold(0)
        MinecraftServer.setBrandName("Minestom")
        MinecraftServer.setTerminalEnabled(false)

        if (config.velocitySecret.isBlank()) {
            if (config.onlineMode) MojangAuth.init()
        } else { VelocityProxy.enable(config.velocitySecret) }

        server.start(config.ip, config.port)
    }
}