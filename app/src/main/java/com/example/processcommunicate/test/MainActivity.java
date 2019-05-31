package com.example.processcommunicate.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;
import com.example.processcommunicate.log.Log;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

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


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TryTest.main();

    }

    @Override
    protected void onSuccess() {
        setContentView(R.layout.layout_test);

        ClassLoader sysClassLoader = getClassLoader();


        try {
            File optimizedDir = new File(getDir("optimized", Context.MODE_PRIVATE).getAbsolutePath());
            if (!optimizedDir.exists()) {
                optimizedDir.mkdir();
            }

            MyClassLoader myClassLoader = new MyClassLoader(sysClassLoader);


            DexClassLoader dexClassLoader = new DexClassLoader(Environment.getExternalStorageDirectory().getAbsolutePath() +
                    File.separator + "1fix" + File.separator + "classes2.dex", optimizedDir.getAbsolutePath(), null, myClassLoader);

            Class<?> aClass = dexClassLoader.loadClass("com.example.processcommunicate.test.A");
//            Object a = aClass.newInstance();
//            Method show = aClass.getDeclaredMethod("show");
//            show.setAccessible(true);
//            show.invoke(a);

            IA a = (IA) aClass.newInstance();
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "dexClassLoader can not find A");
        }

        try {
            Class<?> aClass = sysClassLoader.loadClass("com.example.processcommunicate.test.A");
            IA a = (IA) aClass.newInstance();
            a.show();

        } catch (Exception e) {
            Log.e("TAG", "sysClassLoader can not find A");
        }

    }

    class MyClassLoader extends ClassLoader {
        private ClassLoader cl;

        public MyClassLoader(ClassLoader cl) {
            this.cl = cl;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if ("com.example.processcommunicate.test.A".equals(name))
                return null;
            else {
                return cl.loadClass(name);
            }
        }


    }
}
