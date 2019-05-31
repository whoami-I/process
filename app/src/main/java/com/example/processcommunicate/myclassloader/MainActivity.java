package com.example.processcommunicate.myclassloader;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;

import com.example.processcommunicate.R;
import com.example.processcommunicate.base.BaseActivity;
import com.example.processcommunicate.log.Log;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * 这个代码实现了自定义ClassLoader，实现了加载两个具有相同的全限定名的类
 * 实现的方法就是自己定义一个DexClassLoader，设定好需要加载类的位置，然后
 * 还要定义一个MyClassLoder作为DexClassLoader Parent Classloder，因为如果使用apk的ClassLoader
 * 作为Parent Classloder的话，会由于双亲委派模式导致加载的只能是apk中的类，
 * 定义MyClassLoder的话，需要注意：因为我们想要加载的类继承自Object，因此加载器
 * 会去找Object类，因此在MyClassLoder中使用代理，如果要找自己定义的类，那么就返回null，
 * 使得DexClassLoader去找到，如果是其他的类，那么就使用apk的ClassLoader去找
 */
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

            Class<?> aClass = dexClassLoader.loadClass("com.example.processcommunicate.myclassloader.A");
//            Object a = aClass.newInstance();
//            Method show = aClass.getDeclaredMethod("show");
//            show.setAccessible(true);
//            show.invoke(a);
            //之所以转化成接口的原因是，在这里如果转化成A，那么就会导致转化异常。因为在这里A就是指
            //apk ClassLoader加载得到的A
            IA a = (IA) aClass.newInstance();
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "dexClassLoader can not find A");
        }

        try {
            Class<?> aClass = sysClassLoader.loadClass("com.example.processcommunicate.myclassloader.A");
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
