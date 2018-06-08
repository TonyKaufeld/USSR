package com.example.tony1.login_test.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony1.login_test.Article;
import com.example.tony1.login_test.R;

import java.util.ArrayList;
import java.util.List;

public class PostRecycleViewAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<Article> posts = new ArrayList<>();
    private Context context;

    public PostRecycleViewAdapter(Context context) {
        this.context = context;
    }


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(R.layout.post_layout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        Article post = posts.get(position);
        holder.postId = post.getId();
        holder.textViewName.setText(post.getName());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (null != posts) {
            count = posts.size();
        }
        return count;
    }


    public void notifyWithNewData(List<Article> articles) {

        // Attach new articles to adapter property and notify
        this.posts = articles;
        notifyDataSetChanged();

    }

}
