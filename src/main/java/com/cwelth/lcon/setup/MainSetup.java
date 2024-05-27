package com.cwelth.lcon.setup;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class MainSetup {
    public static void setup() {
        //Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-common.toml"));

        Registries.setup();
        IEventBus bus = MinecraftForge.EVENT_BUS;
        bus.register(new EventHandlersForge());
    }
}
