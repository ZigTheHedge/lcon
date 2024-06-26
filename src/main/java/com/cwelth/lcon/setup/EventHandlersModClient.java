package com.cwelth.lcon.setup;


import com.cwelth.lcon.Config;
import com.cwelth.lcon.LCon;
import com.cwelth.lcon.server.WSSListener;
import com.google.gson.Gson;
import com.google.gson.JsonSerializer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = LCon.MODID)
public class EventHandlersModClient {
    @SubscribeEvent
    public static void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS)
        {

        }
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.PlayerTickEvent event) throws IOException {
        if(!event.player.level().isClientSide) return;

        LocalPlayer player = (LocalPlayer) event.player;
        if(player != null)
        {
            if(Config.ENABLE_MOD.get())
            {
                if(LCon.wss == null)
                {
                    LCon.wss = new WSSListener(Config.PORT.get(), player);
                    LCon.wss.start();
                } else
                    LCon.wss.updatePlayer(player);

            }
        }
    }

    @SubscribeEvent
    public void logOut(ClientPlayerNetworkEvent.LoggingOut event){
        if(event.getConnection() == null) return;
        if(LCon.wss != null) {
            try {
                LCon.wss.stop(3, "201:closed.");
            } catch (InterruptedException e) {
            } finally {
                LCon.wss = null;
            }
        }
    }

    @SubscribeEvent
    public static void getChatMessage(ClientChatReceivedEvent event) throws IOException {
        if(LCon.wss != null)
        {
            Gson gson = new Gson();
            LCon.wss.broadcast("200:" + gson.toJson(event.getMessage().getContents().toString()));
        }
    }
}
