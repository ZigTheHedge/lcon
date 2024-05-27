package com.cwelth.lcon;

import com.cwelth.lcon.server.WSSListener;
import com.cwelth.lcon.setup.MainSetup;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.slf4j.Logger;

@Mod(LCon.MODID)
public class LCon
{
    public static final String MODID = "lcon";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static WSSListener wss = null;
	
    public LCon()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + "-client.toml"));
        MainSetup.setup();
    }
}
