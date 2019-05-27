package com.example.processcommunicate.proxy.dynamicproxy;

import android.app.Activity;
import android.os.Bundle;

import com.example.processcommunicate.R;
import com.example.processcommunicate.log.Log;

import java.lang.reflect.Proxy;


public class MainActivity extends Activity {


    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_proxy);
        //静态代理相对于静态代理的好处，就是不需要写代理类
        IBank iBank = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(),
                new Class[]{IBank.class}, new DynamicProxy(new Person("mike")));
        iBank.register();
        iBank.getCard();
        iBank.lost();
        Log.e(TAG, "iBank --> " + iBank.getClass().getName());

    }


    //静态代理

    void staticProxy() {

    }


}
