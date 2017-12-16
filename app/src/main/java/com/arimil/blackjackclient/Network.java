package com.arimil.blackjackclient;

import com.arimil.blackjackclient.packets.requests.BetRequest;
import com.arimil.blackjackclient.packets.requests.LoginRequest;
import com.arimil.blackjackclient.packets.responses.BetResponse;
import com.arimil.blackjackclient.packets.responses.LoginResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    static final int PORT = 50236;
    static final int TIMEOUT = 5000;

    static void register(EndPoint endpoint) {
        Kryo kryo = endpoint.getKryo();
        // Register all requests
        kryo.register(LoginRequest.class);
        kryo.register(BetRequest.class);

        // Register all responses
        kryo.register(LoginResponse.class);
        kryo.register(BetResponse.class);
    }
}
