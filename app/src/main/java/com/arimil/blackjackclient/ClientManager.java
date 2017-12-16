package com.arimil.blackjackclient;

import android.content.Context;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class ClientManager {

    private static ClientManager instance = null;

    public Client client;

    private ClientManager(Context context) throws IOException {
        client = new Client();
        client.addListener(new BlackjackListener(context));
        Network.register(client);
        client.start();
        client.connect(Network.TIMEOUT, "10.0.2.2", Network.PORT);
    }

    public static ClientManager getInstance(Context context) {
        if (instance == null) {
            try {
                instance = new ClientManager(context);
            } catch (IOException e) {
                e.printStackTrace();
                //show connection error dialog
            }
        }
        return instance;
    }
}
