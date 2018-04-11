package me.kamili.rachid.restclientapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import me.kamili.rachid.restclientapp.helpers.OkHttpHelper;
import me.kamili.rachid.restclientapp.model.GitHubProfile;
import me.kamili.rachid.restclientapp.utils.Constants;
import me.kamili.rachid.restclientapp.utils.HandlerUtils;
import me.kamili.rachid.restclientapp.utils.MessageUtils;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        HandlerUtils.getInstance().setReceiver(new Handler(this));
    }

    public void showProfile(View view) {
        //Get the username removing the special characters
        String userName = etUsername.getText().toString().replaceAll("\\W", "");
        OkHttpHelper.execute(Constants.GITHUB_USER_URL + userName);
    }

    @Override
    public boolean handleMessage(Message msg) {

        Gson gson = new Gson();
        GitHubProfile gitHubProfile = gson.fromJson(MessageUtils.getMessage(msg), GitHubProfile.class);

        //Checking if the username is correct
        if (gitHubProfile.getHtmlUrl() != null){
            // TODO: 4/10/2018 Starting the ProfileActivity
        }else {
            Toast.makeText(this, "Username is not correct!",Toast.LENGTH_LONG).show();
            etUsername.setText("");
        }

        return false;
    }
}
