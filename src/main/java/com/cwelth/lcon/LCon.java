package com.cwelth.lcon;

import com.cwelth.lcon.server.WSSListener;
import com.cwelth.lcon.setup.MainSetup;
import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(LCon.MODID)
public class LCon
{
    public static final String MODID = "lcon";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static WSSListener wss = null;
	
    public LCon()
    {
        MainSetup.setup();
    }
}
