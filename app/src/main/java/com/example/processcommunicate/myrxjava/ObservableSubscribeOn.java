package com.example.processcommunicate.myrxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ObservableSubscribeOn<T> extends Observable<T> {
    private static final String TAG = "ObservableSubscribeOn";

    private ObservableSource<T> source;
    private Schedulers schedulers;
    private ThreadPoolExecutor threadPoolExecutor;

    public ObservableSubscribeOn(ObservableSource<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
        threadPoolExecutor = new ThreadPoolExecutor(3,
                5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));
    }


    @Override
    public void subscribe(Observer<T> observer) {
        SubscribeOnObserver<T> subscribeOnObserver = new SubscribeOnObserver<>(observer);
        //source.subscribe(observer);
        Task task = new Task(subscribeOnObserver);
        //Log.e(TAG, "subscribe");
        threadPoolExecutor.execute(task);
    }

    class Task implements Runnable {
        Observer<T> observer;

        Task(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            //Log.e(TAG, "execute task");
            source.subscribe(observer);
        }
    }


    class SubscribeOnObserver<T> implements Observer<T> {

        Observer<T> observer;

        SubscribeOnObserver(Observer observer) {
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {

        }

        @Override
        public void onNext(T t) {
            Looper looper = Looper.getMainLooper();
            Handler handler = new Handler(looper);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Log.e(TAG, "run() thread -->" + Thread.currentThread());
                    observer.onNext(t);

                }
            });

        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {

        }
    }
}
