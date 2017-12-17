package com.arimil.blackjackclient.packets.responses;


import android.content.Intent;

import com.arimil.blackjackclient.BlackjackListener;
import com.arimil.blackjackclient.User;
import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class HoldResponse extends Message {
    String[] cards;
    int currency;
    String result;

    public HoldResponse() {

    }

    public HoldResponse(String[] dealer, int currency, String result) {

    }

    @Override
    public boolean Process(Connection c, Listener l) {

        BlackjackListener listener = (BlackjackListener) l;

        Intent intent = new Intent();
        intent.setAction("com.arimil.blackjackclient.GameActivity");
        intent.putExtra("type", "hold");
        intent.putExtra("dealer", cards);
        intent.putExtra("result", result);

        User.currency = currency;

        listener.currentContext.sendBroadcast(intent);
        return false;
    }
}
