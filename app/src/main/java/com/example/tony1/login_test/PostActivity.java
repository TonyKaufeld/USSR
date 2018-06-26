package com.example.tony1.login_test;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tony1.login_test.widget.PostRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity
{
    private static final String TAG = PostActivity.class.getName();

    private CoordinatorLayout coordinatorLayout;
    private ProgressBar progress;
    private APIInterface apiInterface;
    private PostRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Context context = this;

        progress = findViewById(R.id.progress_bar);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // Create a new ToDoRecycleView Adapter, which needs context and a database cursor to fetch data
        adapter = new PostRecycleViewAdapter(context);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_articles);

        // Attach a layout manager and the data adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        progress.setVisibility(View.VISIBLE);

        /**
         GET List Resources
         **/
        Call<List<Article>> call = apiInterface.listArticles();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                Log.d(TAG, String.valueOf(response.code()));

                progress.setVisibility(View.GONE);

                List<Article> articles = response.body();

                // Notify adapter which will refresh the data in the recycler view
                adapter.notifyWithNewData(articles);

            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                call.cancel();
                adapter.notifyWithNewData(new ArrayList<Article>());

                progress.setVisibility(View.GONE);

            }


        });


    }

}
