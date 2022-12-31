package tech.shiftmc.common

import net.minestom.server.MinecraftServer
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.velocity.VelocityProxy
import net.minestom.server.instance.block.BlockHandler
import tech.shiftmc.common.blockhandler.BannerHandler
import tech.shiftmc.common.blockhandler.SignHandler
import tech.shiftmc.common.blockhandler.SkullHandler
import tech.shiftmc.common.config.ConfigHelper
import tech.shiftmc.common.config.ServerConfig
import java.nio.file.Path

object Common {

    lateinit var config: ServerConfig
    private val configPath = Path.of("./config.json")

    fun init() {
        // Not always false. The init can be called alone.
        if (config == null) config = ConfigHelper.initConfigFile(configPath, ServerConfig())

//        SignHandler.register("minecraft:sign")
//        SkullHandler.register("minecraft:skull")
//        BannerHandler.register("minecraft:banner")
    }

    fun initServer() {
        config = ConfigHelper.initConfigFile(configPath, ServerConfig())

        val server = MinecraftServer.init()

        System.setProperty("minestom.entity-view-distance", config.entityViewDistance.toString())
        System.setProperty("minestom.chunk-view-distance", config.chunkViewDistance.toString())
        MinecraftServer.setCompressionThreshold(0)
        MinecraftServer.setBrandName("ShiftMC")
        MinecraftServer.setTerminalEnabled(false)

        if (config.velocitySecret.isBlank()) {
            if (config.onlineMode) MojangAuth.init()
        } else { VelocityProxy.enable(config.velocitySecret) }

        init()

        server.start(config.ip, config.port)
    }
}