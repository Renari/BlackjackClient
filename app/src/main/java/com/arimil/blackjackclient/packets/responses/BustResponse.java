package com.arimil.blackjackclient.packets.responses;

import android.content.Intent;

import com.arimil.blackjackclient.BlackjackListener;
import com.arimil.blackjackclient.User;
import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class BustResponse extends Message {
    String card;
    int currency;

    public BustResponse() {

    }

    public BustResponse(String card, int currency) {
        this.card = card;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        BlackjackListener listener = (BlackjackListener) l;

        Intent intent = new Intent();
        intent.setAction("com.arimil.blackjackclient.GameActivity");
        intent.putExtra("type", "bust");
        intent.putExtra("card", card);

        User.currency = currency;

        listener.currentContext.sendBroadcast(intent);
        return true;
    }
}
