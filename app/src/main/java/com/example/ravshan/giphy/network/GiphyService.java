package com.example.ravshan.giphy.network;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyService {

    @GET("v1/gifs/search")
    Single<GiphySearchResponse> search(@Query("q") String query);
}

