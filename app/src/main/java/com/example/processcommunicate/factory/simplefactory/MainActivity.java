package com.example.processcommunicate.factory.simplefactory;

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

        //简单工厂破环了开闭原则，因为需要修改FruitFactory中的代码
        Fruit fruit = FruitFactory.getInstance().create(FruitFactory.Type.APPLE);
        Log.e(TAG,"type --> "+fruit.type());
        Log.e(TAG,"type --> "+FruitFactory.getInstance().create(FruitFactory.Type.PEAR).type());

    }





}
