package com.arimil.blackjackclient;

import android.content.Context;
import android.content.Intent;

import com.arimil.blackjackclient.activities.LoginActivity;
import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BlackjackListener extends Listener {
    public Context currentContext;

    BlackjackListener(Context context) {
        this.currentContext = context;
    }

    @Override
    public void disconnected(Connection connection) {
        Intent intent = new Intent(currentContext, LoginActivity.class);
        currentContext.startActivity(intent);
    }

    @Override
    public void received(Connection connection, Object o) {
        //handle message here
        if (o instanceof Message) {
            Message p = (Message) o;
            p.Process(connection, this);
        }
    }
}
