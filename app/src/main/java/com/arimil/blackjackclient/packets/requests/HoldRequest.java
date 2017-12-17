package com.arimil.blackjackclient.packets.requests;

import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class HoldRequest extends Message {
    @Override
    public boolean Process(Connection c, Listener l) {
        return false;
    }
}
