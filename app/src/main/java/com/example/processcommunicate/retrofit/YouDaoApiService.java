package com.example.processcommunicate.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YouDaoApiService {

    //@GET("ajax.php?a=fy&f=auto&t=zh")
    @GET("translate?&doctype=json&type=AUTO")
    Call<YouDaoData> getTranslation(@Query("i") String enString);

}
