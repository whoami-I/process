package com.example.processcommunicate.hotfix;

import android.app.Application;
import android.os.Environment;

import com.example.processcommunicate.log.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String fromPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
                "1myfile" + File.separator + "fixdex";
        FixbugManager fixbugManager = new FixbugManager(this, fromPath);
        fixbugManager.fixBug();


//        ClassLoader cl = getClassLoader();
//        Log.e("TAG", "cl --> " + cl);
//
//        Object[] dexElements = getDexElements(cl);
//
//
//
//        String path = getDir("fixdexDir", MODE_PRIVATE)
//                .getAbsolutePath() + File.separator + "fixdex";
//
//        String fromPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +
//                "1myfile" + File.separator + "fixdex";
//        File dexFile = new File(path);
//        File fromFile = new File(fromPath);
//        copyFile(fromFile, dexFile);
//
//        BaseDexClassLoader baseDexClassLoader = new BaseDexClassLoader(
//                dexFile.getAbsolutePath(), null, null, cl);
//        Object[] fixDexElements = getDexElements(baseDexClassLoader);
//        dexElements = combineArray(fixDexElements, dexElements);
//        //5、注入
//        inject(cl, dexElements);
    }

    void inject(ClassLoader cl, Object injected) {

        try {
            Field pathListField = null;
            Object[] dexElements = null;
            pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            if (!pathListField.isAccessible()) pathListField.setAccessible(true);
            Object pathList = pathListField.get(cl);

            //3、找到DexPathList的Element[] dexElements
            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            if (!dexElementsField.isAccessible()) dexElementsField.setAccessible(true);
            dexElementsField.set(pathList, injected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    Object[] combineArray(Object[] left, Object[] right) {

        int rightLength = right.length;
        int leftLength = left.length;
        Class<?> leftClass = left[0].getClass();

        Object[] newArray = (Object[]) Array.newInstance(leftClass, leftLength + rightLength);
        for (int i = 0; i < leftLength; i++) {
            newArray[i] = left[i];
        }
        for (int i = 0; i < rightLength; i++) {
            newArray[i + leftLength] = right[i];
        }
        return newArray;
    }

    Object[] getDexElements(ClassLoader cl) {
        //2、找到PathClassLoader父类的BaseDexClassLoader中的pathList
        Field pathListField = null;
        Object[] dexElements = null;
        try {

            pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
            if (!pathListField.isAccessible()) pathListField.setAccessible(true);
            Object pathList = pathListField.get(cl);

            //3、找到DexPathList的Element[] dexElements
            Field dexElementsField = pathList.getClass().getDeclaredField("dexElements");
            if (!dexElementsField.isAccessible()) dexElementsField.setAccessible(true);
            dexElements = (Object[]) dexElementsField.get(pathList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dexElements;

    }

    void copyFile(File from, File to) {


        try {
            FileInputStream fis = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to);
            byte[] bf = new byte[1024 * 1024];
            int len = -1;
            while ((len = fis.read(bf)) != -1) {
                fos.write(bf, 0, len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
