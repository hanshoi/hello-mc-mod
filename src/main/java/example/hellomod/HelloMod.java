package example.hellomod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloMod implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("modid");
    public static MinecraftServer SERVER;

    @Override
    public void onInitialize() {
        LOGGER.info("Hello fabirc world!");
        ServerLifecycleEvents.SERVER_STARTED.register((server -> SERVER = server));
    }
}