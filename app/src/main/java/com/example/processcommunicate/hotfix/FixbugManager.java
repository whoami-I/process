package com.example.processcommunicate.hotfix;

import android.content.Context;
import android.os.Environment;

import com.example.processcommunicate.log.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;

public class FixbugManager {

    String fixedDexFilePath;
    Context mContext;

    public FixbugManager(Context context, String fixedDexFilePath) {
        this.fixedDexFilePath = fixedDexFilePath;
        this.mContext = context;
    }

    public void fixBug() {
        ClassLoader cl = mContext.getClassLoader();
        Log.e("TAG", "cl --> " + cl);

        Object[] dexElements = getDexElements(cl);

        File fixdexDir = mContext.getDir("fixdexDir", mContext.MODE_PRIVATE);
        if (!fixdexDir.exists()) {
            fixdexDir.mkdir();
        }

        String path = fixdexDir.getAbsolutePath() + File.separator + "fixdex";
        File dexFile = new File(path);
        File fromFile = new File(fixedDexFilePath);
        //判断fromFile是否存在，如果不存在则跳过复制文件
        if (fromFile.exists()) {
            copyFile(fromFile, dexFile);
        }


        if (dexFile.exists()) {
            //如果有补丁，fixbug
            BaseDexClassLoader baseDexClassLoader = new BaseDexClassLoader(
                    dexFile.getAbsolutePath(), null, null, cl);
            Object[] fixDexElements = getDexElements(baseDexClassLoader);
            dexElements = combineArray(fixDexElements, dexElements);

            inject(cl, dexElements);
        }


    }


    private void inject(ClassLoader cl, Object injected) {

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

    private Object[] combineArray(Object[] left, Object[] right) {

        if (left == null || right == null) {
            throw new IllegalArgumentException("left or right can not be null");
        }

        if (!left.getClass().equals(right.getClass())) {
            throw new IllegalArgumentException("left.class must be same with right.class");
        }

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


    private Object[] getDexElements(ClassLoader cl) {
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

    private void copyFile(File from, File to) {
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
