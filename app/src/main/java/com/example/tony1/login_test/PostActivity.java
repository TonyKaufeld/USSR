package com.example.tony1.login_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = PostActivity.class.getName();

    private APIInterface apiInterface;

    private TextView tvName;
    private TextView tvAuthor;
    private TextView tvArticleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // Initialize the API Client with the corresponding API methods/functions
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // Get reference to the views and keep these in mind
        tvName = findViewById(R.id.tvName);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvArticleText = findViewById(R.id.tvArticleText);

        /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });
        */
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
        tvAuthor.setText("");
        tvArticleText.setText("");
    }
    /**
     * Show article data
     *
     * @param articleFromAPI
     */
    private void showArticleInTextViews(Article articleFromAPI) {

        tvName.setText(articleFromAPI.getName());
        tvAuthor.setText(articleFromAPI.getAuthor());
        tvArticleText.setText(articleFromAPI.getText());

    }

}
