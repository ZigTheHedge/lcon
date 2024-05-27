package com.cwelth.lcon.server;

/*
import com.netiq.websocket.WebSocket;
import com.netiq.websocket.WebSocketServer;

 */
import net.minecraft.client.player.LocalPlayer;
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
        webSocket.send("200:Welcome to LCon! Have fun! Don't forget to use prefixes with every message you send to me. [chat], [client], [server] are valid prefixes.");
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
        webSocket.send("400:Error! Send message prefix first! [chat], [client], [server] are valid prefixes.");
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }

    @Override
    public void onStart() {

    }
}
