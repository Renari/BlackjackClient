package com.arimil.blackjackclient.tasks;


import android.content.Context;
import android.os.AsyncTask;

import com.arimil.blackjackclient.ClientManager;
import com.arimil.blackjackclient.packets.requests.HoldRequest;

public class UserHoldTask extends AsyncTask<Context, Void, Boolean> {

    public UserHoldTask() {
    }

    @Override
    protected Boolean doInBackground(Context... context) {
        ClientManager cm = ClientManager.getInstance(context[0]);
        cm.client.sendTCP(new HoldRequest());
        return true;
    }
}