package com.arimil.blackjackclient.tasks;


import android.content.Context;
import android.os.AsyncTask;

import com.arimil.blackjackclient.ClientManager;
import com.arimil.blackjackclient.packets.requests.BetRequest;

public class UserBetTask extends AsyncTask<Context, Void, Boolean> {

    private final int mAmount;

    public UserBetTask(int amount) {
        mAmount = amount;
    }

    @Override
    protected Boolean doInBackground(Context... context) {
        ClientManager cm = ClientManager.getInstance(context[0]);
        cm.client.sendTCP(new BetRequest(mAmount));
        return true;
    }
}