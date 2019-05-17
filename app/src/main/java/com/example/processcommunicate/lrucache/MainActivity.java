package com.example.processcommunicate.lrucache;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedHashMap;



public class MainActivity extends Activity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LruCache<String, Integer> lruCache = new LruCache<String, Integer>(2);
////        lruCache.put("John", new Integer(1000));
////        lruCache.put("Sarah", new Integer(2000));
////        lruCache.put("Mike", new Integer(3000));
////        Log.e(TAG, "lruCache.putCount()" + lruCache.putCount());

        LinkedHashMap<String, Integer> lruCache = new LinkedHashMap<String, Integer>(
                30, 0.5f, true);

        lruCache.put("John", new Integer(3000));
        lruCache.put("Sarah", new Integer(2000));
        lruCache.put("Mike", new Integer(5000));
        //lruCache.put("Mike", new Integer(4000));

        lruCache.get("Sarah");
        for (Iterator<Integer> i = lruCache.values().iterator(); i.hasNext(); ) {
            Integer integer = (Integer) i.next();
            Log.e(TAG, "" + integer);
        }

    }
}
