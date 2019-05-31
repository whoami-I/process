package com.example.processcommunicate.mvp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.example.processcommunicate.mvp.base.BaseModel;

import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BitmapModel implements BaseModel {

    BitmapPresenter bitmapPresenter;



    void getBitmap(String url) throws Exception {




    }
}
