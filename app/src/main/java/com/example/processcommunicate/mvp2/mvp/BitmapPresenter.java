package com.example.processcommunicate.mvp2.mvp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.processcommunicate.mvp2.mvp.base.BaseModel;
import com.example.processcommunicate.mvp2.mvp.base.BasePresenter;

import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BitmapPresenter extends BasePresenter {


    void getBitmap(String url) {

        io.reactivex.Observable.just(url).map(new Function<String, Bitmap>() {


            @Override
            public Bitmap apply(String s) throws Exception {

                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url)
                        .get().build();
                Call call = okHttpClient.newCall(request);
                Response response = call.execute();
                InputStream is = response.body().byteStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                return bitmap;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {

                        //if (mView != null)
                        getView().onSuccess(bitmap);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //if (mView != null)
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



    @Override
    public BaseModel createModel() {
        return new BitmapModel();
    }
}
