package com.example.tony1.login_test.widget;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.tony1.login_test.PostActivity;
import com.example.tony1.login_test.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewName;
    public long postId;

    public ArticleViewHolder(View itemView) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.tv_post_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PostActivity.class);
                intent.putExtra("articleId", articleId);
                v.getContext().startActivity(intent);

            }
        });


    }

}
