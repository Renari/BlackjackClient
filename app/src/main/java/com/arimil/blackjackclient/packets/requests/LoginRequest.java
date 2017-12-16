package com.arimil.blackjackclient.packets.requests;

import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LoginRequest extends Message {
    private String username;
    private String password;

    public LoginRequest() {

    }

    public LoginRequest (String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        //client does not need to process requests
        return false;
    }
}
