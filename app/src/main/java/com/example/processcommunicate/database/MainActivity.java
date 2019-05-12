package com.example.processcommunicate.database;

import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.processcommunicate.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_database);
        IDaoSupport<Person> dao = DaoFactory.getFactory().getDao(Person.class);
        //dao.insert(new Person("xiaoming", 25));
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            persons.add(new Person("xiaoming", i,"male"));
        }

        long startTime = System.currentTimeMillis();
        dao.insert(persons);
        long endTime = System.currentTimeMillis();
        Log.e(TAG, "time --> " + (endTime - startTime));
        //ContentValues.class.getde
    }


}
