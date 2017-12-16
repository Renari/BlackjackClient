package com.arimil.blackjackclient.packets;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public abstract class Message {
    public abstract boolean Process(Connection c, Listener l);
}

