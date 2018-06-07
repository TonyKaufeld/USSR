package com.example.tony1.login_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity
{
    private static final String TAG = PostActivity.class.getName();

    private APIInterface apiInterface;

    private TextView tvName;
    private TextView tvTitle;
    private TextView tvImg;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Initialize the API Client with the corresponding API methods/functions
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // Get reference to the views and keep these in mind
        tvName = findViewById(R.id.tvName);
        tvTitle = findViewById(R.id.tvTitle);
        tvImg = findViewById(R.id.tvImg);
        btnPost = findViewById(R.id.btnPost);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, AddPost.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        emptyArticleTextViews();

        // Get one article from the API though the internet.
        Call<Article> call = apiInterface.getArticle(4);
        call.enqueue(new Callback<Article>() {
            @Override
            public void onResponse(Call<Article> call, Response<Article> response) {
                Article article = response.body();
                if (null != article) {
                    showArticleInTextViews(article);
                } else {
                    Log.e(TAG, "Got no article, because:" + response.raw());
                }
            }

            @Override
            public void onFailure(Call<Article> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    /**
     * To ensure text views are emptied before we fresh texts in
     */
    private void emptyArticleTextViews(){
        tvName.setText("");
        tvTitle.setText("");
        tvImg.setText("");
    }
    /**
     * Show article data
     *
     * @param articleFromAPI
     */
    private void showArticleInTextViews(Article articleFromAPI) {

        tvName.setText(articleFromAPI.getName());
        tvTitle.setText(articleFromAPI.getTitle());
        tvImg.setText(articleFromAPI.getImg());

    }

}
