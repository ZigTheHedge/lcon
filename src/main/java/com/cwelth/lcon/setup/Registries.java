package com.cwelth.lcon.setup;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.cwelth.lcon.LCon.MODID;

public class Registries {

    public static void setup() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
