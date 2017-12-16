package com.arimil.blackjackclient.tasks;


import android.content.Context;
import android.os.AsyncTask;

import com.arimil.blackjackclient.ClientManager;
import com.arimil.blackjackclient.packets.requests.LoginRequest;


public class UserLoginTask extends AsyncTask<Context, Void, Boolean> {

    private final String mUsername;
    private final String mPassword;

    public UserLoginTask(String user, String password) {
        mUsername = user;
        mPassword = password;
    }

    @Override
    protected Boolean doInBackground(Context... context) {
        ClientManager cm = ClientManager.getInstance(context[0]);
        cm.client.sendTCP(new LoginRequest(mUsername, mPassword));
        return true;
    }
}