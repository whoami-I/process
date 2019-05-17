package com.example.processcommunicate.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServer {
    @GET("users/list")
    Call<TestData> getTestData();

}
