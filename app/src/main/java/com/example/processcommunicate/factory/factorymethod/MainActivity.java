package com.example.processcommunicate.factory.factorymethod;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.processcommunicate.R;


public class MainActivity extends Activity {


    private final static String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_factory);
        //工厂方法也会有缺点，因为随着产品越来越多，相应的工厂也会很多
        //不利于管理，因此只适用于产品比较少的时候
        FruitFactory fruitFactory = new AppleFactory();
        Fruit fruit = fruitFactory.create();
        Log.e(TAG, "fruit --> " + fruit.type());

    }


}
