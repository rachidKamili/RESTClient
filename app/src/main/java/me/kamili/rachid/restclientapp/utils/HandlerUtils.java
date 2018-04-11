package me.kamili.rachid.restclientapp.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


public class HandlerUtils {

    private static HandlerUtils instance = null;
    private Handler handler;

    private HandlerUtils() {

    }

    public static HandlerUtils getInstance() {
        if (instance == null) {
            instance = new HandlerUtils();
        }
        return instance;
    }

    public void setReceiver(Handler handler) {
        this.handler = handler;
    }

    public void sendMessage(String message) {
        Message handlerMessage = new Message();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULTS_TAG, message);
        handlerMessage.setData(bundle);
        handler.sendMessage(handlerMessage);
    }
}
