package com.example.tony1.login_test;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("articles")
    Call<List<Article>> listArticles();

    @GET("article/{id}")
    Call<Article> getArticle(@Path("id") long id);

}
