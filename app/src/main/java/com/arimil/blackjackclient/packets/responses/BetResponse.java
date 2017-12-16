package com.arimil.blackjackclient.packets.responses;

import android.content.Intent;

import com.arimil.blackjackclient.BlackjackListener;
import com.arimil.blackjackclient.packets.Message;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class BetResponse extends Message {

    String card;
    String dealerCard;

    public BetResponse() {

    }

    @Override
    public boolean Process(Connection c, Listener l) {
        BlackjackListener listener = (BlackjackListener) l;

        Intent intent = new Intent();
        intent.setAction("com.arimil.blackjackclient.GameActivity");
        intent.putExtra("type", "bet");
        intent.putExtra("card", card);
        intent.putExtra("dealer", dealerCard);
        listener.currentContext.sendBroadcast(intent);
        return true;
    }
}
