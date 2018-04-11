package me.kamili.rachid.restclientapp.helpers;

import java.io.IOException;

import me.kamili.rachid.restclientapp.utils.HandlerUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper {

    public static void execute(String baseUrl) {

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(baseUrl)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    HandlerUtils.getInstance().sendMessage(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
