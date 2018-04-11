package me.kamili.rachid.restclientapp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.kamili.rachid.restclientapp.adapters.RepositoryAdapter;
import me.kamili.rachid.restclientapp.helpers.OkHttpHelper;
import me.kamili.rachid.restclientapp.model.GitHubProfile;
import me.kamili.rachid.restclientapp.model.Repository;
import me.kamili.rachid.restclientapp.utils.Constants;
import me.kamili.rachid.restclientapp.utils.HandlerUtils;
import me.kamili.rachid.restclientapp.utils.MessageUtils;

public class RepositoryActivity extends AppCompatActivity implements Handler.Callback {

    protected RecyclerView.Adapter mAdapter;
    protected List<Repository> mRepositories;
    private String reposUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        reposUrl = getIntent().getStringExtra(Constants.GITHUB_ROPOS_URL);

        if (reposUrl != null) {
            HandlerUtils.getInstance().setReceiver(new Handler(this));
            OkHttpHelper.execute(reposUrl);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {

        String results = MessageUtils.getMessage(msg);
        mRepositories = Arrays.asList(new Gson().fromJson(results, Repository[].class));

        RecyclerView mRecyclerView = findViewById(R.id.rvRepositories);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RepositoryAdapter(mRepositories);
        mRecyclerView.setAdapter(mAdapter);

        return false;
    }
}
