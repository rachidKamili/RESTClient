package me.kamili.rachid.restclientapp.utils;

import android.os.Message;

public class MessageUtils {

    public static String getMessage(Message message) {
        return message.getData().getString(Constants.RESULTS_TAG);
    }

}
