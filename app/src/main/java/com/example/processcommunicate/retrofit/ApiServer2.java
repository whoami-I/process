package com.example.processcommunicate.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServer2 {

    @GET("TestServelet/LoginServlet")
    Call<TestData> getTestData(@Query("username")String name);
}
