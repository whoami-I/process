package com.example.processcommunicate.myokhttp.okhttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Dispatcher {
    ExecutorService executorService;

    public Dispatcher() {

    }

    public Dispatcher enqueue(RealCall.AsyncCall asyncCall) {
        executorService().execute(asyncCall);
        return this;
    }

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0,
                    Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<>(), new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread t = new Thread(r, "okHttp");
                    t.setDaemon(false);
                    return t;
                }
            });
        }
        return executorService;
    }
}
