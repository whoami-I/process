package com.example.processcommunicate.proxy.staticproxy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.processcommunicate.R;
import com.example.processcommunicate.retrofit.ApiServer;
import com.example.processcommunicate.retrofit.ApiServer2;
import com.example.processcommunicate.retrofit.TestData;
import com.example.processcommunicate.retrofit.YouDaoApiService;
import com.example.processcommunicate.retrofit.YouDaoData;

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
        setContentView(R.layout.layout_proxy);
        IBank iBank = new Clerk(new Person("jack"));
        iBank.register();
        iBank.getCard();


    }


    //静态代理

    void staticProxy() {

    }


}
