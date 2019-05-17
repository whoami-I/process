package com.example.processcommunicate.retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.processcommunicate.R;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {


    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_retrofit);
        YouDaoTranslate();

    }

    Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.e(TAG, "request --> " + request.toString());
                okhttp3.Response response = chain.proceed(request);

                return response;
            }
        };
    }

    void YouDaoTranslate() {
        Interceptor myInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {
                Log.e(TAG, "log --> " + s);
            }
        });

        ((HttpLoggingInterceptor) myInterceptor).setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(myInterceptor)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YouDaoApiService apiService = retrofit.create(YouDaoApiService.class);
        Call<YouDaoData> translation = apiService.getTranslation("welcome to china");
        translation.enqueue(new Callback<YouDaoData>() {
            @Override
            public void onResponse(Call<YouDaoData> call, Response<YouDaoData> response) {
                String out = response.body().getTranslateResult().get(0).get(0).getTgt();
                Log.e(TAG, "out --> " + out);
                Toast.makeText(MainActivity.this, out, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<YouDaoData> call, Throwable t) {

            }
        });
    }


    void getUsername() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer2 apiServer2 = retrofit.create(ApiServer2.class);
        Call<TestData> testData =
                apiServer2.getTestData("dragon");

        testData.enqueue(new Callback<TestData>() {
            @Override
            public void onResponse(Call<TestData> call, retrofit2.Response<TestData> response) {
                TestData body = response.body();
                Log.e(TAG, body.getMsg());
            }

            @Override
            public void onFailure(Call<TestData> call, Throwable t) {
                Log.e(TAG, "onFailure");
            }
        });
    }

    void f1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Call<TestData> testData =
                apiServer.getTestData();

        testData.enqueue(new Callback<TestData>() {
            @Override
            public void onResponse(Call<TestData> call, retrofit2.Response<TestData> response) {
                TestData body = response.body();

            }

            @Override
            public void onFailure(Call<TestData> call, Throwable t) {

            }
        });
    }


}
