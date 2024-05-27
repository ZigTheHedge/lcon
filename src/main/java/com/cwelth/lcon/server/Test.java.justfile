package com.cwelth.lcon.server;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.IOException;

public class Test {
    private WebSocket ws = null;
    public Test() throws IOException {
        ws = new WebSocketFactory()
                .setConnectionTimeout(1500)
                .createSocket("wss://open-chat.trovo.live/chat");
    }
}
