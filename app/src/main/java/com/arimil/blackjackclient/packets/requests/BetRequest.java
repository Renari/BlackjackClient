package com.arimil.blackjackclient.packets.requests;

import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class BetRequest extends Message {

    public int amount = 0;

    BetRequest() {

    }

    public BetRequest(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        return false;
    }
}
