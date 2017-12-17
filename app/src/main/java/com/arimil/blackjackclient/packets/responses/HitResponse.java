package com.arimil.blackjackclient.packets.responses;

import android.content.Intent;

import com.arimil.blackjackclient.BlackjackListener;
import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


public class HitResponse extends Message {
    String card;

    public HitResponse() {

    }

    public HitResponse(String card) {
        this.card = card;
    }

    @Override
    public boolean Process(Connection c, Listener l) {
        BlackjackListener listener = (BlackjackListener) l;

        Intent intent = new Intent();
        intent.setAction("com.arimil.blackjackclient.GameActivity");
        intent.putExtra("type", "hit");
        intent.putExtra("card", card);

        listener.currentContext.sendBroadcast(intent);
        return true;
    }
}
