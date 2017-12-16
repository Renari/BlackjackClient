package com.arimil.blackjackclient.packets.responses;

import android.content.Intent;

import com.arimil.blackjackclient.BlackjackListener;
import com.arimil.blackjackclient.User;
import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class LoginResponse extends Message {

    int currency;
    String errors;

    public LoginResponse() {

    }

    public LoginResponse(int currency) {
        this.currency = currency;
    }

    public LoginResponse(String errors) {
        this.errors = errors;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        BlackjackListener listener = (BlackjackListener) l;

        User.currency = currency;

        Intent intent = new Intent();
        intent.setAction("com.arimil.blackjackclient.LoginActivity");
        intent.putExtra("errors", errors);
        listener.currentContext.sendBroadcast(intent);
        return true;
    }
}
