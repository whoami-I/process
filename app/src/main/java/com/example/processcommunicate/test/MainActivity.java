package com.example.processcommunicate.test;

import android.app.Activity;
import android.os.Bundle;

import com.example.processcommunicate.R;

class TryTest {
    public static void main() {
        System.out.println(test());
    }

    private static int test() {
        int num = 10;
        try {
            System.out.println("try");
            return num += 80;
        } catch (Exception e) {
            System.out.println("error");
        } finally {
            if (num > 20) {
                System.out.println("num > 20 :" + num);
            }
            System.out.println("finally");
            return 100;
        }
        //return num;
    }
}


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        TryTest.main();

    }
}
