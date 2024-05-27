package com.cwelth.lcon.server;

/*
import com.netiq.websocket.WebSocket;
import com.netiq.websocket.WebSocketServer;

 */
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.ClientCommandHandler;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WSSListener extends WebSocketServer {
    private final LocalPlayer player;

    public WSSListener(int port, LocalPlayer player)
    {
        super(new InetSocketAddress(port));
        this.player = player;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        webSocket.send("200:Welcome to LCon! Have fun! Don't forget to use prefixes with every message you send to me.");
        webSocket.send("200:Valid prefixes:");
        webSocket.send("200:[chat] - send message to Minecraft chat.");
        webSocket.send("200:[message] - display message for player only.");
        webSocket.send("200:[client] - execute client-side command.");
        webSocket.send("200:[server] - execute server-side command.");
        webSocket.send("201:ready.");
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        String clearMessage = "";
        if(s.startsWith("[chat]"))
        {
            // Send chat
            clearMessage = s.substring(6);
            player.connection.sendChat(clearMessage);
            return;
        }
        if(s.startsWith("[message]"))
        {
            // Send chat
            clearMessage = s.substring(9);
            player.displayClientMessage(Component.literal(clearMessage), true);
            return;
        }
        if(s.startsWith("[client]"))
        {
            // Client Command
            clearMessage = s.substring(8);
            if(clearMessage.startsWith("/")) clearMessage = clearMessage.substring(1);
            ClientCommandHandler.runCommand(clearMessage);
            return;
        }
        if(s.startsWith("[server]"))
        {
            // Server Command
            clearMessage = s.substring(8);
            if(clearMessage.startsWith("/")) clearMessage = clearMessage.substring(1);
            player.connection.sendCommand(clearMessage);
            return;
        }
        webSocket.send("400:Error! Send message prefix first! [chat], [message], [client], [server] are valid prefixes.");
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
