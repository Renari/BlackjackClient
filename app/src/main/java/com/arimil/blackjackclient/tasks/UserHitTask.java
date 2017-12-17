package com.arimil.blackjackclient.tasks;


import android.content.Context;
import android.os.AsyncTask;

import com.arimil.blackjackclient.ClientManager;
import com.arimil.blackjackclient.packets.requests.HitRequest;

public class UserHitTask extends AsyncTask<Context, Void, Boolean> {

    public UserHitTask() {
    }

    @Override
    protected Boolean doInBackground(Context... context) {
        ClientManager cm = ClientManager.getInstance(context[0]);
        cm.client.sendTCP(new HitRequest());
        return true;
    }
}